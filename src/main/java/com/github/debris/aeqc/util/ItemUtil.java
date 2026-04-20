package com.github.debris.aeqc.util;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ItemUtil {
    public static Item parseItem(String string) {
        return parseItem(ResourceLocation.parse(string));
    }

    public static Item parseItem(ResourceLocation identifier) {
        return BuiltInRegistries.ITEM.get(identifier);
    }

    public static boolean isFullStack(ItemStack itemStack) {
        return itemStack.getCount() >= itemStack.getMaxStackSize();
    }

    public static boolean canMerge(ItemStack to, ItemStack from) {
        if (isFullStack(to)) return false;
        return ItemStack.isSameItemSameComponents(to, from);
    }
}
