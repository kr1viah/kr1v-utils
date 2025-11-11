package kr1v.kr1vUtils.client.utils.annotation;

import java.lang.annotation.*;

/**
 * Adds a label above this config
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Repeatable(Labels.class)
public @interface Label {
    String value() default "";
}