package kr1v.kr1vUtils.client.utils.annotation.fieldannotations;

import java.lang.annotation.*;
import kr1v.kr1vUtils.client.utils.annotation.methodannotations.Extras;

/**
 * Marks a point in the config. See {@link Extras}
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.TYPE})
public @interface Marker {
    String value() default "";
}