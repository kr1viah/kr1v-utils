package kr1v.kr1vUtils.client.utils.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Config {
    String name() default "";
    boolean defaultEnabled() default true;
}