package com.github.debris.aeqc.feat;

import appeng.api.stacks.AEKey;
import appeng.api.stacks.GenericStack;
import appeng.integration.modules.itemlists.EncodingHelper;
import com.github.debris.aeqc.config.AEQCConfig;
import com.github.debris.aeqc.localization.TooltipText;
import com.github.debris.aeqc.reference.GTCEUReference;
import com.github.debris.aeqc.reference.ModReference;
import com.github.debris.aeqc.unsafe.GTCEUAccess;
import fi.dy.masa.malilib.hotkeys.IKeybind;
import mezz.jei.api.gui.IRecipeLayoutDrawable;
import mezz.jei.api.gui.builder.ITooltipBuilder;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import org.jetbrains.annotations.Nullable;
import tamaized.ae2jeiintegration.integration.modules.jei.GenericEntryStackHelper;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;

public class PatternTweaks {
    public static boolean SKIP_MERGING = false;

    public static IRecipeLayoutDrawable<?> CURRENT_LAYOUT = null;

    public static void addTransferTooltip(ITooltipBuilder tooltip) {
        IRecipeLayoutDrawable<?> layout = CURRENT_LAYOUT;
        if (layout == null) return;

        Object recipe = layout.getRecipe();
        if (recipe instanceof RecipeHolder<?> recipeHolder && !EncodingHelper.isSupportedCraftingRecipe(recipeHolder.value())) {
            IKeybind keybind = AEQCConfig.ModifierSkipPatternMerging.getKeybind();
            if (!keybind.getKeys().isEmpty() && anyMergeable(layout.getRecipeSlotsView())) {
                tooltip.add(TooltipText.SKIP_MERGING.text(keybind.getKeysDisplayString()));
            }
        }
    }

    private static boolean anyMergeable(IRecipeSlotsView slotsView) {
        List<List<GenericStack>> inputs = GenericEntryStackHelper.ofInputs(slotsView);
        List<AEKey> list = inputs.stream()
                .filter(x -> x.size() == 1)
                .map(x -> x.get(0).what())
                .toList();
        Set<AEKey> set = new HashSet<>(list);
        return list.size() != set.size();
    }

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


        original.accept(input, output);
    }
}
