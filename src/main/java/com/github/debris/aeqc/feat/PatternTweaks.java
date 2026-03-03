package com.github.debris.aeqc.feat;

import appeng.api.stacks.GenericStack;
import com.github.debris.aeqc.config.AEQCConfig;
import com.github.debris.aeqc.reference.GTCEUReference;
import com.github.debris.aeqc.reference.ModReference;
import com.github.debris.aeqc.unsafe.GTCEUAccess;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import net.minecraft.world.item.crafting.Recipe;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PatternTweaks {
    public static List<GenericStack> onTransfer(List<GenericStack> original, @Nullable Recipe<?> recipe, IRecipeSlotsView slotsView) {
        if (AEQCConfig.PatternPlaceHolder.getBooleanValue() && original.isEmpty()) {
            return PatternPlaceHolder.createOutput(slotsView);
        }

        if (AEQCConfig.GTConvertMoltenAlloy.getBooleanValue()
                && recipe != null
                && ModReference.has(ModReference.GTCEU)
                && GTCEUAccess.isGTRecipeType(recipe.getType())
                && GTCEUAccess.getId(recipe.getType()).equals(GTCEUReference.ALLOY_BLAST_SMELTER)
        ) {
            return PatternConverter.convertMoltenAlloyToIngot(original);
        }

        return original;
    }
}
