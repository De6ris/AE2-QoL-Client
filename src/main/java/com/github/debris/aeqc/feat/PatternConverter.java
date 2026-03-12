package com.github.debris.aeqc.feat;

import appeng.api.stacks.AEFluidKey;
import appeng.api.stacks.AEItemKey;
import appeng.api.stacks.AEKey;
import appeng.api.stacks.GenericStack;
import com.github.debris.aeqc.reference.GTCEUReference;
import com.github.debris.aeqc.reference.ModReference;
import com.github.debris.aeqc.util.ItemUtil;
import com.github.debris.aeqc.util.Platform;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.material.Fluid;

import java.util.List;

public class PatternConverter {
    @SuppressWarnings("deprecation")
    public static List<GenericStack> convertMoltenAlloyToIngot(List<GenericStack> original) {
        if (original.size() != 1) return original;
        GenericStack stack = original.get(0);
        AEKey key = stack.what();
        if (key instanceof AEFluidKey fluidKey) {
            Fluid fluid = fluidKey.getFluid();

            ResourceLocation identifier = BuiltInRegistries.FLUID.getKey(fluid);
            if (!identifier.getPath().startsWith("molten_")) return original;

            String materialName = identifier.getPath().substring(7);
            Item item = ItemUtil.parseItem(Platform.identifier(ModReference.GTCEU, materialName + "_ingot"));
            if (item == Items.AIR) return original;

            int size = (int) stack.amount() / GTCEUReference.INGOT_FLUID_UNIT;
            GenericStack genericStack = GenericStack.fromItemStack(new ItemStack(item, size));
            if (genericStack == null) return original;

            return List.of(genericStack);
        }
        return original;
    }

    public static List<GenericStack> convertHotIngotToIngot(List<GenericStack> original) {
        if (original.size() != 1) return original;
        GenericStack stack = original.get(0);
        AEKey key = stack.what();
        if (key instanceof AEItemKey itemKey) {
            Item hot = itemKey.getItem();

            ResourceLocation identifier = BuiltInRegistries.ITEM.getKey(hot);
            if (!identifier.getPath().startsWith("hot_")) return original;

            String ingotName = identifier.getPath().substring(4);
            Item item = ItemUtil.parseItem(Platform.identifier(ModReference.GTCEU, ingotName));
            if (item == Items.AIR) return original;

            GenericStack genericStack = GenericStack.fromItemStack(new ItemStack(item, (int) stack.amount()));
            if (genericStack == null) return original;

            return List.of(genericStack);
        }
        return original;
    }
}
