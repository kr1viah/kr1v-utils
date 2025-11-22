package kr1v.kr1vUtils.client.utils;

import fi.dy.masa.malilib.config.IConfigBase;
import kr1v.kr1vUtils.client.config.ConfigHandler;
import kr1v.kr1vUtils.client.utils.annotation.classannotations.Config;
import kr1v.kr1vUtils.client.utils.annotation.classannotations.PopupConfig;
import kr1v.kr1vUtils.client.utils.annotation.classannotations.Touch;
import kr1v.kr1vUtils.client.utils.malilib.plus.ConfigBooleanPlus;
import org.reflections.Reflections;

import java.util.*;

public final class Annotations {
	private Annotations() {}

	public static final Map<Class<?>, List<IConfigBase>> CACHE = new TreeMap<>(Comparator.comparing(Annotations::nameForConfig));

	static {
		Reflections reflections = new Reflections();
		{
			Set<Class<?>> configTypes = reflections.getTypesAnnotatedWith(Config.class);

			for (Class<?> cfgClass : configTypes) {
                ConfigBooleanPlus.defaultEnabled = cfgClass.getAnnotation(Config.class).defaultEnabled();
				List<IConfigBase> list = ConfigHandler.generateOptions(cfgClass);
                ConfigBooleanPlus.defaultEnabled = true;
                CACHE.put(cfgClass, list);
			}
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

    public static String nameForConfig(Class<?> configClass) {
        String name;
        if (configClass.isAnnotationPresent(PopupConfig.class)) {
            name = configClass.getAnnotation(PopupConfig.class).name();
        } else {
            name = configClass.getAnnotation(Config.class).name();
        }
        if (name.isEmpty()) name = configClass.getSimpleName();
        return name;
    }
}