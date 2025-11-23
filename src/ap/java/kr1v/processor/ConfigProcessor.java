package kr1v.processor;

import com.google.auto.service.AutoService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.source.tree.*;
import com.sun.source.util.TreePath;
import com.sun.source.util.Trees;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.FileObject;
import javax.tools.StandardLocation;
import java.io.Writer;
import java.util.*;
import java.util.stream.Collectors;


@SupportedSourceVersion(SourceVersion.RELEASE_21)
@SupportedAnnotationTypes({"kr1v.kr1vUtils.client.utils.annotation.classannotations.Config",
        "kr1v.kr1vUtils.client.utils.annotation.classannotations.PopupConfig"})
@AutoService(Processor.class)
public class ConfigProcessor extends AbstractProcessor {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    // fqcn -> class representation
    private static final Map<String, List<ElementRepresentation>> map = new HashMap<>();

    private Trees trees;
    private Elements elementUtils;
    private Types typeUtils;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        this.trees = Trees.instance(processingEnv);
        this.elementUtils = processingEnv.getElementUtils();
        this.typeUtils = processingEnv.getTypeUtils();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annos, RoundEnvironment roundEnv) {
        Set<TypeElement> classes = new HashSet<>();
        for (TypeElement a : annos) {
            for (javax.lang.model.element.Element e : roundEnv.getElementsAnnotatedWith(a)) {
                if (e.getKind() == ElementKind.CLASS || e.getKind() == ElementKind.INTERFACE) {
                    classes.add((TypeElement) e);
                }
            }
        }

        for (TypeElement typeElement : classes) {
            String fqcn = elementUtils.getBinaryName(typeElement).toString();
            List<ElementRepresentation> classRepresentation = new ArrayList<>();

            TreePath typePath = trees.getPath(typeElement);

            ClassTree classTree = (ClassTree) typePath.getLeaf();
            List<? extends Tree> members = classTree.getMembers();

            for (Tree memberTree : members) {
                Element memberElement = trees.getElement(new TreePath(typePath, memberTree));

                if (memberElement == null) {
                    continue;
                }

                List<? extends AnnotationMirror> annotations = getAnnotations(memberElement);

                List<AnnotationDTO> annotationStrings = new ArrayList<>();

                for (var annotation : annotations) {
                    annotationStrings.add(toDTO(annotation, elementUtils));
                }

                switch (memberElement) {
                    case VariableElement field -> {
                        ElementRepresentation representation = new ElementRepresentation("field", field.getSimpleName().toString());
                        representation.annotations = annotationStrings;
                        classRepresentation.add(representation);
                    }
                    case ExecutableElement method -> {
                        ElementRepresentation representation = new ElementRepresentation("method", method.getSimpleName().toString());
                        representation.annotations = annotationStrings;
                        classRepresentation.add(representation);
                    }
                    case TypeElement inner -> {
                        ElementRepresentation representation = new ElementRepresentation("innerClass", inner.getQualifiedName().toString());
                        representation.annotations = annotationStrings;
                        classRepresentation.add(representation);
                    }
                    default -> {

                    }
                }
            }

            map.put(fqcn, classRepresentation);
        }

        try {
            Filer filer = processingEnv.getFiler();
            if (roundEnv.processingOver()) {
                FileObject file = filer.createResource(StandardLocation.CLASS_OUTPUT, "", "META-INF/kr1v/classes.json");
                try (Writer w = file.openWriter()) {
                    GSON.toJson(map, w);
                }
            }
            println("Written map. classes processed: " + map.size());
        } catch (Throwable t) {
            processingEnv.getMessager().printError("Failed to write resource: " + t);
        }

        return false;
    }

    private List<AnnotationMirror> getAnnotations(Element element) {
        TreePath elementPath = trees.getPath(element);

        if (elementPath == null) {
            return element.getAnnotationMirrors().stream().map(m -> (AnnotationMirror) m).collect(Collectors.toList());
        }

        Tree leaf = elementPath.getLeaf();
        List<? extends AnnotationMirror> mirrors = element.getAnnotationMirrors();
        if (mirrors.isEmpty()) return List.of();

        List<? extends AnnotationTree> annotationTrees = switch (leaf) {
            case ClassTree ct -> ct.getModifiers().getAnnotations();
            case VariableTree vt -> vt.getModifiers().getAnnotations();
            case MethodTree mt -> mt.getModifiers().getAnnotations();
            default -> List.of();
        };

        if (annotationTrees.isEmpty()) {
            return mirrors.stream().map(m -> (AnnotationMirror) m).collect(Collectors.toList());
        }

        Map<String, Deque<AnnotationMirror>> mirrorsByType = new HashMap<>();
        for (AnnotationMirror am : mirrors) {
            String topType = am.getAnnotationType().toString();
            mirrorsByType.computeIfAbsent(topType, k -> new ArrayDeque<>()).addLast(am);

            for (Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> ev : am.getElementValues().entrySet()) {
                Object val = ev.getValue().getValue();
                if (val instanceof List<?>) {
                    for (Object inner : (List<?>) val) {
                        if (inner instanceof AnnotationMirror innerAm) {
                            String innerType = innerAm.getAnnotationType().toString();
                            mirrorsByType.computeIfAbsent(innerType, k -> new ArrayDeque<>()).addLast(innerAm);
                        }
                    }
                }
            }
        }

        List<AnnotationMirror> ordered = new ArrayList<>(annotationTrees.size());

        for (AnnotationTree at : annotationTrees) {
            try {
                boolean expanded = false;

                for (ExpressionTree arg : at.getArguments()) {
                    ExpressionTree expr = arg;
                    if (expr instanceof AssignmentTree asg) {
                        expr = asg.getExpression();
                    }

                    if (expr instanceof NewArrayTree newArr) {
                        for (ExpressionTree init : newArr.getInitializers()) {
                            if (init instanceof AnnotationTree innerAt) {
                                TypeMirror innerAnnType = trees.getTypeMirror(new TreePath(elementPath, innerAt.getAnnotationType()));
                                AnnotationMirror matched = null;
                                if (innerAnnType != null) {
                                    for (Deque<AnnotationMirror> dq : mirrorsByType.values()) {
                                        if (dq == null || dq.isEmpty()) continue;
                                        AnnotationMirror candidate = dq.peekFirst();
                                        if (candidate != null && typeUtils.isSameType(candidate.getAnnotationType(), innerAnnType)) {
                                            matched = dq.removeFirst();
                                            break;
                                        }
                                    }
                                }

                                if (matched == null) {
                                    String innerName = innerAt.getAnnotationType().toString();
                                    Deque<AnnotationMirror> dq2 = mirrorsByType.get(innerName);
                                    if (dq2 != null && !dq2.isEmpty()) matched = dq2.removeFirst();
                                }

                                if (matched != null) ordered.add(matched);
                            }
                        }
                        expanded = true;
                        break;
                    }
                }

                if (expanded) continue;

                TypeMirror annType = trees.getTypeMirror(new TreePath(elementPath, at.getAnnotationType()));
                AnnotationMirror matched = null;
                if (annType != null) {
                    for (Deque<AnnotationMirror> dq : mirrorsByType.values()) {
                        if (dq == null || dq.isEmpty()) continue;
                        AnnotationMirror candidate = dq.peekFirst();
                        if (candidate != null && typeUtils.isSameType(candidate.getAnnotationType(), annType)) {
                            matched = dq.removeFirst();
                            break;
                        }
                    }
                }

                if (matched == null) {
                    String annName = at.getAnnotationType().toString();
                    Deque<AnnotationMirror> dq = mirrorsByType.get(annName);
                    if (dq != null && !dq.isEmpty()) matched = dq.removeFirst();
                }

                if (matched != null) ordered.add(matched);
            } catch (Exception ex) {
                ordered.add(null);
            }
        }


        for (Deque<AnnotationMirror> dq : mirrorsByType.values()) {
            while (!dq.isEmpty()) {
                ordered.add(dq.removeFirst());
            }
        }

        return ordered;
    }

    private void println(Object o) {
        processingEnv.getMessager().printNote(o == null ? "null" : o.toString());
    }

    public static class ElementRepresentation {
        public String type;
        public String name;
        public List<AnnotationDTO> annotations = new ArrayList<>();

        public ElementRepresentation(String type, String name) {
            this.type = type;
            this.name = name;
        }
    }

    AnnotationDTO toDTO(AnnotationMirror mirror, Elements elements) {
        AnnotationDTO dto = new AnnotationDTO();
        TypeElement annType = (TypeElement) mirror.getAnnotationType().asElement();
        dto.annotationType = elements.getBinaryName(annType).toString();
        dto.values = new LinkedHashMap<>();

        for (var entry : mirror.getElementValues().entrySet()) {
            String name = entry.getKey().getSimpleName().toString();
            dto.values.put(name, toValueDTO(entry.getValue(), elements));
        }
        return dto;
    }

    ValueDTO toValueDTO(AnnotationValue av, Elements elements) {
        Object v = av.getValue();
        ValueDTO dto = new ValueDTO();

        if (v instanceof String s) {
            dto.kind = "string";
            dto.value = s;
        } else if (v instanceof Integer || v instanceof Long ||
                v instanceof Short   || v instanceof Byte ||
                v instanceof Boolean || v instanceof Character ||
                v instanceof Double  || v instanceof Float) {
            dto.kind = "primitive";
            dto.value = v;
        } else if (v instanceof VariableElement ve) {
            EnumDTO e = new EnumDTO();
            TypeElement enumType = (TypeElement) ve.getEnclosingElement();
            e.enumType = elements.getBinaryName(enumType).toString();
            e.constant = ve.getSimpleName().toString();
            dto.kind = "enum";
            dto.value = e;
        } else if (v instanceof TypeMirror tm) {
            TypeElement type = (TypeElement)tm;
            ClassDTO c = new ClassDTO();
            c.className = elements.getBinaryName(type).toString();
            dto.kind = "class";
            dto.value = c;
        } else if (v instanceof AnnotationMirror nested) {
            dto.kind = "annotation";
            dto.value = toDTO(nested, elements);
        } else if (v instanceof List<?> list) {
            ArrayDTO arr = new ArrayDTO();
            arr.values = new ArrayList<>();
            for (Object o : list) {
                arr.values.add(toValueDTO((AnnotationValue) o, elements));
            }
            dto.kind = "array";
            dto.value = arr;
        } else {
            throw new IllegalStateException("Unknown annotation member type: " + v);
        }
        return dto;
    }

    public static final class AnnotationDTO {
        public String annotationType;
        public Map<String, ValueDTO> values;
    }
    public static final class ValueDTO {
        public String kind;
        public Object value;
    }
    public static class ArrayDTO {
        public List<ValueDTO> values;
    }
    public static class EnumDTO {
        public String enumType;
        public String constant;
    }
    public static class ClassDTO {
        public String className;
    }
}
