package com.github.debris.aeqc.util;

import appeng.api.stacks.GenericStack;
import mezz.jei.api.ingredients.ITypedIngredient;
import mezz.jei.api.runtime.IJeiRuntime;
import org.jetbrains.annotations.Nullable;
import tamaized.ae2jeiintegration.integration.modules.jei.GenericEntryStackHelper;

public class JeiUtil {
    public static IJeiRuntime jeiRuntime;

    @Nullable
    public static GenericStack getHoveredStack() {
        ITypedIngredient<?> ingredient = jeiRuntime.getBookmarkOverlay().getIngredientUnderMouse()
                .orElseGet(() ->
                        jeiRuntime.getIngredientListOverlay().getIngredientUnderMouse()
                                .orElse(null)
                );
        if (ingredient != null) {
            return GenericEntryStackHelper.ingredientToStack(ingredient);
        }
        return null;
    }
}
