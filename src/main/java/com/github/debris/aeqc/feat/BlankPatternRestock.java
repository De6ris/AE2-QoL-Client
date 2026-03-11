package com.github.debris.aeqc.feat;

import appeng.api.stacks.AEItemKey;
import appeng.core.definitions.AEItems;
import appeng.helpers.InventoryAction;
import appeng.menu.SlotSemantics;
import appeng.menu.me.common.GridInventoryEntry;
import appeng.menu.me.items.PatternEncodingTermMenu;
import com.github.debris.aeqc.util.AE2Util;
import com.github.debris.aeqc.util.InventoryUtil;
import com.github.debris.aeqc.util.ItemUtil;
import net.minecraft.world.inventory.Slot;

import java.util.List;
import java.util.Optional;

public class BlankPatternRestock {
    public static void execute(PatternEncodingTermMenu menu) {
        List<Slot> slots = menu.getSlots(SlotSemantics.BLANK_PATTERN);
        if (slots.size() != 1) return;
        Slot slot = slots.get(0);

        if (slot.hasItem() && ItemUtil.isFullStack(slot.getItem())) return;

        Optional<GridInventoryEntry> optional = AE2Util.findEntry(menu.getClientRepo(), AEItemKey.of(AEItems.BLANK_PATTERN));
        if (optional.isEmpty()) return;

        GridInventoryEntry entry = optional.get();
        if (entry.getStoredAmount() == 0) return;

        menu.handleInteraction(entry.getSerial(), InventoryAction.PICKUP_OR_SET_DOWN);
        InventoryUtil.leftClick(slot);
        menu.handleInteraction(entry.getSerial(), InventoryAction.PICKUP_OR_SET_DOWN);
    }
}
