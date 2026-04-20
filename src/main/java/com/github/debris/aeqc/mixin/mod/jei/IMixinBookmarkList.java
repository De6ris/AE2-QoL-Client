package com.github.debris.aeqc.mixin.mod.jei;

import mezz.jei.gui.bookmarks.BookmarkFactory;
import mezz.jei.gui.bookmarks.BookmarkList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value = BookmarkList.class, remap = false)
public interface IMixinBookmarkList {
    @Accessor(remap = false)
    BookmarkFactory getBookmarkFactory();
}
