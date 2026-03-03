package com.github.debris.aeqc.feat;

import appeng.api.stacks.AEFluidKey;
import appeng.api.stacks.AEKey;
import appeng.api.stacks.GenericStack;
import com.github.debris.aeqc.reference.GTCEUReference;
import com.github.debris.aeqc.reference.ModReference;
import com.github.debris.aeqc.util.ItemUtil;
import com.github.debris.aeqc.util.Platform;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.material.Fluid;

import java.util.List;

public class PatternConverter {
    public static List<GenericStack> convertMoltenAlloyToIngot(List<GenericStack> original) {
        if (original.size() != 1) return original;
        GenericStack stack = original.get(0);
        AEKey key = stack.what();
        if (key instanceof AEFluidKey fluidKey) {
            Fluid fluid = fluidKey.getFluid();
            String descriptionKey = fluid.getFluidType().getDescriptionId();
            String material = descriptionKey.substring(15);
            ResourceLocation identifier = Platform.identifier(ModReference.GTCEU, material + "_ingot");
            Item item = ItemUtil.parseItem(identifier);
            if (item == Items.AIR) return original;
            int size = (int) stack.amount() / GTCEUReference.INGOT_FLUID_UNIT;
            GenericStack genericStack = GenericStack.fromItemStack(new ItemStack(item, size));
            if (genericStack == null) return original;
            return List.of(genericStack);
        }
        return original;
    }
}
