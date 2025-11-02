package kr1v.kr1vUtils.client.mixin.gui;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import kr1v.kr1vUtils.client.config.Chat;
import net.minecraft.client.gui.hud.SubtitlesHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

import java.util.List;

@Mixin(SubtitlesHud.class)
public class SubtitlesHudMixin {
    @Shadow
    private boolean enabled;

    //	@Definition(id = "enabled", field = "Lnet/minecraft/client/gui/hud/SubtitlesHud;enabled:Z")
//	@Expression("(this.enabled)")
//	@ModifyExpressionValue(method = "render", at = @At("MIXINEXTRAS:EXPRESSION"))
    @ModifyExpressionValue(method = "render", at = @At(value = "FIELD", target = "Lnet/minecraft/client/gui/hud/SubtitlesHud;enabled:Z", ordinal = 4))
    private boolean forceRender(boolean original) {
        return original || Chat.REDIRECT_TO_SUBTITLES.getBooleanValue();
    }

    @Definition(id = "entries", field = "Lnet/minecraft/client/gui/hud/SubtitlesHud;entries:Ljava/util/List;")
    @Expression("this.entries")
    @ModifyExpressionValue(method = "render", at = @At("MIXINEXTRAS:EXPRESSION"))
    private List<SubtitlesHud.SubtitleEntry> removeEntriesIfDisabled(List<SubtitlesHud.SubtitleEntry> original) {
        if (!enabled && Chat.REDIRECT_TO_SUBTITLES.getBooleanValue()) {
            original.removeIf(entry ->
                    entry.range != 1093813.875f);
        }
        return original;
    }

    @Definition(id = "bl", local = @Local(type = boolean.class))
    @Expression("bl")
    @ModifyExpressionValue(method = "render", at = @At("MIXINEXTRAS:EXPRESSION"))
    private boolean preventAngleBracketRendering(boolean original, @SuppressWarnings("LocalMayBeArgsOnly") @Local SubtitlesHud.SubtitleEntry subtitleEntry) {
        if ((subtitleEntry).range == 1093813.875f) {
            return true;
        }
        return original;
    }
}
