package com.github.debris.aeqc.util;

import appeng.api.stacks.GenericStack;
import appeng.integration.modules.jei.GenericEntryStackHelper;
import mezz.jei.api.ingredients.ITypedIngredient;
import mezz.jei.api.runtime.IJeiRuntime;
import org.jetbrains.annotations.Nullable;

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
