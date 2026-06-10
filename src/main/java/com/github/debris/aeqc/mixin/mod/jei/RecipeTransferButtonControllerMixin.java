package com.github.debris.aeqc.mixin.mod.jei;

import com.github.debris.aeqc.config.AEQCConfig;
import com.github.debris.aeqc.feat.PatternTweaks;
import mezz.jei.api.gui.IRecipeLayoutDrawable;
import mezz.jei.api.gui.inputs.IJeiUserInput;
import mezz.jei.gui.recipes.RecipeTransferButtonController;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.Rect2i;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = RecipeTransferButtonController.class, remap = false)
public class RecipeTransferButtonControllerMixin {
    @Shadow
    @Final
    private IRecipeLayoutDrawable<?> recipeLayout;

    @Inject(
            method = "onPress",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screens/Screen;hasShiftDown()Z", remap = true),
            remap = false
    )
    private void onRecipeTransfer(IJeiUserInput input, CallbackInfoReturnable<Boolean> cir) {
        PatternTweaks.SKIP_MERGING = AEQCConfig.ModifierSkipPatternMerging.getKeybind().isKeybindHeld();
    }

    @Inject(
            method = "drawExtras",
            at = @At(value = "INVOKE",
                    target = "Lmezz/jei/api/recipe/transfer/IRecipeTransferError;showError(Lnet/minecraft/client/gui/GuiGraphics;IILmezz/jei/api/gui/ingredient/IRecipeSlotsView;II)V",
                    remap = false),
            remap = false
    )
    private void onTooltip(GuiGraphics guiGraphics, Rect2i buttonArea, int mouseX, int mouseY, float partialTicks, CallbackInfo ci) {
        PatternTweaks.CURRENT_LAYOUT = this.recipeLayout;
    }
}
