package com.github.debris.aeqc.mixin.mod.ae2;

import appeng.client.gui.WidgetContainer;
import net.minecraft.client.gui.components.AbstractWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;

@Mixin(value = WidgetContainer.class, remap = false)
public interface IMixinWidgetContainer {
    @Accessor(remap = false)
    Map<String, AbstractWidget> getWidgets();
}
