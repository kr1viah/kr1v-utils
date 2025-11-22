package kr1v.kr1vUtils.client.utils.annotation.methodannotations;

import kr1v.kr1vUtils.client.utils.annotation.classannotations.Config;
import kr1v.kr1vUtils.client.utils.annotation.classannotations.PopupConfig;
import kr1v.kr1vUtils.client.utils.annotation.fieldannotations.Label;
import kr1v.kr1vUtils.client.utils.annotation.fieldannotations.Marker;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Adds entries to the config list.
 * <p>
 * For example, the code below:
 * <pre>{@code
 * @Extras
 * public static void addTests(List<IConfigBase> existingList) {
 * 	for (int i = 0; i < 6; i++) {
 * 		existingList.add(new ConfigBooleanPlus("Test! " + i));
 * 	}
 * }
 * }</pre>
 * Will add 5 ConfigBooleanPlus entries to the end.<br>
 * Must be inside a {@link Config} or {@link PopupConfig} class.
 * <p>
 * if {@code runAt} is set, it will look for all {@link Marker}s with the same value, and will run there.
 * <p>
 * if {@code runBeforeLabel} is set, it will look for all {@link Label}s with the same value and will run before them.
 * <p>
 * if {@code runAfterLabel} is set, it will look for all {@link Label}s with the same value and will run after them.
 * <p>
 * if none are set, will run at the end.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Extras {
    String runAt() default "";
    String runAfterLabel() default "";
    String runBeforeLabel() default "";
}