package kr1v.kr1vUtils.client.mixin.render;

import kr1v.kr1vUtils.client.config.Render;
import net.minecraft.client.render.BuiltBuffer;
import net.minecraft.client.render.RenderLayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RenderLayer.MultiPhase.class)
public class RenderLayerMixin {
    @Inject(method = "draw", at = @At("HEAD"), cancellable = true)
    private void injected(BuiltBuffer buffer, CallbackInfo ci) {
        if ("solid"                          .equals(((RenderLayer)(Object)this).getName()) && !Render.SOLID.getBooleanValue()) { ci.cancel(); return; }
        if ("cutout_mipped"                  .equals(((RenderLayer)(Object)this).getName()) && !Render.CUTOUT_MIPPED.getBooleanValue()) { ci.cancel(); return; }
        if ("cutout"                         .equals(((RenderLayer)(Object)this).getName()) && !Render.CUTOUT.getBooleanValue()) { ci.cancel(); return; }
        if ("translucent"                    .equals(((RenderLayer)(Object)this).getName()) && !Render.TRANSLUCENT.getBooleanValue()) { ci.cancel(); return; }
        if ("translucent_moving_block"       .equals(((RenderLayer)(Object)this).getName()) && !Render.TRANSLUCENT_MOVING_BLOCK.getBooleanValue()) { ci.cancel(); return; }
        if ("armor_cutout_no_cull"           .equals(((RenderLayer)(Object)this).getName()) && !Render.ARMOR_CUTOUT_NO_CULL.getBooleanValue()) { ci.cancel(); return; }
        if ("armor_translucent"              .equals(((RenderLayer)(Object)this).getName()) && !Render.ARMOR_TRANSLUCENT.getBooleanValue()) { ci.cancel(); return; }
        if ("entity_solid"                   .equals(((RenderLayer)(Object)this).getName()) && !Render.ENTITY_SOLID.getBooleanValue()) { ci.cancel(); return; }
        if ("entity_solid_z_offset_forward"  .equals(((RenderLayer)(Object)this).getName()) && !Render.ENTITY_SOLID_Z_OFFSET_FORWARD.getBooleanValue()) { ci.cancel(); return; }
        if ("entity_cutout"                  .equals(((RenderLayer)(Object)this).getName()) && !Render.ENTITY_CUTOUT.getBooleanValue()) { ci.cancel(); return; }
        if ("entity_cutout_no_cull"          .equals(((RenderLayer)(Object)this).getName()) && !Render.ENTITY_CUTOUT_NO_CULL.getBooleanValue()) { ci.cancel(); return; }
        if ("entity_cutout_no_cull_z_offset" .equals(((RenderLayer)(Object)this).getName()) && !Render.ENTITY_CUTOUT_NO_CULL_Z_OFFSET.getBooleanValue()) { ci.cancel(); return; }
        if ("item_entity_translucent_cull"   .equals(((RenderLayer)(Object)this).getName()) && !Render.ITEM_ENTITY_TRANSLUCENT_CULL.getBooleanValue()) { ci.cancel(); return; }
        if ("entity_translucent"             .equals(((RenderLayer)(Object)this).getName()) && !Render.ENTITY_TRANSLUCENT.getBooleanValue()) { ci.cancel(); return; }
        if ("entity_translucent_emissive"    .equals(((RenderLayer)(Object)this).getName()) && !Render.ENTITY_TRANSLUCENT_EMISSIVE.getBooleanValue()) { ci.cancel(); return; }
        if ("entity_smooth_cutout"           .equals(((RenderLayer)(Object)this).getName()) && !Render.ENTITY_SMOOTH_CUTOUT.getBooleanValue()) { ci.cancel(); return; }
        if ("beacon_beam"                    .equals(((RenderLayer)(Object)this).getName()) && !Render.BEACON_BEAM.getBooleanValue()) { ci.cancel(); return; }
        if ("entity_decal"                   .equals(((RenderLayer)(Object)this).getName()) && !Render.ENTITY_DECAL.getBooleanValue()) { ci.cancel(); return; }
        if ("entity_no_outline"              .equals(((RenderLayer)(Object)this).getName()) && !Render.ENTITY_NO_OUTLINE.getBooleanValue()) { ci.cancel(); return; }
        if ("entity_shadow"                  .equals(((RenderLayer)(Object)this).getName()) && !Render.ENTITY_SHADOW.getBooleanValue()) { ci.cancel(); return; }
        if ("entity_alpha"                   .equals(((RenderLayer)(Object)this).getName()) && !Render.ENTITY_ALPHA.getBooleanValue()) { ci.cancel(); return; }
        if ("eyes"                           .equals(((RenderLayer)(Object)this).getName()) && !Render.EYES.getBooleanValue()) { ci.cancel(); return; }
        if ("leash"                          .equals(((RenderLayer)(Object)this).getName()) && !Render.LEASH.getBooleanValue()) { ci.cancel(); return; }
        if ("water_mask"                     .equals(((RenderLayer)(Object)this).getName()) && !Render.WATER_MASK.getBooleanValue()) { ci.cancel(); return; }
        if ("armor_entity_glint"             .equals(((RenderLayer)(Object)this).getName()) && !Render.ARMOR_ENTITY_GLINT.getBooleanValue()) { ci.cancel(); return; }
        if ("glint_translucent"              .equals(((RenderLayer)(Object)this).getName()) && !Render.GLINT_TRANSLUCENT.getBooleanValue()) { ci.cancel(); return; }
        if ("glint"                          .equals(((RenderLayer)(Object)this).getName()) && !Render.GLINT.getBooleanValue()) { ci.cancel(); return; }
        if ("entity_glint"                   .equals(((RenderLayer)(Object)this).getName()) && !Render.ENTITY_GLINT.getBooleanValue()) { ci.cancel(); return; }
        if ("crumbling"                      .equals(((RenderLayer)(Object)this).getName()) && !Render.CRUMBLING.getBooleanValue()) { ci.cancel(); return; }
        if ("text"                           .equals(((RenderLayer)(Object)this).getName()) && !Render.TEXT.getBooleanValue()) { ci.cancel(); return; }
        if ("text_background"                .equals(((RenderLayer)(Object)this).getName()) && !Render.TEXT_BACKGROUND.getBooleanValue()) { ci.cancel(); return; }
        if ("text_intensity"                 .equals(((RenderLayer)(Object)this).getName()) && !Render.TEXT_INTENSITY.getBooleanValue()) { ci.cancel(); return; }
        if ("text_polygon_offset"            .equals(((RenderLayer)(Object)this).getName()) && !Render.TEXT_POLYGON_OFFSET.getBooleanValue()) { ci.cancel(); return; }
        if ("text_intensity_polygon_offset"  .equals(((RenderLayer)(Object)this).getName()) && !Render.TEXT_INTENSITY_POLYGON_OFFSET.getBooleanValue()) { ci.cancel(); return; }
        if ("text_see_through"               .equals(((RenderLayer)(Object)this).getName()) && !Render.TEXT_SEE_THROUGH.getBooleanValue()) { ci.cancel(); return; }
        if ("text_background_see_through"    .equals(((RenderLayer)(Object)this).getName()) && !Render.TEXT_BACKGROUND_SEE_THROUGH.getBooleanValue()) { ci.cancel(); return; }
        if ("text_intensity_see_through"     .equals(((RenderLayer)(Object)this).getName()) && !Render.TEXT_INTENSITY_SEE_THROUGH.getBooleanValue()) { ci.cancel(); return; }
        if ("lightning"                      .equals(((RenderLayer)(Object)this).getName()) && !Render.LIGHTNING.getBooleanValue()) { ci.cancel(); return; }
        if ("dragon_rays"                    .equals(((RenderLayer)(Object)this).getName()) && !Render.DRAGON_RAYS.getBooleanValue()) { ci.cancel(); return; }
        if ("dragon_rays_depth"              .equals(((RenderLayer)(Object)this).getName()) && !Render.DRAGON_RAYS_DEPTH.getBooleanValue()) { ci.cancel(); return; }
        if ("tripwire"                       .equals(((RenderLayer)(Object)this).getName()) && !Render.TRIPWIRE.getBooleanValue()) { ci.cancel(); return; }
        if ("end_portal"                     .equals(((RenderLayer)(Object)this).getName()) && !Render.END_PORTAL.getBooleanValue()) { ci.cancel(); return; }
        if ("end_gateway"                    .equals(((RenderLayer)(Object)this).getName()) && !Render.END_GATEWAY.getBooleanValue()) { ci.cancel(); return; }
        if ("lines"                          .equals(((RenderLayer)(Object)this).getName()) && !Render.LINES.getBooleanValue()) { ci.cancel(); return; }
        if ("secondary_block_outline"        .equals(((RenderLayer)(Object)this).getName()) && !Render.SECONDARY_BLOCK_OUTLINE.getBooleanValue()) { ci.cancel(); return; }
        if ("line_strip"                     .equals(((RenderLayer)(Object)this).getName()) && !Render.LINE_STRIP.getBooleanValue()) { ci.cancel(); return; }
        if ("debug_line_strip"               .equals(((RenderLayer)(Object)this).getName()) && !Render.DEBUG_LINE_STRIP.getBooleanValue()) { ci.cancel(); return; }
        if ("debug_line"                     .equals(((RenderLayer)(Object)this).getName()) && !Render.DEBUG_LINE.getBooleanValue()) { ci.cancel(); return; }
        if ("debug_filled_box"               .equals(((RenderLayer)(Object)this).getName()) && !Render.DEBUG_FILLED_BOX.getBooleanValue()) { ci.cancel(); return; }
        if ("debug_quads"                    .equals(((RenderLayer)(Object)this).getName()) && !Render.DEBUG_QUADS.getBooleanValue()) { ci.cancel(); return; }
        if ("debug_triangle_fan"             .equals(((RenderLayer)(Object)this).getName()) && !Render.DEBUG_TRIANGLE_FAN.getBooleanValue()) { ci.cancel(); return; }
        if ("debug_structure_quads"          .equals(((RenderLayer)(Object)this).getName()) && !Render.DEBUG_STRUCTURE_QUADS.getBooleanValue()) { ci.cancel(); return; }
        if ("debug_section_quads"            .equals(((RenderLayer)(Object)this).getName()) && !Render.DEBUG_SECTION_QUADS.getBooleanValue()) { ci.cancel(); return; }
        if ("opaque_particle"                .equals(((RenderLayer)(Object)this).getName()) && !Render.OPAQUE_PARTICLE.getBooleanValue()) { ci.cancel(); return; }
        if ("translucent_particle"           .equals(((RenderLayer)(Object)this).getName()) && !Render.TRANSLUCENT_PARTICLE.getBooleanValue()) { ci.cancel(); return; }
        if ("weather_all_mask"               .equals(((RenderLayer)(Object)this).getName()) && !Render.WEATHER_ALL_MASK.getBooleanValue()) { ci.cancel(); return; }
        if ("weather_color_mask"             .equals(((RenderLayer)(Object)this).getName()) && !Render.WEATHER_COLOR_MASK.getBooleanValue()) { ci.cancel(); return; }
        if ("sunrise_sunset"                 .equals(((RenderLayer)(Object)this).getName()) && !Render.SUNRISE_SUNSET.getBooleanValue()) { ci.cancel(); return; }
        if ("celestial"                      .equals(((RenderLayer)(Object)this).getName()) && !Render.CELESTIAL.getBooleanValue()) { ci.cancel(); return; }
        if ("block_screen_effect"            .equals(((RenderLayer)(Object)this).getName()) && !Render.BLOCK_SCREEN_EFFECT.getBooleanValue()) { ci.cancel(); return; }
        if ("fire_screen_effect"             .equals(((RenderLayer)(Object)this).getName()) && !Render.FIRE_SCREEN_EFFECT.getBooleanValue()) { ci.cancel(); return; }
        if ("gui"                            .equals(((RenderLayer)(Object)this).getName()) && !Render.GUI.getBooleanValue()) { ci.cancel(); return; }
        if ("gui_overlay"                    .equals(((RenderLayer)(Object)this).getName()) && !Render.GUI_OVERLAY.getBooleanValue()) { ci.cancel(); return; }
        if ("gui_textured_overlay"           .equals(((RenderLayer)(Object)this).getName()) && !Render.GUI_TEXTURED_OVERLAY.getBooleanValue()) { ci.cancel(); return; }
        if ("gui_opaque_textured_background" .equals(((RenderLayer)(Object)this).getName()) && !Render.GUI_OPAQUE_TEXTURED_BACKGROUND.getBooleanValue()) { ci.cancel(); return; }
        if ("gui_nausea_overlay"             .equals(((RenderLayer)(Object)this).getName()) && !Render.GUI_NAUSEA_OVERLAY.getBooleanValue()) { ci.cancel(); return; }
        if ("gui_text_highlight"             .equals(((RenderLayer)(Object)this).getName()) && !Render.GUI_TEXT_HIGHLIGHT.getBooleanValue()) { ci.cancel(); return; }
        if ("gui_ghost_recipe_overlay"       .equals(((RenderLayer)(Object)this).getName()) && !Render.GUI_GHOST_RECIPE_OVERLAY.getBooleanValue()) { ci.cancel(); return; }
        if ("gui_textured"                   .equals(((RenderLayer)(Object)this).getName()) && !Render.GUI_TEXTURED.getBooleanValue()) { ci.cancel(); return; }
        if ("vignette"                       .equals(((RenderLayer)(Object)this).getName()) && !Render.VIGNETTE.getBooleanValue()) { ci.cancel(); return; }
        if ("crosshair"                      .equals(((RenderLayer)(Object)this).getName()) && !Render.CROSSHAIR_TEXTURED.getBooleanValue()) { ci.cancel(); return; }
        if ("mojang_logo"                    .equals(((RenderLayer)(Object)this).getName()) && !Render.MOJANG_LOGO.getBooleanValue()) { ci.cancel(); return; }
    }
}
