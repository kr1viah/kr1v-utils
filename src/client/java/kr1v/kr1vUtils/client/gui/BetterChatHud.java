package kr1v.kr1vUtils.client.gui;

import kr1v.kr1vUtils.client.config.Chat;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.client.gui.hud.ChatHudLine;
import net.minecraft.client.gui.hud.MessageIndicator;
import net.minecraft.client.util.ChatMessages;
import net.minecraft.network.message.MessageSignatureData;
import net.minecraft.text.Text;
import net.minecraft.util.Colors;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.profiler.Profiler;
import net.minecraft.util.profiler.Profilers;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Collections;

// TODO: Make it a mixin
public class BetterChatHud extends ChatHud {
    public static String selectedText;
    boolean selecting = false;

    int anchorLine = -1;
    int anchorChar = 0;

    int activeLine = -1;
    int activeChar = 0;

    private int normFirstSelectedLine = -1;
    private int normLastSelectedLine = -1;
    private int normSelectCharacterStart = 0;
    private int normSelectCharacterEnd = 0;

    private int mouseClickX = 0;
    private int mouseClickY = 0;

    public BetterChatHud(MinecraftClient client) {
        super(client);
    }

    @Override
    public void addMessage(Text message, @Nullable MessageSignatureData signatureData, @Nullable MessageIndicator indicator) {
        ChatHudLine chatHudLine = new ChatHudLine(this.client.inGameHud.getTicks(), message, signatureData, indicator);

        int i = MathHelper.floor((double)this.getWidth() / this.getChatScale());
        MessageIndicator.Icon icon = chatHudLine.getIcon();
        if (icon != null) {
            i -= icon.width + 4 + 2;
        }
        if (normFirstSelectedLine != -1 && normLastSelectedLine != -1) {
            normFirstSelectedLine += ChatMessages.breakRenderedChatMessageLines(chatHudLine.content(), i, this.client.textRenderer).size();
            normLastSelectedLine += ChatMessages.breakRenderedChatMessageLines(chatHudLine.content(), i, this.client.textRenderer).size();
        }
        super.addMessage(message, signatureData, indicator);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY) {
        if (!Chat.CHAT_SELECTING.getBooleanValue()) {
            return super.mouseClicked(mouseX, mouseY);
        }
        mouseClickX = (int) mouseX;
        mouseClickY = (int) mouseY;
        int messageIndex = this.getMessageLineIndex(this.toChatLineX(mouseX), this.toChatLineY(mouseY));
        if (messageIndex < 0) {
            clearSelection();
            return super.mouseClicked(mouseX, mouseY);
        }

        int chatX = (int) this.toChatLineX(mouseX);
        StringBuilder sb = new StringBuilder();
        this.visibleMessages.get(messageIndex).content().accept((index, style, codePoint) -> {
            sb.append((char) codePoint);
            return true;
        });
        String msgStr = sb.toString();
        int charIndex = charIndexAtChatX(msgStr, chatX);

        selecting = true;
        anchorLine = messageIndex;
        anchorChar = charIndex;

        activeLine = anchorLine;
        activeChar = anchorChar;

        normalizeSelection();
        return super.mouseClicked(mouseX, mouseY);
    }

    public void mouseReleased(double mouseX, double mouseY) {
        if (!Chat.CHAT_SELECTING.getBooleanValue()) return;
        if (mouseClickX == (int) mouseX && mouseClickY == (int) mouseY) {
            clearSelection();
            return;
        }
        int messageIndex = this.getMessageLineIndex(this.toChatLineX(mouseX), this.toChatLineY(mouseY));
        if (messageIndex >= 0) {
            int chatX = (int) this.toChatLineX(mouseX);
            StringBuilder sb = new StringBuilder();
            this.visibleMessages.get(messageIndex).content().accept((index, style, codePoint) -> {
                sb.append((char) codePoint);
                return true;
            });
            String msgStr = sb.toString();
            int charIndex = charIndexAtChatX(msgStr, chatX);

            activeLine = messageIndex;
            activeChar = charIndex;
        }

        selecting = false;
        normalizeSelection();
    }

