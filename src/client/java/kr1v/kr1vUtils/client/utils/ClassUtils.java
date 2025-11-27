package kr1v.kr1vUtils.client.utils;

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
}
