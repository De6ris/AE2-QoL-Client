package com.github.debris.aeqc.mixin.mod.ae2;

import appeng.api.stacks.GenericStack;
import com.github.debris.aeqc.feat.PatternTweaks;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import net.minecraft.world.item.crafting.Recipe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import tamaized.ae2jeiintegration.integration.modules.jei.transfer.EncodePatternTransferHandler;

import java.util.List;

@Mixin(value = EncodePatternTransferHandler.class, remap = false)
public class EncodePatternTransferHandlerMixin {

    @ModifyExpressionValue(
            method = "transferRecipe(Lappeng/menu/me/items/PatternEncodingTermMenu;Ljava/lang/Object;Lmezz/jei/api/gui/ingredient/IRecipeSlotsView;Lnet/minecraft/world/entity/player/Player;ZZ)Lmezz/jei/api/recipe/transfer/IRecipeTransferError;",
            at = @At(value = "INVOKE", target = "Ltamaized/ae2jeiintegration/integration/modules/jei/GenericEntryStackHelper;ofOutputs(Lmezz/jei/api/gui/ingredient/IRecipeSlotsView;)Ljava/util/List;", remap = false),
            remap = false
    )
    private List<GenericStack> tweakOutput(List<GenericStack> original, @Local(name = "recipe") Recipe<?> recipe, @Local(argsOnly = true) IRecipeSlotsView slotsView) {
        return PatternTweaks.onTransfer(original, recipe, slotsView);
    }
}
