package kr1v.kr1vUtils.client.utils.annotation.classannotations;

import java.lang.annotation.*;

/**
 * Marks this class as a config class.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Config {
    String name() default "";
    boolean defaultEnabled() default true;
}