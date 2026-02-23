package com.github.debris.aeqc.util;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class ItemUtil {
    @SuppressWarnings("deprecation")
    public static Item parseItem(String string) {
        return BuiltInRegistries.ITEM.get(ResourceLocation.parse(string));
    }
}
