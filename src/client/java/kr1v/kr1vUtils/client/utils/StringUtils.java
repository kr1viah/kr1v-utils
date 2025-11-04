package kr1v.kr1vUtils.client.utils;

import java.util.Arrays;
import java.util.stream.Collectors;

public class StringUtils {
	public static String convertCamelCase(String snake) {
		if (snake == null || snake.isEmpty())
			return snake;
		return Arrays.stream(snake.split("_"))
			.filter(s -> !s.isEmpty())
			.map(s -> s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase())
			.collect(Collectors.joining(" "));
	}
}
