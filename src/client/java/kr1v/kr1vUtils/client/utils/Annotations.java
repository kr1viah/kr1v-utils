package kr1v.kr1vUtils.client.utils;

import kr1v.kr1vUtils.client.utils.annotation.classannotations.Touch;
import kr1v.malilibApi.InternalMalilibApi;

import java.util.*;

public final class Annotations {
	private Annotations() {}

	static {
        Set<Class<?>> touchableClasses = InternalMalilibApi.reflections.getTypesAnnotatedWith(Touch.class);
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