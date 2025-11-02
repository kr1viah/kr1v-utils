package kr1v.kr1vUtils.client.mixin.gui;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import kr1v.kr1vUtils.client.config.Chat;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.client.gui.hud.MessageIndicator;
import net.minecraft.client.gui.hud.SubtitlesHud;
import net.minecraft.network.message.MessageSignatureData;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

@Mixin(ChatHud.class)
public class ChatHudMixin {
    @WrapMethod(method = "addMessage(Lnet/minecraft/text/Text;Lnet/minecraft/network/message/MessageSignatureData;Lnet/minecraft/client/gui/hud/MessageIndicator;)V")
    private void injected(Text message, MessageSignatureData signatureData, MessageIndicator indicator, Operation<Void> original) {
        for (String str : Chat.CHAT_HIDE.getStrings()) {
            try {
                Pattern pattern = Pattern.compile(str);
                Matcher matcher = pattern.matcher(message.getString());
                if (matcher.matches()) {
                    if (Chat.REDIRECT_TO_SUBTITLES.getBooleanValue()) {
                        if (MinecraftClient.getInstance().player != null) {
                            List<SubtitlesHud.SubtitleEntry> entries = (MinecraftClient.getInstance().inGameHud.subtitlesHud).entries;
                            if (!Chat.ALLOW_DUPLICATE_SUBTITLES.getBooleanValue()){
                                List<SubtitlesHud.SubtitleEntry> audibleEntries = MinecraftClient.getInstance().inGameHud.subtitlesHud.audibleEntries;
                                for (var entry : audibleEntries) {
                                    if (entry.getText().copy().equals(message.copy())) {
                                        audibleEntries.remove(entry);
                                        entries.remove(entry);
                                        break;
                                    }
                                }
                            }
                            entries.add(new SubtitlesHud.SubtitleEntry(message, 1093813.875f, MinecraftClient.getInstance().player.getPos()));
                        }
                    }
                    return;
                }
            } catch (PatternSyntaxException ignored) {
            }
        }
        original.call(message, signatureData, indicator);
    }
}
