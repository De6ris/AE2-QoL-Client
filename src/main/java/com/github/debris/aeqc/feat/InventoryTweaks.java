package com.github.debris.aeqc.feat;

import com.github.debris.aeqc.util.InventoryUtil;
import com.github.debris.aeqc.util.ItemUtil;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

import java.util.List;
import java.util.Optional;

public class InventoryTweaks {
    public static boolean clearCursor(List<Slot> slots) {
        ItemStack stack = InventoryUtil.getHeldStack();
        List<Slot> mergeSlots = slots.stream()
                .filter(Slot::hasItem)
                .filter(x -> ItemUtil.canMerge(x.getItem(), stack))
                .toList();
        for (Slot slot : mergeSlots) {
            InventoryUtil.leftClick(slot);
            if (!InventoryUtil.isHoldingItem()) return true;
        }

        if (InventoryUtil.isHoldingItem()) {
            Optional<Slot> emptySlot = slots.stream()
                    .filter(x -> !x.hasItem())
                    .findFirst();
            if (emptySlot.isPresent()) {
                InventoryUtil.leftClick(emptySlot.get());
                return true;
            }
        }
        return false;
    }
}
