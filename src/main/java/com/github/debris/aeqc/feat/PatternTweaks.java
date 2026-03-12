package com.github.debris.aeqc.feat;

import appeng.api.stacks.GenericStack;
import com.github.debris.aeqc.config.AEQCConfig;
import com.github.debris.aeqc.reference.GTCEUReference;
import com.github.debris.aeqc.reference.ModReference;
import com.github.debris.aeqc.unsafe.GTCEUAccess;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PatternTweaks {
    public static boolean SKIP_MERGING = false;

    public static List<GenericStack> onTransfer(List<GenericStack> original, @Nullable Recipe<?> recipe, IRecipeSlotsView slotsView) {
        if (AEQCConfig.PatternPlaceHolder.getBooleanValue() && original.isEmpty()) {
            return PatternPlaceHolder.createOutput(slotsView);
        }

        if (recipe != null
                && ModReference.has(ModReference.GTCEU)
                && GTCEUAccess.isGTRecipeType(recipe.getType())
        ) {
            ResourceLocation id = GTCEUAccess.getId(recipe.getType());
            if (AEQCConfig.GTConvertMoltenAlloy.getBooleanValue() && id.equals(GTCEUReference.ALLOY_BLAST_SMELTER)) {
                return PatternConverter.convertMoltenAlloyToIngot(original);
            }
            if (AEQCConfig.GTConvertHotIngot.getBooleanValue() && id.equals(GTCEUReference.ELECTRIC_BLAST_FURNACE)) {
                return PatternConverter.convertHotIngotToIngot(original);
            }
        }

        return original;
    }
}
