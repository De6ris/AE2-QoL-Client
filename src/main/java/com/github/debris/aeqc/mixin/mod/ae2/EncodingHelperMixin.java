package com.github.debris.aeqc.mixin.mod.ae2;

import appeng.api.stacks.GenericStack;
import appeng.integration.modules.jeirei.EncodingHelper;
import com.github.debris.aeqc.feat.PatternTweaks;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.List;

@Mixin(value = EncodingHelper.class, remap = false)
public class EncodingHelperMixin {
    @WrapOperation(
            method = "encodeBestMatchingStacksIntoSlots",
            at = @At(
                    value = "INVOKE",
                    target = "Lappeng/integration/modules/jeirei/EncodingHelper;addOrMerge(Ljava/util/List;Lappeng/api/stacks/GenericStack;)V",
                    remap = false
            ),
            remap = false
    )
    private static void skipMerging(List<GenericStack> stacks, GenericStack newStack, Operation<Void> original) {
        if (PatternTweaks.SKIP_MERGING) {
            stacks.add(newStack);
            return;
        }
        original.call(stacks, newStack);
    }
}
