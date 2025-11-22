package kr1v.processor;

import com.google.auto.service.AutoService;
import com.sun.source.tree.*;
import com.sun.source.util.TreePath;
import com.sun.source.util.Trees;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import java.util.*;
import java.util.stream.Collectors;


@SupportedSourceVersion(SourceVersion.RELEASE_21)
@SupportedAnnotationTypes({"kr1v.kr1vUtils.client.utils.annotation.classannotations.Config",
        "kr1v.kr1vUtils.client.utils.annotation.classannotations.PopupConfig"})
@AutoService(Processor.class)
public class ConfigProcessor extends AbstractProcessor {
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
            println(fqcn);

            TreePath typePath = trees.getPath(typeElement);

            ClassTree classTree = (ClassTree) typePath.getLeaf();
            List<? extends Tree> members = classTree.getMembers();

            for (Tree memberTree : members) {
                javax.lang.model.element.Element memberElement = trees.getElement(new TreePath(typePath, memberTree));

                if (memberElement == null) {
                    continue;
                }

                List<? extends AnnotationMirror> annotations = getAnnotations(memberElement);

                switch (memberElement) {
                    case VariableElement field -> {
                        for (AnnotationMirror annotation : annotations) {
                            println(annotation);
                        }
                        println(field.getSimpleName());
                    }
                    case ExecutableElement method -> {
                        for (AnnotationMirror annotation : annotations) {
                            println(annotation);
                        }
                        println(method.getSimpleName());
                    }
                    case TypeElement inner -> {
                        for (AnnotationMirror annotation : annotations) {
                            println(annotation);
                        }
                        println(inner.getSimpleName());
                    }
                    default -> {}
                }
                println("");
            }
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
}
