package kr1v.kr1vUtils.client.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ClassUtils {
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
