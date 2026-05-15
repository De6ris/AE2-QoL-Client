package com.github.debris.aeqc.mixin.mod.arsnouveau;

import com.github.debris.aeqc.feat.SearchTweaks;
import com.hollingsworth.arsnouveau.client.container.AbstractStorageTerminalScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = AbstractStorageTerminalScreen.class, remap = false)
public class AbstractStorageTerminalScreenMixin {
    @Inject(method = "charTyped",
            at = @At(value = "INVOKE", target = "Lcom/hollingsworth/arsnouveau/client/container/AbstractStorageTerminalScreen;clearFocus()V", remap = true),
            remap = true,
            cancellable = true
    )
    private void preventClearing(char codePoint, int modifiers, CallbackInfoReturnable<Boolean> cir) {
        if (SearchTweaks.CancelInput) {
            SearchTweaks.CancelInput = false;
            cir.setReturnValue(false);
        }
    }

}
