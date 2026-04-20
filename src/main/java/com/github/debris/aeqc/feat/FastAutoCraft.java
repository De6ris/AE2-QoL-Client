package com.github.debris.aeqc.feat;

import appeng.api.stacks.AEKey;
import appeng.api.stacks.GenericStack;
import appeng.client.gui.me.common.MEStorageScreen;
import appeng.client.gui.me.common.Repo;
import appeng.helpers.InventoryAction;
import appeng.menu.me.common.GridInventoryEntry;
import com.github.debris.aeqc.util.AE2Util;
import com.github.debris.aeqc.util.AccessUtil;
import com.github.debris.aeqc.util.JeiUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;

import java.util.Optional;

public class FastAutoCraft {
    public static boolean onKeyGui(Minecraft client) {
        Screen screen = client.screen;

        if (!(screen instanceof MEStorageScreen<?> meStorageScreen)) return false;

        GenericStack stack = JeiUtil.getHoveredStack();
        if (stack == null) return false;

        AEKey key = stack.what();

        return autoCraft(meStorageScreen, key);
    }

    private static boolean autoCraft(MEStorageScreen<?> screen, AEKey key) {
        Repo repo = AccessUtil.getRepo(screen);
        Optional<GridInventoryEntry> optional = AE2Util.findEntry(repo, key, GridInventoryEntry::isCraftable);
        if (optional.isPresent()) {
            long serial = optional.get().getSerial();
            screen.getMenu().handleInteraction(serial, InventoryAction.AUTO_CRAFT);
            return true;
        }
        return false;
    }
}
