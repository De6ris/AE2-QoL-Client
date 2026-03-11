package com.github.debris.aeqc.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class InventoryUtil {
    public static void leftClick(Slot slot) {
        click(slot, false, ClickType.PICKUP);
    }

    public static void leftClick(int index) {
        click(index, false, ClickType.PICKUP);
    }

    public static void rightClick(Slot slot) {
        click(slot, true, ClickType.PICKUP);
    }

    public static void rightClick(int index) {
        click(index, true, ClickType.PICKUP);
    }

    public static void click(Slot slot, boolean rightClick, ClickType type) {
        click(getSlotId(slot), rightClick, type);
    }

    public static void click(int index, boolean rightClick, ClickType type) {
        clickSlot(index, rightClick ? 1 : 0, type);
    }

    // the button also imply some other data
    public static void clickSlot(Slot slot, int button, ClickType type) {
        clickSlot(getSlotId(slot), button, type);
    }

    // This is the final click slot, act as a valve
    public static void clickSlot(int index, int button, ClickType type) {
        getController().handleInventoryMouseClick(getWindowID(), index, button, type, getClientPlayer());
    }

    public static int getSlotId(Slot slot) {
        return slot.index;
    }

    public static ItemStack getHeldStack() {
        return getCurrentContainer().getCarried();
    }

    public static boolean isHoldingItem() {
        return !getHeldStack().isEmpty();
    }

    public static AbstractContainerMenu getCurrentContainer() {
        return getClientPlayer().containerMenu;
    }

    public static int getWindowID() {
        return getCurrentContainer().containerId;
    }

    public static MultiPlayerGameMode getController() {
        return getClient().gameMode;
    }

    public static LocalPlayer getClientPlayer() {
        return getClient().player;
    }

    public static Minecraft getClient() {
        return Minecraft.getInstance();
    }
}
