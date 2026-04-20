package com.github.debris.aeqc.feat;

import appeng.api.stacks.AEKey;
import appeng.api.stacks.GenericStack;
import appeng.client.gui.StackWithBounds;
import appeng.menu.me.crafting.CraftConfirmMenu;
import appeng.menu.me.crafting.CraftingPlanSummary;
import appeng.menu.me.crafting.CraftingPlanSummaryEntry;
import com.github.debris.aeqc.util.AccessUtil;
import com.github.debris.aeqc.util.JeiUtil;
import mezz.jei.api.runtime.IBookmarkOverlay;
import mezz.jei.api.runtime.IClickableIngredient;
import mezz.jei.api.runtime.IIngredientManager;
import mezz.jei.gui.bookmarks.BookmarkList;
import mezz.jei.gui.overlay.bookmarks.BookmarkOverlay;
import net.minecraft.client.renderer.Rect2i;
import tamaized.ae2jeiintegration.integration.modules.jei.GenericEntryStackHelper;

import java.util.List;

public class BookmarkMissing {
    private static final Rect2i EMPTY = new Rect2i(0, 0, 0, 0);

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
        IClickableIngredient<?> iClickableIngredient = GenericEntryStackHelper.stackToClickableIngredient(
                ingredientManager,
                new StackWithBounds(new GenericStack(what, 1), EMPTY)
        );
        if (iClickableIngredient == null) return;
        bookmarkList.add(AccessUtil.getBookmarkFactory(bookmarkList).create(iClickableIngredient.getTypedIngredient()));
    }
}
