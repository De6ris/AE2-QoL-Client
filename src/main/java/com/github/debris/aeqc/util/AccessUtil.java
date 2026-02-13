package com.github.debris.aeqc.util;

import appeng.client.gui.AEBaseScreen;
import appeng.client.gui.WidgetContainer;
import appeng.client.gui.me.common.MEStorageScreen;
import appeng.client.gui.me.common.Repo;
import com.github.debris.aeqc.mixin.IMixinAbstractContainerMenu;
import com.github.debris.aeqc.mixin.mod.ae2.IMixinAEBaseScreen;
import com.github.debris.aeqc.mixin.mod.ae2.IMixinMEStorageScreen;
import com.github.debris.aeqc.mixin.mod.ae2.IMixinWidgetContainer;
import com.github.debris.aeqc.mixin.mod.jei.IMixinBookmarkOverlay;
import mezz.jei.gui.bookmarks.BookmarkList;
import mezz.jei.gui.overlay.bookmarks.BookmarkOverlay;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class AccessUtil {
    public static WidgetContainer getWidgetContainer(AEBaseScreen<?> screen) {
        return ((IMixinAEBaseScreen) screen).getWidgets();
    }

    public static Map<String, AbstractWidget> getWidgets(WidgetContainer widgetContainer) {
        return ((IMixinWidgetContainer) widgetContainer).getWidgets();
    }

    public static Repo getRepo(MEStorageScreen<?> screen) {
        return ((IMixinMEStorageScreen) screen).getRepo();
    }

    public static BookmarkList getBookmarkList(BookmarkOverlay bookmarkOverlay) {
        return ((IMixinBookmarkOverlay) bookmarkOverlay).getBookmarkList();
    }

    @Nullable
    public static MenuType<?> getMenuType(AbstractContainerMenu menu) {
        return ((IMixinAbstractContainerMenu) menu).getMenuType();
    }
}
