package kr1v.kr1vUtils.client.utils.annotation;

import java.lang.annotation.*;

/**
 * Generates a list of configs based on the public static finals the annotated class
 * As a side effect also loads this class at mod init
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Config {
    String name() default "";
    boolean defaultEnabled() default true;
}