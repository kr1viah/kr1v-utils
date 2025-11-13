package kr1v.kr1vUtils.client.utils;

import sun.misc.Unsafe;

import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ClassUtils {
	public static void touch(Class<?>... classes) {
		for (Class<?> clazz : classes) {
			touch(clazz);
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
