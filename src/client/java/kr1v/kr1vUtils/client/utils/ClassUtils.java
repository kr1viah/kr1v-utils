package kr1v.kr1vUtils.client.utils;

import kr1v.processor.ConfigProcessor;
import sun.misc.Unsafe;

import java.lang.reflect.*;

import java.util.*;

public class ClassUtils {
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

    public static List<ConfigProcessor.Element> getDeclaredElements(Class<?> clazz) {
        List<ConfigProcessor.Element> elementsOfClass = new ArrayList<>();
        List<ConfigProcessor.ElementRepresentation> elementRepresentations = ConfigProcessor.getDeclaredElementRepresentationsForClass(clazz);

        try {
            assert elementRepresentations != null;
            for (ConfigProcessor.ElementRepresentation el : elementRepresentations) {
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

                ConfigProcessor.Element element = new ConfigProcessor.Element(f, m, klass);
                for (ConfigProcessor.AnnotationDTO ann : el.annotations) {
                    element.annotations.add(ConfigProcessor.toAnnotation(ann, Class.forName(ann.annotationType)));
                }
                elementsOfClass.add(element);
            }
        } catch (NoSuchFieldException | ClassNotFoundException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

        return elementsOfClass;
    }
}
