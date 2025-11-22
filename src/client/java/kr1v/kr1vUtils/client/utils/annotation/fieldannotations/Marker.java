package kr1v.kr1vUtils.client.utils.annotation.fieldannotations;

import java.lang.annotation.*;
import kr1v.kr1vUtils.client.utils.annotation.methodannotations.Extras;

/**
 * Marks a point in the config. See {@link Extras}
 *
 * Should not be used in combination with Labels.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Marker {
    String value() default "";
}