    private int charIndexAtChatX(String messageString, int chatX) {
        if (chatX <= 0) return 0;
        int curX = 0;
        int i = 0;
        int len = messageString.length();
        while (i < len) {
            curX += client.textRenderer.getWidth(String.valueOf(messageString.charAt(i)));
            if (curX > chatX) return i + 1;
            i++;
        }
        return len;
    }

    @Override
    public void render(DrawContext context, int currentTick, int mouseX, int mouseY, boolean focused) {
        if (!Chat.CHAT_SELECTING.getBooleanValue()) {
            super.render(context, currentTick, mouseX, mouseY, focused);
            return;
        }
        StringBuilder selectedTextBuilder = new StringBuilder();
        if (!focused) {
            clearSelection();
        }
        if (!this.isChatHidden()) {
            int visibleLineCount = this.getVisibleLineCount();
            int visibleMessageCount = this.visibleMessages.size();
            if (visibleMessageCount > 0) {
                Profiler profiler = Profilers.get();
                profiler.push("chat");

                float chatScale = (float)this.getChatScale();
                int chatWidthScaled = MathHelper.ceil(this.getWidth() / chatScale);
                int windowHeight = context.getScaledWindowHeight();

                context.getMatrices().push();
                context.getMatrices().scale(chatScale, chatScale, 1.0F);
                context.getMatrices().translate(4.0F, 0.0F, 0.0F);

                int chatSizeY = MathHelper.floor((windowHeight - 40) / chatScale);
                int messageIndexAtMouse = this.getMessageIndex(this.toChatLineX(mouseX), this.toChatLineY(mouseY));

                double chatOpacity = this.client.options.getChatOpacity().getValue() * 0.9 + 0.1;
                double textBackgroundOpacity = this.client.options.getTextBackgroundOpacity().getValue();
                double chatLineSpacing = this.client.options.getChatLineSpacing().getValue();

                int lineHeight = this.getLineHeight();
                int firstLineY = (int)Math.round(-8.0 * (chatLineSpacing + 1.0) + 4.0 * chatLineSpacing);
                int renderedLineCount = 0;

                for (int r = 0; r + this.scrolledLines < this.visibleMessages.size() && r < visibleLineCount; r++) {
                    int messageIndex = r + this.scrolledLines;
                    ChatHudLine.Visible visible = this.visibleMessages.get(messageIndex);
                    if (visible != null) {
                        int ticksSinceAdded = currentTick - visible.addedTime();
                        if (ticksSinceAdded < 200 || focused) {
                            double opacityMultiplier = focused ? 1.0 : getMessageOpacityMultiplier(ticksSinceAdded);
                            int textOpacity = (int)(255.0 * opacityMultiplier * chatOpacity);
                            int backgroundOpacity = (int)(255.0 * opacityMultiplier * textBackgroundOpacity);
                            renderedLineCount++;
                            if (textOpacity > 3) {
                                int y1 = chatSizeY - r * lineHeight;
                                int textY = y1 + firstLineY;

                                if (selecting && messageIndex == getMessageLineIndex(this.toChatLineX(mouseX), this.toChatLineY(mouseY))) {
                                    StringBuilder sb = new StringBuilder();
                                    this.visibleMessages.get(messageIndex).content().accept((index, style, codePoint) -> {
                                        sb.append((char) codePoint);
                                        return true;
                                    });
                                    String messageString = sb.toString();
                                    int chatMouseX = (int) this.toChatLineX(mouseX);
                                    int currentIndex = charIndexAtChatX(messageString, chatMouseX);
                                    activeLine = messageIndex;
                                    activeChar = currentIndex;
                                    normalizeSelection();
                                }

                                if (indexInsideOf(messageIndex)) {
                                    int backgroundColourForNormalText = backgroundOpacity << 24;
                                    int backgroundColourForSelectedText = Chat.CHAT_SELECTED_TEXT_BACKGROUND_COLOUR.getIntegerValue();

                                    int startX = -4;
                                    int normalEndSelectedStart = -4;
                                    int selectedEndNormalStart;
                                    int end = chatWidthScaled + 8;

                                    StringBuilder sb = new StringBuilder();
                                    visible.content().accept((index, style, codePoint) -> {
                                        sb.append((char) codePoint);
                                        return true;
                                    });
                                    String full = sb.toString();
                                    int len = full.length();
                                    int sStart = Math.clamp(normSelectCharacterStart, 0, len);
                                    int sEnd = Math.clamp(normSelectCharacterEnd, 0, len);

                                    if (isFirst(messageIndex) && isLast(messageIndex)) {
                                        if (sStart < sEnd) {
                                            normalEndSelectedStart = client.textRenderer.getWidth(full.substring(0, Math.min(sStart, len)));
                                            selectedEndNormalStart = client.textRenderer.getWidth(full.substring(0, Math.min(sEnd, len)));
                                            selectedTextBuilder.append(full, sStart, sEnd);
                                            selectedTextBuilder.append("\n");
                                        } else {
                                            normalEndSelectedStart = client.textRenderer.getWidth(full.substring(0, Math.min(sEnd, len)));
                                            selectedEndNormalStart = client.textRenderer.getWidth(full.substring(0, Math.min(sStart, len)));
                                            selectedTextBuilder.append(full, sEnd, sStart);
                                            selectedTextBuilder.append("\n");
                                        }
                                    } else if (isFirst(messageIndex)) {
                                        selectedEndNormalStart = client.textRenderer.getWidth(full.substring(0, Math.min(sStart, len)));
                                        selectedTextBuilder.append(full, 0, sStart);
                                        selectedTextBuilder.append("\n");
                                    } else if (isLast(messageIndex)) {
                                        normalEndSelectedStart = client.textRenderer.getWidth(full.substring(0, Math.min(sEnd, len)));
                                        selectedEndNormalStart = end;
                                        selectedTextBuilder.append(full, sEnd, len);
                                        selectedTextBuilder.append("\n");
                                    } else {
                                        selectedTextBuilder.append(full);
                                        selectedTextBuilder.append("\n");
                                        selectedEndNormalStart = end;
                                    }

                                    context.fill(startX, y1 - lineHeight, normalEndSelectedStart, y1, backgroundColourForNormalText);
                                    context.fill(normalEndSelectedStart, y1 - lineHeight, selectedEndNormalStart, y1, backgroundColourForSelectedText);
                                    context.fill(selectedEndNormalStart, y1 - lineHeight, end, y1, backgroundColourForNormalText);
                                } else {
                                    context.fill(-4, y1 - lineHeight, chatWidthScaled + 8, y1, backgroundOpacity << 24);
                                }

                                MessageIndicator messageIndicator = visible.indicator();
                                if (messageIndicator != null) {
                                    int indicatorColourWithAlpha = messageIndicator.indicatorColor() | textOpacity << 24;
                                    context.fill(-4, y1 - lineHeight, -2, y1, indicatorColourWithAlpha);
                                    if (messageIndex == messageIndexAtMouse) {
                                        if (messageIndicator.icon() != null) {
                                            int textIndicatorX = this.getIndicatorX(visible);
                                            int textIndicatorY = textY + 9;
                                            this.drawIndicatorIcon(context, textIndicatorX, textIndicatorY, messageIndicator.icon());
                                        }
                                    }
                                }

                                context.getMatrices().push();
                                context.getMatrices().translate(0.0F, 0.0F, 50.0F);
                                context.drawTextWithShadow(this.client.textRenderer, visible.content(), 0, textY, ColorHelper.withAlpha(textOpacity, Colors.WHITE));
                                context.getMatrices().pop();
                            }
                        }
                    }
                }

                long unprocessedMessageCount = this.client.getMessageHandler().getUnprocessedMessageCount();
                if (unprocessedMessageCount > 0) {
                    int xPendingLinesTextOpacity = (int)(128.0 * chatOpacity);
                    int textBackgroundOpacityReal = (int)(255.0 * textBackgroundOpacity);

                    context.getMatrices().push();
                    context.getMatrices().translate(0.0F, (float) chatSizeY, 0.0F);
                    context.fill(-2, 0, chatWidthScaled + 4, 9, textBackgroundOpacityReal << 24);
                    context.getMatrices().translate(0.0F, 0.0F, 50.0F);
                    context.drawTextWithShadow(this.client.textRenderer, Text.translatable("chat.queue", unprocessedMessageCount), 0, 1, 0xffffff + (xPendingLinesTextOpacity << 24));
                    context.getMatrices().pop();
                }

                if (focused) {
                    int lineHeight1 = this.getLineHeight();
                    int visibleMessageSizeY = visibleMessageCount * lineHeight1;
                    int renderedLinesSizeY = renderedLineCount * lineHeight1;
                    int scrollOffsetY = this.scrolledLines * renderedLinesSizeY / visibleMessageCount - chatSizeY;
                    int scrollThumbHeight = renderedLinesSizeY * renderedLinesSizeY / visibleMessageSizeY;
                    if (visibleMessageSizeY != renderedLinesSizeY) {
                        int alpha = scrollOffsetY > 0 ? 170 : 96;
                        int indicatorColour = this.hasUnreadNewMessages ? 0xcc3333 : 0x3333aa;
                        int x = chatWidthScaled + 4;
                        context.fill(x, -scrollOffsetY, x + 2, -scrollOffsetY - scrollThumbHeight, 100, indicatorColour + (alpha << 24));
                        context.fill(x + 2, -scrollOffsetY, x + 1, -scrollOffsetY - scrollThumbHeight, 100, 0xcccccc + (alpha << 24));
                    }
                }

                context.getMatrices().pop();
                profiler.pop();
                if (selectedTextBuilder.isEmpty()) return;
                String[] lines = selectedTextBuilder.toString().split("\n");
                Collections.reverse(Arrays.asList(lines));

                selectedText = String.join("\n", lines);
            }
        }
    }

