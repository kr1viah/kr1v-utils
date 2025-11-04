package kr1v.kr1vUtils.client.gui.screen;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.text.Style;

public class DummyScreen extends ChatScreen {
	public DummyScreen() {
		super("");
		client = MinecraftClient.getInstance();
		if (this.client.player == null) this.client.setScreen(null);
	}

	@Override
	public void init() {
		if (this.client.player == null) return;
		super.init();
	}

	@Override
	protected void addScreenNarrations(NarrationMessageBuilder messageBuilder) {
		if (this.client.player == null) return;
		super.addScreenNarrations(messageBuilder);
	}

	@Override
	protected void setInitialFocus() {
		if (this.client.player == null) return;
		super.setInitialFocus();
	}

	@Override
	public void render(DrawContext context, int mouseX, int mouseY, float deltaTicks) {
		this.client.inGameHud.getChatHud().render(context, this.client.inGameHud.getTicks(), mouseX, mouseY, true);
	}

	@Override
	public boolean mouseClicked(double mouseX, double mouseY, int button) {
		if (button == 0) {
			ChatHud chatHud = this.client.inGameHud.getChatHud();
			if (chatHud.mouseClicked(mouseX, mouseY)) {
				return true;
			}

			Style style = this.client.inGameHud.getChatHud().getTextStyleAt(mouseX, mouseY);
			if (style != null && this.handleTextClick(style)) {
				return true;
			}
		}
		this.client.setScreen(null);
		return true;
	}
}
