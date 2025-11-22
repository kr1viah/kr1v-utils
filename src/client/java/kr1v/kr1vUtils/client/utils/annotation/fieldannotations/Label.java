package kr1v.kr1vUtils.client.utils.annotation.fieldannotations;

import java.lang.annotation.*;

/**
 * Adds a label above this config
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD, ElementType.FIELD, ElementType.TYPE})
@Repeatable(Labels.class)
public @interface Label {
    String value() default "";
}