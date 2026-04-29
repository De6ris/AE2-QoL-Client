package com.github.debris.aeqc.feat;

import appeng.api.stacks.GenericStack;
import com.github.debris.aeqc.config.AEQCConfig;
import com.github.debris.aeqc.util.ItemUtil;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import tamaized.ae2jeiintegration.integration.modules.jei.GenericEntryStackHelper;

import java.util.List;

public class PatternPlaceHolder {

    public static List<GenericStack> createOutput(IRecipeSlotsView slotsView) {
        List<List<GenericStack>> inputs = GenericEntryStackHelper.ofInputs(slotsView);
        if (inputs.isEmpty()) return List.of();

        List<GenericStack> first = inputs.get(0);
        // don't know why list here
        if (first.size() != 1) return List.of();

        GenericStack mainBlock = first.get(0);
        Item item = ItemUtil.parseItem(AEQCConfig.PlaceholderItem.getStringValue());
        ItemStack placeHolder = new ItemStack(item == Items.AIR ? Items.PAPER : item);
        placeHolder.set(DataComponents.CUSTOM_NAME, mainBlock.what().getDisplayName());

        GenericStack stack = GenericStack.fromItemStack(placeHolder);
        if (stack == null) return List.of();

        return List.of(stack);
    }
}
