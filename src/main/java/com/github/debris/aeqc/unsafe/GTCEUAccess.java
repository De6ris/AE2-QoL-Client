package com.github.debris.aeqc.unsafe;

import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeType;

public class GTCEUAccess {
    public static boolean isGTRecipeType(RecipeType<?> type) {
        return type instanceof GTRecipeType;
    }

    public static ResourceLocation getId(RecipeType<?> type) {
        return ((GTRecipeType) type).registryName;
    }
}
