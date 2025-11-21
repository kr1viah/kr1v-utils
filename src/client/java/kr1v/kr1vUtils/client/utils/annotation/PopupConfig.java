package kr1v.kr1vUtils.client.utils.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface PopupConfig {
    String name() default "";
    String buttonName() default "";
    boolean defaultEnabled() default true;
    int width() default 400;
    int height() default 300;
    int distanceFromSides() default -1; // if this is set, width is ignored
    int distanceFromTops() default -1; // if this is set, height is ignored
}