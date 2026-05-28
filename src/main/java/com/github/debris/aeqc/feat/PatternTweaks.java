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
import java.util.function.BiConsumer;

public class PatternTweaks {
    public static boolean SKIP_MERGING = false;

    public static void onTransfer(@Nullable Recipe<?> recipe,
                                  IRecipeSlotsView slotsView,
                                  List<List<GenericStack>> input,
                                  List<GenericStack> output,
                                  BiConsumer<List<List<GenericStack>>, List<GenericStack>> original
    ) {
        if (AEQCConfig.GTRemoveCircuit.getBooleanValue()) {
            input = PatternConverter.removeCircuit(input);
        }

        if (AEQCConfig.PatternPlaceholder.getBooleanValue() && output.isEmpty()) {
            output = PatternPlaceHolder.createOutput(slotsView);
        }

        if (recipe != null
                && ModReference.has(ModReference.GTCEU)
                && GTCEUAccess.isGTRecipeType(recipe.getType())
        ) {
            ResourceLocation id = GTCEUAccess.getId(recipe.getType());
            if (AEQCConfig.GTConvertMoltenAlloy.getBooleanValue() && id.equals(GTCEUReference.ALLOY_BLAST_SMELTER)) {
                output = PatternConverter.convertMoltenAlloyToIngot(output);
            }
            if (AEQCConfig.GTConvertHotIngot.getBooleanValue() && id.equals(GTCEUReference.ELECTRIC_BLAST_FURNACE)) {
                output = PatternConverter.convertHotIngotToIngot(output);
            }
        }

        if (recipe == null && ModReference.IS_GTCEU_1_4_4.get()) {
            if (AEQCConfig.GTConvertMoltenAlloy.getBooleanValue()) {
                output = PatternConverter.convertMoltenAlloyToIngot(output);
            }
            if (AEQCConfig.GTConvertHotIngot.getBooleanValue()) {
                output = PatternConverter.convertHotIngotToIngot(output);
            }
        }


        original.accept(input, output);
    }
}
