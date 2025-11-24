package kr1v.kr1vUtils.client.mixin.gui;

import kr1v.kr1vUtils.client.config.configs.Misc;
import net.minecraft.SharedConstants;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.screen.multiplayer.ConnectScreen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerServerListWidget;
import net.minecraft.client.network.ServerAddress;
import net.minecraft.client.network.ServerInfo;
import net.minecraft.client.option.ServerList;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Mixin(TitleScreen.class)
public abstract class TitleScreenMixin extends Screen {
	@Shadow
	public abstract void tick();

	@Unique
	private final List<MultiplayerServerListWidget.ServerEntry> serverEntries = new ArrayList<>();

	protected TitleScreenMixin(Text title) {
		super(title);
	}

	@ModifyVariable(method = "<init>(ZLnet/minecraft/client/gui/LogoDrawer;)V", at = @At("HEAD"), ordinal = 0, argsOnly = true)
	private static boolean modifyInitArg(boolean original) {
		return !Misc.FAST_MAIN_MENU.getBooleanValue() && original;
	}

	@Inject(method = "init", at = @At("TAIL"))
	private void injected(CallbackInfo ci) {
		serverEntries.clear();

		MultiplayerScreen dummyScreen = new MultiplayerScreen(MinecraftClient.getInstance().currentScreen);
		dummyScreen.client = MinecraftClient.getInstance();
		ServerList dummyList = new ServerList(MinecraftClient.getInstance());
		dummyList.loadFile();
		MultiplayerServerListWidget dummyWidget = new MultiplayerServerListWidget(dummyScreen, MinecraftClient.getInstance(), 0, 0, 0, 0);
		dummyScreen.serverList = dummyList;

		for (String server : Misc.QUICKPLAY_SERVERS.getStrings()) {
			String ip = Arrays.asList(server.split("#")).getLast();
			String name = Arrays.asList(server.split("#")).getFirst();
			if (this.textRenderer.getWidth(name) > 64) {
				while (this.textRenderer.getWidth(name) > 60) {
					name = name.substring(0, name.length() - 1);
				}
				name += "...";
			}
            MultiplayerServerListWidget.ServerEntry temp = dummyWidget.new ServerEntry(dummyScreen, new ServerInfo(name, ip, ServerInfo.ServerType.OTHER));
			serverEntries.add(temp);
		}
	}

	@Inject(method = "mouseClicked", at = @At("HEAD"), cancellable = true)
	private void mouseClickedInjected(double mouseX, double mouseY, int button, CallbackInfoReturnable<Boolean> cir) {
		if (button != 0) {
			return;
		}
		for (MultiplayerServerListWidget.ServerEntry entry : serverEntries) {
			int index = serverEntries.indexOf(entry);
			int x = this.width / 2 + 33 * index - 33 * serverEntries.size() / 2;
			if (mouseX >= x && mouseX <= x + 32 && mouseY >= (double) this.height / 4 - 5 && mouseY <= (double) this.height / 4 + 32 - 5) {
				//noinspection DataFlowIssue
				ConnectScreen.connect(this, this.client, ServerAddress.parse(entry.server.address), entry.server, false, null);
				cir.setReturnValue(true);
				return;
			}
		}
	}

	@Inject(method = "render", at = @At("TAIL"))
	private void renderInjected(DrawContext context, int mouseX, int mouseY, float deltaTicks, CallbackInfo ci) {
		for (int i = 0; i < serverEntries.size(); i++) {
			MultiplayerServerListWidget.ServerEntry serverEntry = serverEntries.get(i);
			serverEntry.update();
			MinecraftClient client = MinecraftClient.getInstance();
			if (serverEntry.server.getStatus() == ServerInfo.Status.INITIAL) {
				serverEntry.server.setStatus(ServerInfo.Status.PINGING);
				serverEntry.server.label = ScreenTexts.EMPTY;
				serverEntry.server.playerCountLabel = ScreenTexts.EMPTY;
				MultiplayerServerListWidget.SERVER_PINGER_THREAD_POOL.submit(() -> {
					try {
						serverEntry.screen.getServerListPinger().add(serverEntry.server, () ->
							client.execute(serverEntry::saveFile), () -> {
							serverEntry.server.setStatus(serverEntry.server.protocolVersion == SharedConstants.getGameVersion().getProtocolVersion() ? ServerInfo.Status.SUCCESSFUL : ServerInfo.Status.INCOMPATIBLE);
							client.execute(serverEntry::update);
						});
					} catch (Exception ignored) {
					}
				});
			}

			byte[] bs = serverEntry.server.getFavicon();
			if (!Arrays.equals(bs, serverEntry.favicon)) {
				if (serverEntry.uploadFavicon(bs)) {
					serverEntry.favicon = bs;
				} else {
					serverEntry.server.setFavicon(null);
					serverEntry.saveFile();
				}
			}

			int x = this.width / 2 + 33 * i - 33 * serverEntries.size() / 2;
			context.drawTexture(RenderLayer::getGuiTextured, serverEntry.icon.getTextureId(), x, this.height / 4 - 5, 0.0F, 0.0F, 32, 32, 32, 32);

			context.drawTextWithShadow(this.textRenderer, serverEntry.server.name, x, this.height / 4 + 33 + 11 * (i % 2) - 5, 0xFFFFFFFF);

			int i2 = x + 32 - 10 - 5;
			if (serverEntry.statusIconTexture != null) {
				context.drawGuiTexture(RenderLayer::getGuiTextured, serverEntry.statusIconTexture, i2, this.height / 4 - 5, 10, 8);
			}
		}
	}
}
