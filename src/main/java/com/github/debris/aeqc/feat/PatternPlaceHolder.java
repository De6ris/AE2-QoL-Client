package com.github.debris.aeqc.feat;

import appeng.api.stacks.GenericStack;
import appeng.integration.modules.jei.GenericEntryStackHelper;
import com.github.debris.aeqc.config.AEQCConfig;
import com.github.debris.aeqc.util.ItemUtil;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.List;

public class PatternPlaceHolder {

    public static List<GenericStack> createOutput(IRecipeSlotsView slotsView) {
        List<List<GenericStack>> inputs = GenericEntryStackHelper.ofInputs(slotsView);
        if (inputs.isEmpty()) return List.of();

        List<GenericStack> first = inputs.get(0);
        // don't know why list here
        if (first.size() != 1) return List.of();

        GenericStack mainBlock = first.get(0);
        Item item = ItemUtil.parseItem(AEQCConfig.PlaceHolderItem.getStringValue());
        ItemStack placeHolder = new ItemStack(item == Items.AIR ? Items.PAPER : item);
        placeHolder.setHoverName(mainBlock.what().getDisplayName());

        GenericStack stack = GenericStack.fromItemStack(placeHolder);
        if (stack == null) return List.of();

        return List.of(stack);
    }
}
