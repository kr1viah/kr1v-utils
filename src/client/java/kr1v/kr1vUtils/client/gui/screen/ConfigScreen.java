package kr1v.kr1vUtils.client.gui.screen;

import com.google.common.collect.ImmutableList;
import fi.dy.masa.malilib.config.IConfigBase;
import fi.dy.masa.malilib.gui.GuiConfigsBase;
import fi.dy.masa.malilib.gui.button.ButtonGeneric;
import fi.dy.masa.malilib.util.StringUtils;
import kr1v.kr1vUtils.client.Kr1vUtilsClient;
import kr1v.kr1vUtils.client.config.*;
import kr1v.kr1vUtils.client.malilib.ConfigLabel;
import kr1v.kr1vUtils.client.utils.Annotations;
import net.minecraft.client.gui.DrawContext;

import java.util.List;

public class ConfigScreen extends GuiConfigsBase {
	public static ConfigGuiTab tab = null;

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
			x += this.createButton(x, y, -1, tab);
		}
	}

	@SuppressWarnings("SameParameterValue")
	private int createButton(int x, int y, int width, ConfigGuiTab tab) {
		ButtonGeneric button = new ButtonGeneric(x, y, width, 20, tab.getDisplayName());
		button.setEnabled(ConfigScreen.tab != tab);
		final ConfigGuiTab tab2 = tab;

		this.addButton(button, (button1, mouseButton) -> {
			ConfigScreen.tab = tab2;
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

	public enum ConfigGuiTab {
		CHAT("Chat", Annotations.configsFor(Chat.class)),
		DEBUG("Debug", Annotations.configsFor(Debug.class)),
		KEYS("Keys", Annotations.configsFor(Keys.class)),
		MISC("Misc", Annotations.configsFor(Misc.class)),
		RENDER("Render", Annotations.configsFor(Render.class)),
		SCREEN("Screen", Annotations.configsFor(Screen.class));

		private final String translationKey;
		private final List<? extends IConfigBase> options;

		ConfigGuiTab(String translationKey, List<? extends IConfigBase> options) {
			this.options = options;
			this.translationKey = translationKey;
		}

		public List<? extends IConfigBase> getOptions() {
			return this.options;
		}

		public String getDisplayName() {
			return StringUtils.translate(this.translationKey);
		}
	}
}