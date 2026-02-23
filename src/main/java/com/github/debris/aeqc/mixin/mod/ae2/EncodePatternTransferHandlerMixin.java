package com.github.debris.aeqc.mixin.mod.ae2;

import appeng.api.stacks.GenericStack;
import appeng.integration.modules.jei.transfer.EncodePatternTransferHandler;
import com.github.debris.aeqc.config.AEQCConfig;
import com.github.debris.aeqc.feat.PatternPlaceHolder;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.List;

@Mixin(value = EncodePatternTransferHandler.class, remap = false)
public class EncodePatternTransferHandlerMixin {

    @ModifyExpressionValue(
            method = "transferRecipe(Lappeng/menu/me/items/PatternEncodingTermMenu;Ljava/lang/Object;Lmezz/jei/api/gui/ingredient/IRecipeSlotsView;Lnet/minecraft/world/entity/player/Player;ZZ)Lmezz/jei/api/recipe/transfer/IRecipeTransferError;",
            at = @At(value = "INVOKE", target = "Lappeng/integration/modules/jei/GenericEntryStackHelper;ofOutputs(Lmezz/jei/api/gui/ingredient/IRecipeSlotsView;)Ljava/util/List;", remap = false),
            remap = false
    )
    private List<GenericStack> addOutput(List<GenericStack> original, @Local(argsOnly = true) IRecipeSlotsView slotsView) {
        if (!original.isEmpty()) return original;
        if (!AEQCConfig.PatternPlaceHolder.getBooleanValue()) return original;
        return PatternPlaceHolder.createOutput(slotsView);
    }
}
