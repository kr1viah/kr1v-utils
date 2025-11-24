package kr1v.kr1vUtils.client.utils;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import sun.misc.Unsafe;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import java.util.*;
import java.util.stream.Collectors;

public class ClassUtils {
    public static final Gson GSON = new GsonBuilder().registerTypeAdapter(ValueDTO.class, new ValueDTODeserializer()).setPrettyPrinting().create();
    public static final Unsafe unsafe;

    static {
        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            unsafe = (Unsafe) f.get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static void touch(Class<?> clazz) {
		try {
			Class.forName(clazz.getName());
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("What the actual fuck did you do", e);
		}
	}

	public static List<Field> getAllFields(Class<?> clazz) {
		List<Field> output = new ArrayList<>(List.of(clazz.getDeclaredFields()));
		if (clazz != Object.class)
			getAllFields(clazz.getSuperclass(), output);
		return output;
	}

	public static void getAllFields(Class<?> clazz, List<Field> output) {
		output.addAll(List.of(clazz.getDeclaredFields()));
		if (clazz != Object.class)
			getAllFields(clazz.getSuperclass(), output);
	}

    public static List<Element> getDeclaredElements(Class<?> clazz) {
        List<Element> elementsOfClass = new ArrayList<>();
        List<ElementRepresentation> elementRepresentations = getDeclaredElementRepresentationsForClass(clazz);

        try {
            assert elementRepresentations != null;
            for (ElementRepresentation el : elementRepresentations) {
                Field f = null;
                Method m = null;
                Class<?> klass = null;
                switch (el.type) {
                    case "field" -> f = clazz.getField(el.name);
                    case "innerClass" -> klass = Class.forName(el.name);
                    case "method" -> {
                        if (el.name.contains("<")) continue;
                        List<Class<?>> typeList = new ArrayList<>();
                        for (String type : el.types) {
                            typeList.add(Class.forName(type));
                        }
                        Class<?>[] types = typeList.toArray(new Class[]{});

                        m = clazz.getDeclaredMethod(el.name, types);
                    }
                    default -> {}
                }

                Element element = new Element(f, m, klass);
                for (AnnotationDTO ann : el.annotations) {
                    element.annotations.add(toAnnotation(ann, Class.forName(ann.annotationType)));
                }
                elementsOfClass.add(element);
            }
        } catch (NoSuchFieldException | ClassNotFoundException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

        return elementsOfClass;
    }

    public static class Element {
        public final Field field;
        public final Method method;
        public final Class<?> aClass;

        public final List<Annotation> annotations = new ArrayList<>();

        public Element(Field field, Method method, Class<?> aClass) {
            this.field = field;
            this.method = method;
            this.aClass = aClass;
        }
    }

    public static class ElementRepresentation {
        public String type;
        public String name;
        public List<AnnotationDTO> annotations = new ArrayList<>();
        public List<String> types = new ArrayList<>();

        public ElementRepresentation(String type, String name) {
            this.type = type;
            this.name = name;
        }
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

    private static List<ElementRepresentation> getDeclaredElementRepresentationsForClass(Class<?> clazz) {
        try (InputStream in = ClassUtils.class.getClassLoader()
                .getResourceAsStream("META-INF/kr1v/classes.json")) {

            if (in == null) {
                throw new IllegalStateException("classes.json not found on classpath");
            }

            String json = new String(in.readAllBytes(), StandardCharsets.UTF_8);

            Type type = new TypeToken<Map<String, List<ElementRepresentation>>>(){}.getType();
            Map<String, List<ElementRepresentation>> data =
                    GSON.fromJson(json, type);
            return data.get(clazz.getName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public static <A extends Annotation> A toAnnotation(AnnotationDTO dto, Class<?> annotationClass) throws ClassNotFoundException {
        if (dto == null) throw new IllegalArgumentException("dto == null");
        if (annotationClass == null) throw new IllegalArgumentException("annotationClass == null");
        if (!annotationClass.isAnnotation()) throw new IllegalArgumentException(annotationClass + " is not an annotation type");

        Map<String, Object> values = new LinkedHashMap<>();
        ClassLoader cl = annotationClass.getClassLoader();

        for (Method m : annotationClass.getDeclaredMethods()) {
            String name = m.getName();
            ValueDTO vdto = dto.values == null ? null : dto.values.get(name);
            Object value;
            if (vdto != null) {
                value = convertValue(vdto, m.getReturnType(), cl);
            } else {
                Object def = m.getDefaultValue();
                if (def != null) {
                    value = def;
                } else {
                    throw new IllegalArgumentException("No value provided for annotation member '" + name + "' of " + annotationClass);
                }
            }
            values.put(name, value);
        }

        InvocationHandler handler = new AnnotationHandler((Class<? extends Annotation>) annotationClass, values);
        return (A) Proxy.newProxyInstance(cl, new Class[]{annotationClass, Annotation.class}, handler);
    }

    private static Object convertValue(ValueDTO vdto, Class<?> expectedType, ClassLoader cl) throws ClassNotFoundException {
        if (vdto == null) return null;
        String kind = vdto.kind;
        Object raw = vdto.value;

        if (expectedType.isArray()) {
            if (!"array".equals(kind)) throw new IllegalArgumentException("Expected array for " + expectedType + " but got " + kind);
            ArrayDTO arr = (ArrayDTO) raw;
            Class<?> comp = expectedType.getComponentType();
            Object array = Array.newInstance(comp, arr.values.size());
            for (int i = 0; i < arr.values.size(); i++) {
                ValueDTO elem = arr.values.get(i);
                Object converted = convertValue(elem, comp, cl);
                Array.set(array, i, converted);
            }
            return array;
        }

        switch (kind) {
            case "string":
                return raw;
            case "primitive":
                return coercePrimitive(raw, expectedType);
            case "enum": {
                EnumDTO e = (EnumDTO) raw;
                Class<?> enumClazz = Class.forName(e.enumType, false, cl);
                @SuppressWarnings({"rawtypes", "unchecked"})
                Enum<?> enumVal = Enum.valueOf((Class) enumClazz, e.constant);
                return enumVal;
            }
            case "class": {
                ClassDTO c = (ClassDTO) raw;
                return Class.forName(c.className, false, cl);
            }
            case "annotation": {
                AnnotationDTO nested = (AnnotationDTO) raw;
                // expectedType should be an annotation type
                @SuppressWarnings("unchecked")
                Class<? extends Annotation> annType = (Class<? extends Annotation>) Class.forName(nested.annotationType, false, cl);
                return toAnnotation(nested, annType);
            }
            case "array":
                ArrayDTO arr = (ArrayDTO) raw;
                Object[] out = new Object[arr.values.size()];
                for (int i = 0; i < arr.values.size(); i++) out[i] = convertValue(arr.values.get(i), Object.class, cl);
                return out;
            default:
                throw new IllegalArgumentException("Unknown ValueDTO.kind: " + kind);
        }
    }

    private static Object coercePrimitive(Object raw, Class<?> expectedType) {
        if (expectedType == String.class) return String.valueOf(raw);
        if (expectedType == boolean.class || expectedType == Boolean.class) return raw;
        if (expectedType == byte.class || expectedType == Byte.class) return ((Number) raw).byteValue();
        if (expectedType == short.class || expectedType == Short.class) return ((Number) raw).shortValue();
        if (expectedType == int.class || expectedType == Integer.class) return ((Number) raw).intValue();
        if (expectedType == long.class || expectedType == Long.class) return ((Number) raw).longValue();
        if (expectedType == float.class || expectedType == Float.class) return ((Number) raw).floatValue();
        if (expectedType == double.class || expectedType == Double.class) return ((Number) raw).doubleValue();
        if (expectedType == char.class || expectedType == Character.class) {
            if (raw instanceof Character) return raw;
            String s = String.valueOf(raw);
            if (s.isEmpty()) return '\u0000';
            return s.charAt(0);
        }
        return raw;
    }

    private static final class AnnotationHandler implements InvocationHandler {
        private final Class<? extends Annotation> type;
        private final Map<String, Object> values; // member name -> value

        AnnotationHandler(Class<? extends Annotation> type, Map<String, Object> values) {
            this.type = type;
            this.values = Collections.unmodifiableMap(new LinkedHashMap<>(values));
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) {
            String name = method.getName();
            if (name.equals("annotationType") && method.getParameterCount() == 0) return type;
            if (name.equals("toString") && method.getParameterCount() == 0) return buildToString();
            if (name.equals("hashCode") && method.getParameterCount() == 0) return buildHashCode();
            if (name.equals("equals") && method.getParameterCount() == 1) return buildEquals(args[0]);

            // annotation member accessor
            if (values.containsKey(name)) return values.get(name);
            throw new IllegalStateException("Unknown method on annotation proxy: " + method);
        }

        private boolean buildEquals(Object other) {
            if (other == this) return true;
            if (!(other instanceof Annotation oAnn)) return false;
            if (!oAnn.annotationType().equals(type)) return false;

            for (Method m : type.getDeclaredMethods()) {
                try {
                    Object v1 = values.get(m.getName());
                    Object v2 = m.invoke(oAnn);
                    if (!memberEquals(v1, v2)) return false;
                } catch (Exception ex) {
                    return false;
                }
            }
            return true;
        }

        private int buildHashCode() {
            int result = 0;
            for (Method m : type.getDeclaredMethods()) {
                Object v = values.get(m.getName());
                int nameHash = 127 * m.getName().hashCode();
                int valueHash = memberHash(v);
                result += (nameHash ^ valueHash);
            }
            return result;
        }

        private String buildToString() {
            String members = Arrays.stream(type.getDeclaredMethods())
                    .map(m -> {
                        Object v = values.get(m.getName());
                        return m.getName() + "=" + memberToString(v);
                    })
                    .collect(Collectors.joining(", "));
            return "@" + type.getName() + "(" + members + ")";
        }

        private static boolean memberEquals(Object a, Object b) {
            if (a == null) return b == null;
            if (a.getClass().isArray()) {
                if (b == null || !b.getClass().isArray()) return false;
                return deepArrayEquals(a, b);
            }
            return Objects.equals(a, b);
        }

        private static boolean deepArrayEquals(Object a, Object b) {
            Class<?> ac = a.getClass().getComponentType();
            if (ac.isPrimitive()) {
                return switch (a) {
                    case int[] ints -> Arrays.equals(ints, (int[]) b);
                    case long[] longs -> Arrays.equals(longs, (long[]) b);
                    case short[] shorts -> Arrays.equals(shorts, (short[]) b);
                    case byte[] bytes -> Arrays.equals(bytes, (byte[]) b);
                    case char[] chars -> Arrays.equals(chars, (char[]) b);
                    case boolean[] booleans -> Arrays.equals(booleans, (boolean[]) b);
                    case float[] floats -> Arrays.equals(floats, (float[]) b);
                    case double[] doubles -> Arrays.equals(doubles, (double[]) b);
                    default -> false;
                };
            }
            return Arrays.deepEquals((Object[]) a, (Object[]) b);
        }

        private static int memberHash(Object v) {
            if (v == null) return 0;
            if (v.getClass().isArray()) {
                Class<?> comp = v.getClass().getComponentType();
                if (comp.isPrimitive()) {
                    switch (v) {
                        case int[] ints -> {
                            return Arrays.hashCode(ints);
                        }
                        case long[] longs -> {
                            return Arrays.hashCode(longs);
                        }
                        case short[] shorts -> {
                            return Arrays.hashCode(shorts);
                        }
                        case byte[] bytes -> {
                            return Arrays.hashCode(bytes);
                        }
                        case char[] chars -> {
                            return Arrays.hashCode(chars);
                        }
                        case boolean[] booleans -> {
                            return Arrays.hashCode(booleans);
                        }
                        case float[] floats -> {
                            return Arrays.hashCode(floats);
                        }
                        case double[] doubles -> {
                            return Arrays.hashCode(doubles);
                        }
                        default -> {
                        }
                    }
                } else {
                    return Arrays.deepHashCode((Object[]) v);
                }
            }
            return Objects.hashCode(v);
        }

        private static String memberToString(Object v) {
            if (v == null) return "null";
            if (v.getClass().isArray()) {
                Class<?> comp = v.getClass().getComponentType();
                if (comp.isPrimitive()) {
                    switch (v) {
                        case int[] ints -> {
                            return Arrays.toString(ints);
                        }
                        case long[] longs -> {
                            return Arrays.toString(longs);
                        }
                        case short[] shorts -> {
                            return Arrays.toString(shorts);
                        }
                        case byte[] bytes -> {
                            return Arrays.toString(bytes);
                        }
                        case char[] chars -> {
                            return Arrays.toString(chars);
                        }
                        case boolean[] booleans -> {
                            return Arrays.toString(booleans);
                        }
                        case float[] floats -> {
                            return Arrays.toString(floats);
                        }
                        case double[] doubles -> {
                            return Arrays.toString(doubles);
                        }
                        default -> {
                        }
                    }
                }
                return Arrays.toString((Object[]) v);
            }
            return String.valueOf(v);
        }
    }

    static final class ValueDTODeserializer implements JsonDeserializer<ValueDTO> {
        @Override
        public ValueDTO deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext ctx) throws JsonParseException {
            JsonObject obj = json.getAsJsonObject();
            String kind = obj.has("kind") ? obj.get("kind").getAsString() : null;
            ValueDTO v = new ValueDTO();
            v.kind = kind;

            if (!obj.has("value") || obj.get("value").isJsonNull()) {
                v.value = null;
                return v;
            }

            JsonElement valueElem = obj.get("value");

            try {
                switch (kind) {
                    case "string" -> v.value = valueElem.getAsString();
                    case "primitive" -> {
                        JsonPrimitive p = valueElem.getAsJsonPrimitive();
                        if (p.isBoolean()) v.value = p.getAsBoolean();
                        else if (p.isNumber()) {
                            v.value = p.getAsNumber();
                        } else {
                            v.value = p.getAsString();
                        }
                    }
                    case "enum" -> v.value = ctx.deserialize(valueElem, EnumDTO.class);
                    case "class" -> v.value = ctx.deserialize(valueElem, ClassDTO.class);
                    case "annotation" -> v.value = ctx.deserialize(valueElem, AnnotationDTO.class);
                    case "array" -> v.value = ctx.deserialize(valueElem, ArrayDTO.class);
                    default -> v.value = ctx.deserialize(valueElem, Object.class);
                }
            } catch (JsonParseException ex) {
                throw ex;
            } catch (Exception ex) {
                throw new JsonParseException("Failed to deserialize ValueDTO kind=" + kind, ex);
            }
            return v;
        }
    }
}
