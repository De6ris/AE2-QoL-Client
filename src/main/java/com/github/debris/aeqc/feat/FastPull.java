package com.github.debris.aeqc.feat;

import appeng.api.stacks.AEItemKey;
import appeng.api.stacks.AEKey;
import appeng.api.stacks.GenericStack;
import appeng.client.gui.me.common.MEStorageScreen;
import appeng.client.gui.me.common.Repo;
import appeng.helpers.InventoryAction;
import appeng.menu.SlotSemantics;
import appeng.menu.me.common.GridInventoryEntry;
import appeng.menu.me.common.MEStorageMenu;
import com.github.debris.aeqc.util.AE2Util;
import com.github.debris.aeqc.util.AccessUtil;
import com.github.debris.aeqc.util.InventoryUtil;
import com.github.debris.aeqc.util.JeiUtil;
import net.minecraft.client.Minecraft;

import java.util.Optional;

public class FastPull {
    public static boolean pullHovered(Minecraft client, Type type) {
        if (!(client.screen instanceof MEStorageScreen<?> screen)) {
            return false;
        }
        GenericStack stack = JeiUtil.getHoveredStack();
        if (stack == null) return false;

        AEKey key = stack.what();
        if (!AEItemKey.is(key)) return false;

        Repo repo = AccessUtil.getRepo(screen);
        Optional<GridInventoryEntry> optional = AE2Util.findEntry(repo, key);

        if (optional.isEmpty()) return false;

        GridInventoryEntry entry = optional.get();
        MEStorageMenu menu = screen.getMenu();

        if (entry.getStoredAmount() == 0) {
            return handleNoStock(entry, menu);
        }

        pullInternal(menu, entry, type);

        return true;
    }

    private static void pullInternal(MEStorageMenu menu, GridInventoryEntry entry, Type type) {
        if (type == Type.STACK) {
            menu.handleInteraction(entry.getSerial(), InventoryAction.SHIFT_CLICK);
        } else {
            menu.handleInteraction(entry.getSerial(), InventoryAction.PICKUP_SINGLE);
            TaskQueue.schedule(getClearCursorTask(), 5);
        }
    }

    private static Task getClearCursorTask() {
        return client -> {
            if (!(client.screen instanceof MEStorageScreen<?> screen)) {
                // quit task
                return true;
            }
            if (!InventoryUtil.isHoldingItem()) {
                // wait for sync
                return false;
            }
            boolean cleared = InventoryTweaks.clearCursor(screen.getMenu().getSlots(SlotSemantics.PLAYER_HOTBAR));
            if (!cleared) InventoryTweaks.clearCursor(screen.getMenu().getSlots(SlotSemantics.PLAYER_INVENTORY));
            return true;
        };
    }

    private static boolean handleNoStock(GridInventoryEntry entry, MEStorageMenu menu) {
        if (entry.isCraftable()) {
            menu.handleInteraction(entry.getSerial(), InventoryAction.AUTO_CRAFT);
            return true;
        } else {
            return false;
        }
    }

    public enum Type {
        ONE,
        STACK
    }
}
