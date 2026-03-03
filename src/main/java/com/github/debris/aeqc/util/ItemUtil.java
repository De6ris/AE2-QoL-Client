package com.github.debris.aeqc.util;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class ItemUtil {
    public static Item parseItem(String string) {
        return parseItem(ResourceLocation.parse(string));
    }

    @SuppressWarnings("deprecation")
    public static Item parseItem(ResourceLocation identifier) {
        return BuiltInRegistries.ITEM.get(identifier);
    }
}
