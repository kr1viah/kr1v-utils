package kr1v.kr1vUtils.client.mixin.render;

import kr1v.kr1vUtils.client.config.configs.Render;
import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class InGameHudMixin {
    @Inject(method = "renderMiscOverlays", at = @At("HEAD"), cancellable = true)
    private void preventMiscOverlays(CallbackInfo ci) {
        if (Render.MISC_OVERLAYS.shouldHandleNoThis() && !Render.MISC_OVERLAYS.getBooleanValue()) ci.cancel();
    }

    @Inject(method = "renderSleepOverlay", at = @At("HEAD"), cancellable = true)
    private void preventSleepOverlay(CallbackInfo ci) {
        if (Render.SLEEP_OVERLAY.shouldHandleNoThis() && !Render.SLEEP_OVERLAY.getBooleanValue()) ci.cancel();
    }

    @Inject(method = "renderOverlayMessage", at = @At("HEAD"), cancellable = true)
    private void preventOverlayMessage(CallbackInfo ci) {
        if (Render.OVERLAY_MESSAGE.shouldHandleNoThis() && !Render.OVERLAY_MESSAGE.getBooleanValue()) ci.cancel();
    }

    @Inject(method = "renderTitleAndSubtitle", at = @At("HEAD"), cancellable = true)
    private void preventTitleAndSubtitle(CallbackInfo ci) {
        if (Render.TITLE_AND_SUBTITLE.shouldHandleNoThis() && !Render.TITLE_AND_SUBTITLE.getBooleanValue()) ci.cancel();
    }

    @Inject(method = "renderChat", at = @At("HEAD"), cancellable = true)
    private void preventChat(CallbackInfo ci) {
        if (Render.CHAT.shouldHandleNoThis() && !Render.CHAT.getBooleanValue()) ci.cancel();
    }

    @Inject(method = "renderScoreboardSidebar(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/scoreboard/ScoreboardObjective;)V", at = @At("HEAD"), cancellable = true)
    private void preventScoreboardSidebar(CallbackInfo ci) {
        if (Render.SCOREBOARD_SIDEBAR.shouldHandleNoThis() && !Render.SCOREBOARD_SIDEBAR.getBooleanValue()) ci.cancel();
    }

    @Inject(method = "renderPlayerList", at = @At("HEAD"), cancellable = true)
    private void preventPlayerList(CallbackInfo ci) {
        if (Render.PLAYER_LIST.shouldHandleNoThis() && !Render.PLAYER_LIST.getBooleanValue()) ci.cancel();
    }

    @Inject(method = "renderCrosshair", at = @At("HEAD"), cancellable = true)
    private void preventCrosshair(CallbackInfo ci) {
        if (Render.CROSSHAIR.shouldHandleNoThis() && !Render.CROSSHAIR.getBooleanValue()) ci.cancel();
    }

    @Inject(method = "renderStatusEffectOverlay", at = @At("HEAD"), cancellable = true)
    private void preventStatusEffectOverlay(CallbackInfo ci) {
        if (Render.STATUS_EFFECT_OVERLAY.shouldHandleNoThis() && !Render.STATUS_EFFECT_OVERLAY.getBooleanValue()) ci.cancel();
    }

    @Inject(method = "renderMainHud", at = @At("HEAD"), cancellable = true)
    private void preventMainHud(CallbackInfo ci) {
        if (Render.MAIN_HUD.shouldHandleNoThis() && !Render.MAIN_HUD.getBooleanValue()) ci.cancel();
    }

    @Inject(method = "renderHotbar", at = @At("HEAD"), cancellable = true)
    private void preventHotbar(CallbackInfo ci) {
        if (Render.HOTBAR.shouldHandleNoThis() && !Render.HOTBAR.getBooleanValue()) ci.cancel();
    }

    @Inject(method = "renderMountJumpBar", at = @At("HEAD"), cancellable = true)
    private void preventMountJumpBar(CallbackInfo ci) {
        if (Render.MOUNT_JUMP_BAR.shouldHandleNoThis() && !Render.MOUNT_JUMP_BAR.getBooleanValue()) ci.cancel();
    }

    @Inject(method = "renderExperienceBar", at = @At("HEAD"), cancellable = true)
    private void preventExperienceBar(CallbackInfo ci) {
        if (Render.EXPERIENCE_BAR.shouldHandleNoThis() && !Render.EXPERIENCE_BAR.getBooleanValue()) ci.cancel();
    }

    @Inject(method = "renderExperienceLevel", at = @At("HEAD"), cancellable = true)
    private void preventExperienceLevel(CallbackInfo ci) {
        if (Render.EXPERIENCE_LEVEL.shouldHandleNoThis() && !Render.EXPERIENCE_LEVEL.getBooleanValue()) ci.cancel();
    }

    @Inject(method = "renderHeldItemTooltip", at = @At("HEAD"), cancellable = true)
    private void preventHeldItemTooltip(CallbackInfo ci) {
        if (Render.HELD_ITEM_TOOLTIP.shouldHandleNoThis() && !Render.HELD_ITEM_TOOLTIP.getBooleanValue()) ci.cancel();
    }

    @Inject(method = "renderDemoTimer", at = @At("HEAD"), cancellable = true)
    private void preventDemoTimer(CallbackInfo ci) {
        if (Render.DEMO_TIMER.shouldHandleNoThis() && !Render.DEMO_TIMER.getBooleanValue()) ci.cancel();
    }

    @Inject(method = "renderStatusBars", at = @At("HEAD"), cancellable = true)
    private void preventStatusBars(CallbackInfo ci) {
        if (Render.STATUS_BARS.shouldHandleNoThis() && !Render.STATUS_BARS.getBooleanValue()) ci.cancel();
    }

    @Inject(method = "renderArmor", at = @At("HEAD"), cancellable = true)
    private static void preventArmor(CallbackInfo ci) {
        if (Render.ARMOR.shouldHandleNoThis() && !Render.ARMOR.getBooleanValue()) ci.cancel();
    }

    @Inject(method = "renderHealthBar", at = @At("HEAD"), cancellable = true)
    private void preventHealthBar(CallbackInfo ci) {
        if (Render.HEALTH_BAR.shouldHandleNoThis() && !Render.HEALTH_BAR.getBooleanValue()) ci.cancel();
    }

    @Inject(method = "renderFood", at = @At("HEAD"), cancellable = true)
    private void preventFood(CallbackInfo ci) {
        if (Render.FOOD.shouldHandleNoThis() && !Render.FOOD.getBooleanValue()) ci.cancel();
    }

    @Inject(method = "renderMountHealth", at = @At("HEAD"), cancellable = true)
    private void preventMountHealth(CallbackInfo ci) {
        if (Render.MOUNT_HEALTH.shouldHandleNoThis() && !Render.MOUNT_HEALTH.getBooleanValue()) ci.cancel();
    }

    @Inject(method = "renderSpyglassOverlay", at = @At("HEAD"), cancellable = true)
    private void preventSpyglassOverlay(CallbackInfo ci) {
        if (Render.SPYGLASS_OVERLAY.shouldHandleNoThis() && !Render.SPYGLASS_OVERLAY.getBooleanValue()) ci.cancel();
    }

    @Inject(method = "renderVignetteOverlay", at = @At("HEAD"), cancellable = true)
    private void preventVignetteOverlay(CallbackInfo ci) {
        if (Render.VIGNETTE_OVERLAY.shouldHandleNoThis() && !Render.VIGNETTE_OVERLAY.getBooleanValue()) ci.cancel();
    }

    @Inject(method = "renderPortalOverlay", at = @At("HEAD"), cancellable = true)
    private void preventPortalOverlay(CallbackInfo ci) {
        if (Render.PORTAL_OVERLAY.shouldHandleNoThis() && !Render.PORTAL_OVERLAY.getBooleanValue()) ci.cancel();
    }

    @Inject(method = "renderNauseaOverlay", at = @At("HEAD"), cancellable = true)
    private void preventNauseaOverlay(CallbackInfo ci) {
        if (Render.NAUSEA_OVERLAY.shouldHandleNoThis() && !Render.NAUSEA_OVERLAY.getBooleanValue()) ci.cancel();
    }

    @Inject(method = "renderHotbarItem", at = @At("HEAD"), cancellable = true)
    private void preventHotbarItem(CallbackInfo ci) {
        if (Render.HOTBAR_ITEM.shouldHandleNoThis() && !Render.HOTBAR_ITEM.getBooleanValue()) ci.cancel();
    }

    @Inject(method = "renderAutosaveIndicator", at = @At("HEAD"), cancellable = true)
    private void preventAutosaveIndicator(CallbackInfo ci) {
        if (Render.AUTOSAVE_INDICATOR.shouldHandleNoThis() && !Render.AUTOSAVE_INDICATOR.getBooleanValue()) ci.cancel();
    }

}
