package kr1v.kr1vUtils.client.utils;

import fi.dy.masa.malilib.config.IConfigBase;
import kr1v.kr1vUtils.client.config.ConfigHandler;
import kr1v.kr1vUtils.client.utils.annotation.Config;
import kr1v.kr1vUtils.client.utils.annotation.Touch;
import org.reflections.Reflections;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class Annotations {
	private Annotations() {}

	private static final Map<Class<?>, List<IConfigBase>> CACHE;

	static {
		Reflections reflections = new Reflections();
		{
			Map<Class<?>, List<IConfigBase>> result = new HashMap<>();
			Set<Class<?>> configTypes = reflections.getTypesAnnotatedWith(Config.class);
			for (Class<?> cfgClass : configTypes) {
				List<IConfigBase> list = ConfigHandler.generateOptions(cfgClass);
				result.put(cfgClass, list);
			}
			CACHE = result;
		}
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

	public static List<IConfigBase> configsFor(Class<?> configClass) {
		return CACHE.get(configClass);
	}
}