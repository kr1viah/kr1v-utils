package kr1v.kr1vUtils.client.malilib.event;

import fi.dy.masa.malilib.config.IConfigBase;
import fi.dy.masa.malilib.hotkeys.*;
import kr1v.kr1vUtils.client.Kr1vUtilsClient;
import kr1v.kr1vUtils.client.gui.screen.ConfigScreen;

import java.util.ArrayList;
import java.util.List;

public class InputHandler implements IKeybindProvider, IKeyboardInputHandler, IMouseInputHandler {
    private static final InputHandler INSTANCE = new InputHandler();

    private InputHandler() {
        super();
    }

    public static InputHandler getInstance() {
        return INSTANCE;
    }

    @Override
    public void addKeysToMap(IKeybindManager manager) {
        for (ConfigScreen.ConfigGuiTab config : ConfigScreen.ConfigGuiTab.values()) {
            for (IConfigBase option : config.getOptions()) {
                if (option instanceof IHotkey hotkey) {
                    manager.addKeybindToMap(hotkey.getKeybind());
                }
            }
        }
    }

    @Override
    public void addHotkeys(IKeybindManager manager) {
        for (ConfigScreen.ConfigGuiTab config : ConfigScreen.ConfigGuiTab.values()) {
            List<IHotkey> hotkeys = new ArrayList<>();
            for (IConfigBase option : config.getOptions()) {
                if (option instanceof IHotkey hotkey) {
                    hotkeys.add(hotkey);
                }
            }
            manager.addHotkeysForCategory(Kr1vUtilsClient.MOD_ID, config.getDisplayName(), hotkeys);
        }
    }
}