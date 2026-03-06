package com.github.debris.aeqc.mixin.mod.jei;

import com.github.debris.aeqc.config.AEQCConfig;
import com.github.debris.aeqc.feat.PatternTweaks;
import mezz.jei.gui.input.UserInput;
import mezz.jei.gui.recipes.RecipeTransferButton;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = RecipeTransferButton.class, remap = false)
public class RecipeTransferButtonMixin {
    @Inject(
            method = "onMouseClicked",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screens/Screen;hasShiftDown()Z", remap = true),
            remap = false
    )
    private void onRecipeTransfer(UserInput input, CallbackInfoReturnable<Boolean> cir) {
        PatternTweaks.SKIP_MERGING = AEQCConfig.ModifierSkipPatternMerging.getKeybind().isKeybindHeld();
    }
}
