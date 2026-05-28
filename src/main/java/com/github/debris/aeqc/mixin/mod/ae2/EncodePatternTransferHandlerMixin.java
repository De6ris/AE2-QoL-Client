package com.github.debris.aeqc.mixin.mod.ae2;

import appeng.api.stacks.GenericStack;
import appeng.integration.modules.jei.transfer.EncodePatternTransferHandler;
import appeng.menu.me.items.PatternEncodingTermMenu;
import com.github.debris.aeqc.feat.PatternTweaks;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import net.minecraft.world.item.crafting.Recipe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.List;

@Mixin(value = EncodePatternTransferHandler.class, remap = false)
public class EncodePatternTransferHandlerMixin {
    @WrapOperation(
            method = "transferRecipe(Lappeng/menu/me/items/PatternEncodingTermMenu;Ljava/lang/Object;Lmezz/jei/api/gui/ingredient/IRecipeSlotsView;Lnet/minecraft/world/entity/player/Player;ZZ)Lmezz/jei/api/recipe/transfer/IRecipeTransferError;",
            at = @At(value = "INVOKE", target = "Lappeng/integration/modules/jeirei/EncodingHelper;encodeProcessingRecipe(Lappeng/menu/me/items/PatternEncodingTermMenu;Ljava/util/List;Ljava/util/List;)V", remap = false),
            remap = false
    )
    private void tweakPattern(PatternEncodingTermMenu menu,
                              List<List<GenericStack>> genericIngredients,
                              List<GenericStack> genericResults,
                              Operation<Void> original,
                              @Local(name = "recipe") Recipe<?> recipe,
                              @Local(argsOnly = true) IRecipeSlotsView slotsView
    ) {
        PatternTweaks.onTransfer(
                recipe, slotsView, genericIngredients, genericResults, (input, output) -> original.call(menu, input, output)
        );
    }
}