    private void clearSelection() {
        selecting = false;
        anchorLine = activeLine = -1;
        selectedText = null;
        normalizeSelection();
    }

    private void normalizeSelection() {
        if (anchorLine < 0 || activeLine < 0) {
            normFirstSelectedLine = normLastSelectedLine = -1;
            normSelectCharacterStart = normSelectCharacterEnd = 0;
            return;
        }

        if (anchorLine <= activeLine) {
            normFirstSelectedLine = anchorLine;
            normLastSelectedLine = activeLine;
            normSelectCharacterStart = anchorChar;
            normSelectCharacterEnd = activeChar;
        } else {
            normFirstSelectedLine = activeLine;
            normLastSelectedLine = anchorLine;
            normSelectCharacterStart = activeChar;
            normSelectCharacterEnd = anchorChar;
        }

        StringBuilder sb = new StringBuilder();
        if (normFirstSelectedLine < visibleMessages.size()) {
            visibleMessages.get(normFirstSelectedLine).content().accept((index, style, codePoint) -> {
                sb.append((char) codePoint);
                return true;
            });
            String firstLineText = sb.toString();
            normSelectCharacterStart = Math.clamp(normSelectCharacterStart, 0, firstLineText.length());
        }
        if (normLastSelectedLine >= 0 && normLastSelectedLine < visibleMessages.size()) {
            visibleMessages.get(normLastSelectedLine).content().accept((index, style, codePoint) -> {
                sb.append((char) codePoint);
                return true;
            });
            String lastLineText = sb.toString();
            normSelectCharacterEnd = Math.clamp(normSelectCharacterEnd, 0, lastLineText.length());
        }
    }

    private boolean indexInsideOf(int index) {
        if (normFirstSelectedLine < 0 || normLastSelectedLine < 0) return false;
        return normFirstSelectedLine <= index && index <= normLastSelectedLine;
    }

    private boolean isFirst(int index) {
        return index == normFirstSelectedLine;
    }

    private boolean isLast(int index) {
        return index == normLastSelectedLine;
    }
}
