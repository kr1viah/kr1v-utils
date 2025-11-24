package kr1v.kr1vUtils.client.mixin.accessor;

import fi.dy.masa.malilib.gui.widgets.WidgetListConfigOptionsBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(WidgetListConfigOptionsBase.class)
public interface WidgetListConfigOptionsBaseAccessor {
    @Accessor int getMaxLabelWidth();
    @Accessor void setConfigWidth(int configWidth);
}
