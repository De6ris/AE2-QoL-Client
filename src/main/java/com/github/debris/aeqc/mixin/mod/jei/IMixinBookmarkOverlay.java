package com.github.debris.aeqc.mixin.mod.jei;

import mezz.jei.gui.bookmarks.BookmarkList;
import mezz.jei.gui.overlay.bookmarks.BookmarkOverlay;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value = BookmarkOverlay.class, remap = false)
public interface IMixinBookmarkOverlay {
    @Accessor(remap = false)
    BookmarkList getBookmarkList();
}
