package com.github.debris.aeqc.mixin.mod.ae2;

import appeng.client.gui.AEBaseScreen;
import appeng.client.gui.WidgetContainer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value = AEBaseScreen.class, remap = false)
public interface IMixinAEBaseScreen {
    @Accessor(remap = false)
    WidgetContainer getWidgets();
}
