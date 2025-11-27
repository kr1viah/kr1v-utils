package kr1v.kr1vUtils.client.utils;

import kr1v.kr1vUtils.client.utils.annotation.classannotations.Touch;
import org.reflections.Reflections;

import java.util.*;

public final class Annotations {
	private Annotations() {}

	static {
		Reflections reflections = new Reflections();
		{
			Set<Class<?>> touchableClasses = reflections.getTypesAnnotatedWith(Touch.class);
			for (Class<?> cfgClass : touchableClasses) {
				Touch touch = cfgClass.getAnnotation(Touch.class);
				if (touch.async()) {
					new Thread(() -> ClassUtils.touch(cfgClass)).start();
				} else {
					ClassUtils.touch(cfgClass);
				}
			}
		}
	}
}