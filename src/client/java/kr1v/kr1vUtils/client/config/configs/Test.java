package kr1v.kr1vUtils.client.config.configs;

import fi.dy.masa.malilib.config.IConfigBase;
import kr1v.kr1vUtils.client.malilib.ConfigLabel;
import kr1v.kr1vUtils.client.utils.annotation.classannotations.Config;
import kr1v.kr1vUtils.client.utils.annotation.classannotations.PopupConfig;
import kr1v.kr1vUtils.client.utils.annotation.fieldannotations.Label;
import kr1v.kr1vUtils.client.utils.annotation.fieldannotations.Marker;
import kr1v.kr1vUtils.client.utils.annotation.methodannotations.Extras;
import kr1v.kr1vUtils.client.utils.malilib.plus.ConfigBooleanPlus;
import kr1v.kr1vUtils.client.utils.malilib.plus.ConfigIntegerPlus;
import kr1v.kr1vUtils.client.utils.malilib.plus.ConfigStringListPlus;
import kr1v.kr1vUtils.client.utils.malilib.plus.ConfigStringPlus;

import java.util.List;

@Config
public class Test {
    @Label("Label above class")
    @PopupConfig
    public static class Popup {
        @Extras
        private static void moreCustom(List<IConfigBase> existing) {
            for (int i = 0; i < 4; i++) {
                existing.add(new ConfigStringListPlus("String list " + i));
            }
        }
    }

    @Label("Label above method")
    @Extras
    private static void addCustom(List<IConfigBase> existing) {
        existing.add(new ConfigBooleanPlus("Programmatically add new ones"));
        for (int i = 0; i < 6; i++) {
            existing.add(new ConfigLabel("" + i));
        }
    }

    public static final ConfigStringPlus STRING = new ConfigStringPlus("String");
    @Label("Labels and markers mixed in")
    @Marker("Marker name")
    @Label("Another label sandwiching the marker")
    public static final ConfigIntegerPlus INT = new ConfigIntegerPlus("Integer");

    @Extras(runAt = "Marker name")
    private static void addMoreCustom(List<IConfigBase> existing) {
        existing.add(new ConfigStringPlus("In between the labels"));
    }
}
