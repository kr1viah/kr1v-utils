//package kr1v.kr1vUtils.client.utils.malilib;
//
//import fi.dy.masa.malilib.config.IConfigBoolean;
//import fi.dy.masa.malilib.config.options.ConfigColor;
//import fi.dy.masa.malilib.util.data.Color4f;
//import kr1v.kr1vUtils.client.config.ConfigHandler;
//
//public class ConfigColorPlus extends ConfigColor implements Plus {
//    @Override
//    public boolean shouldHandle() {
//        if (this instanceof IConfigBoolean configBoolean && !configBoolean.getBooleanValue()) {
//            return false;
//        }
//        if (ConfigHandler.dependantOns.containsKey(this)) {
//            String[] dependencyNames = ConfigHandler.dependantOns.get(this);
//            for (String dependencyName : dependencyNames) {
//                ConfigBooleanPlus dependencyConfig = ConfigHandler.dependencies.get(dependencyName);
//                if (!dependencyConfig.getBooleanValue()) {
//                    return false;
//                }
//            }
//        }
//        return true;
//    }
//
//    public ConfigColorPlus(String name) {
//        super(name, new Color4f(0, 0, 0, 0));
//    }
//
//    public ConfigColorPlus(String name, Color4f defaultValue) {
//        super(name, defaultValue);
//    }
//
//    public ConfigColorPlus(String name, String defaultValue) {
//        super(name, defaultValue);
//    }
//
//    public ConfigColorPlus(String name, String defaultValue, String comment) {
//        super(name, defaultValue, comment);
//    }
//
//    public ConfigColorPlus(String name, String defaultValue, String comment, String prettyName) {
//        super(name, defaultValue, comment, prettyName);
//    }
//
//    public ConfigColorPlus(String name, String defaultValue, String comment, String prettyName, String translatedName) {
//        super(name, defaultValue, comment, prettyName, translatedName);
//    }
//}
