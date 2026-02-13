package com.github.debris.aeqc.feat;

import appeng.api.stacks.AEKey;
import appeng.api.stacks.GenericStack;
import appeng.integration.modules.jei.GenericEntryStackHelper;
import appeng.menu.me.crafting.CraftConfirmMenu;
import appeng.menu.me.crafting.CraftingPlanSummary;
import appeng.menu.me.crafting.CraftingPlanSummaryEntry;
import com.github.debris.aeqc.util.AccessUtil;
import com.github.debris.aeqc.util.JeiUtil;
import mezz.jei.api.ingredients.ITypedIngredient;
import mezz.jei.api.runtime.IBookmarkOverlay;
import mezz.jei.api.runtime.IIngredientManager;
import mezz.jei.gui.bookmarks.BookmarkList;
import mezz.jei.gui.bookmarks.IngredientBookmark;
import mezz.jei.gui.overlay.bookmarks.BookmarkOverlay;

import java.util.List;

public class BookmarkMissing {
    public static void onClick(CraftConfirmMenu menu) {
        CraftingPlanSummary plan = menu.getPlan();
        if (plan == null) return;
        List<CraftingPlanSummaryEntry> entries = plan.getEntries();
        IIngredientManager ingredientManager = JeiUtil.jeiRuntime.getIngredientManager();
        IBookmarkOverlay bookmarkOverlay = JeiUtil.jeiRuntime.getBookmarkOverlay();
        if (!(bookmarkOverlay instanceof BookmarkOverlay notDummy)) return;
        BookmarkList bookmarkList = AccessUtil.getBookmarkList(notDummy);
        for (CraftingPlanSummaryEntry entry : entries) {
            if (entry.getMissingAmount() > 0) {
                bookmark(bookmarkList, ingredientManager, entry.getWhat());
            }
        }
    }

    private static void bookmark(BookmarkList bookmarkList, IIngredientManager ingredientManager, AEKey what) {
        ITypedIngredient<?> iTypedIngredient = GenericEntryStackHelper.stackToIngredient(
                ingredientManager,
                new GenericStack(what, 1)
        );
        if (iTypedIngredient == null) return;
        bookmarkList.add(IngredientBookmark.create(iTypedIngredient, ingredientManager));
    }
}
