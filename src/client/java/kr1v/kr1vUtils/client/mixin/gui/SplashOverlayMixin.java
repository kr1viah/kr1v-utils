package kr1v.kr1vUtils.client.mixin.gui;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.blaze3d.systems.CommandEncoder;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.GpuTexture;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.SplashOverlay;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

import java.util.function.Function;

@Debug(export = true)
@Mixin(SplashOverlay.class)
public class SplashOverlayMixin {
    @Shadow
    @Final
    private MinecraftClient client;

    @Definition(id = "context", local = @Local(type = DrawContext.class, argsOnly = true))
    @Definition(id = "fill", method = "Lnet/minecraft/client/gui/DrawContext;fill(Lnet/minecraft/client/render/RenderLayer;IIIII)V")
    @Expression("context.fill(?, ?, ?, ?, ?, ?)")
    @WrapWithCondition(method = "render", at = @At("MIXINEXTRAS:EXPRESSION"))
    private boolean preventFill(DrawContext instance, RenderLayer layer, int x1, int y1, int x2, int y2, int color) {
        return false;
    }

    @Definition(id = "context", local = @Local(type = DrawContext.class, argsOnly = true))
    @Definition(id = "drawTexture", method = "Lnet/minecraft/client/gui/DrawContext;drawTexture(Ljava/util/function/Function;Lnet/minecraft/util/Identifier;IIFFIIIIIII)V")
    @Expression("context.drawTexture(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")
    @WrapWithCondition(method = "render", at = @At("MIXINEXTRAS:EXPRESSION"))
    private boolean preventFill(DrawContext instance, Function<Identifier, RenderLayer> renderLayers, Identifier sprite, int x, int y, float u, float v, int width, int height, int regionWidth, int regionHeight, int textureWidth, int textureHeight, int color) {
        return false;
    }
    @WrapOperation(method = "render", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/CommandEncoder;clearColorTexture(Lcom/mojang/blaze3d/textures/GpuTexture;I)V"))
    private void prevent(CommandEncoder instance, GpuTexture gpuTexture, int i, Operation<Void> original, @Local DrawContext drawContext, @Local(argsOnly = true, ordinal = 0) int mouseX, @Local(argsOnly = true, ordinal = 1) int mouseY, @Local(argsOnly = true, ordinal = 0) float deltaTicks) {
        try {
            this.client.currentScreen.render(drawContext, mouseX, mouseY, deltaTicks);
        } catch (Throwable ignored) {
            RenderSystem.getModelViewStack().popMatrix();
            RenderSystem.getModelViewStack().popMatrix();
        }
    }

    @WrapMethod(method = "renderProgressBar")
    private void preventProgressBarRendering(DrawContext context, int minX, int minY, int maxX, int maxY, float opacity, Operation<Void> original) {}
}
