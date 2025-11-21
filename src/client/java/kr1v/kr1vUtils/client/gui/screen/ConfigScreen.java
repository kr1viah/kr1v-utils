package kr1v.kr1vUtils.client.gui.screen;

import com.google.common.collect.ImmutableList;
import fi.dy.masa.malilib.config.IConfigBase;
import fi.dy.masa.malilib.gui.GuiConfigsBase;
import fi.dy.masa.malilib.gui.button.ButtonGeneric;
import fi.dy.masa.malilib.util.StringUtils;
import kr1v.kr1vUtils.client.Kr1vUtilsClient;
import kr1v.kr1vUtils.client.config.configs.Misc;
import kr1v.kr1vUtils.client.malilib.ConfigLabel;
import kr1v.kr1vUtils.client.utils.Annotations;
import kr1v.kr1vUtils.client.utils.annotation.PopupConfig;
import net.minecraft.client.gui.DrawContext;

import java.util.ArrayList;
import java.util.List;

public class ConfigScreen extends GuiConfigsBase {
    public static ConfigGuiTab tab = null;

    public static void setTab(ConfigGuiTab tab) {
        ConfigScreen.tab = tab;
    }

	public ConfigScreen() {
		super(10, 50, Kr1vUtilsClient.MOD_ID, null, "Configs", "0.0.0");
	}

	@Override
	public void initGui() {
		super.initGui();
		this.clearOptions();

		int x = 10;
		int y = 26;

		for (ConfigGuiTab tab : ConfigGuiTab.values()) {
            if (!tab.isPopup)
                x += this.createButton(x, y, -1, tab);
		}
	}

	@SuppressWarnings("SameParameterValue")
	private int createButton(int x, int y, int width, ConfigGuiTab tab) {
		ButtonGeneric button = new ButtonGeneric(x, y, width, 20, tab.getDisplayName());
		button.setEnabled(ConfigScreen.tab != tab);
		final ConfigGuiTab tab2 = tab;

		this.addButton(button, (button1, mouseButton) -> {
			ConfigScreen.setTab(tab2);
			reCreateListWidget(); // apply the new config width
			initGui();
		});

		return button.getWidth() + 2;
	}

	@Override
	protected void reCreateListWidget() {
		super.reCreateListWidget();
		if (getListWidget() != null)
			getListWidget().getScrollbar().setValue(Misc.tabToScrollPosition.getOrDefault(ConfigScreen.tab.toString(),0));
	}

	@Override
	protected void closeGui(boolean showParent) {
		super.closeGui(showParent);
	}

	@Override
	protected int getConfigWidth() {
		return this.width / 2;
	}

	@Override
	public List<ConfigOptionWrapper> getConfigs() {
		ImmutableList.Builder<ConfigOptionWrapper> builder = ImmutableList.builder();
		for (IConfigBase config : ConfigScreen.tab.getOptions()) {
			if (config instanceof ConfigLabel)
				builder.add(new ConfigOptionWrapper(config.getComment()));
			else
				builder.add(new ConfigOptionWrapper(config));
		}
		return builder.build();
	}

	@Override
	public void render(DrawContext drawContext, int mouseX, int mouseY, float partialTicks) {
		if (this.client != null && this.client.world == null) this.renderPanoramaBackground(drawContext, partialTicks);
		this.applyBlur();
		super.render(drawContext, mouseX, mouseY, partialTicks);
	}

	public static class ConfigGuiTab {
		private final String translationKey;
		private final List<? extends IConfigBase> options;
        private static ConfigGuiTab[] values;
        public final boolean isPopup;

        ConfigGuiTab(String translationKey, List<? extends IConfigBase> options, boolean isPopup) {
			this.options = options;
			this.translationKey = translationKey;
            this.isPopup = isPopup;
		}

        public static ConfigGuiTab[] values() {
            if (values == null) {
                List<ConfigGuiTab> valuesList = new ArrayList<>();
                for (Class<?> clazz : Annotations.CACHE.keySet()) {
                    if (clazz.isAnnotationPresent(PopupConfig.class)) {
                        valuesList.add(new ConfigGuiTab(Annotations.nameForConfig(clazz), Annotations.configsFor(clazz), true));
                    } else {
                        valuesList.add(new ConfigGuiTab(Annotations.nameForConfig(clazz), Annotations.configsFor(clazz), false));
                    }
                }
                values = valuesList.toArray(new ConfigGuiTab[0]);
            }
            return values;
        }

        public static ConfigGuiTab valueOf(String lastTab) {
            for (ConfigGuiTab tab : values()) {
                if (tab.translationKey.equals(lastTab)) return tab;
            }
            System.out.println(lastTab);
            return values()[0];
//            throw new RuntimeException("No such tab exists!");
        }

        public List<? extends IConfigBase> getOptions() {
			return this.options;
		}

		public String getDisplayName() {
			return StringUtils.translate(this.translationKey);
		}
	}
}