package com.github.debris.aeqc.mixin.mod.ae2;

import com.github.debris.aeqc.feat.PatternTweaks;
import com.llamalad7.mixinextras.sugar.Local;
import mezz.jei.api.gui.builder.ITooltipBuilder;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(
        targets = "tamaized.ae2jeiintegration.integration.modules.jei.transfer.EncodePatternTransferHandler$ErrorRenderer",
        remap = false
)
public class ErrorRendererMixin {
    @Inject(
            method = "getTooltip",
            at = @At("RETURN"),
            remap = false
    )
    private void addTooltip(ITooltipBuilder tooltip, CallbackInfo ci) {
        PatternTweaks.addTransferTooltip(tooltip);
    }
}
