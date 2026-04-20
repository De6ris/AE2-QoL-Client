package com.github.debris.aeqc.mixin.mod.jei;

import com.github.debris.aeqc.config.AEQCConfig;
import com.github.debris.aeqc.feat.PatternTweaks;
import mezz.jei.api.gui.inputs.IJeiUserInput;
import mezz.jei.gui.recipes.RecipeTransferButtonController;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = RecipeTransferButtonController.class, remap = false)
public class RecipeTransferButtonControllerMixin {
    @Inject(
            method = "onPress",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screens/Screen;hasShiftDown()Z", remap = true),
            remap = false
    )
    private void onRecipeTransfer(IJeiUserInput input, CallbackInfoReturnable<Boolean> cir) {
        PatternTweaks.SKIP_MERGING = AEQCConfig.ModifierSkipPatternMerging.getKeybind().isKeybindHeld();
    }
}
