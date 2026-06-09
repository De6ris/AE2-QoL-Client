package com.github.debris.aeqc.mixin.mod.ae2;

import com.github.debris.aeqc.feat.PatternTweaks;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.List;

@Mixin(
        targets = "appeng.integration.modules.jei.transfer.EncodePatternTransferHandler$ErrorRenderer",
        remap = false
)
public class ErrorRendererMixin {
    @ModifyExpressionValue(
            method = "showError",
            at = @At(value = "INVOKE",
                    target = "Lappeng/integration/modules/jeirei/TransferHelper;createEncodingTooltip(Z)Ljava/util/List;",
                    remap = false),
            remap = false
    )
    private List<Component> addTooltip(List<Component> original, @Local(argsOnly = true) IRecipeSlotsView slotsView) {
        PatternTweaks.addTransferTooltip(slotsView, original);
        return original;
    }
}
