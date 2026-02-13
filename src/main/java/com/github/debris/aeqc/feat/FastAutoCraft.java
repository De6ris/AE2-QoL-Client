package com.github.debris.aeqc.feat;

import appeng.api.features.HotkeyAction;
import appeng.api.stacks.AEKey;
import appeng.api.stacks.GenericStack;
import appeng.client.Hotkey;
import appeng.client.Hotkeys;
import appeng.client.gui.me.common.MEStorageScreen;
import appeng.client.gui.me.common.Repo;
import appeng.core.sync.network.NetworkHandler;
import appeng.core.sync.packets.HotkeyPacket;
import appeng.helpers.InventoryAction;
import appeng.menu.me.common.GridInventoryEntry;
import com.github.debris.aeqc.util.AccessUtil;
import com.github.debris.aeqc.util.JeiUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

import java.util.Optional;

public class FastAutoCraft {
    public static boolean onKeyGui(Minecraft client) {
        Screen screen = client.screen;

        if (!(screen instanceof MEStorageScreen<?> meStorageScreen)) return false;

        GenericStack stack = JeiUtil.getHoveredStack();
        if (stack == null) return false;

        AEKey key = stack.what();

        return autoCraft(meStorageScreen, key);
    }

    public static boolean onKeyBlock(Minecraft client) {
        if (client.level == null) return false;
        if (client.player == null) return false;
        if (client.player.getAbilities().instabuild) return false;
        if (client.hitResult == null) return false;
        if (client.hitResult.getType() != HitResult.Type.BLOCK) return false;


        BlockPos blockPos = ((BlockHitResult) client.hitResult).getBlockPos();
        BlockState blockState = client.level.getBlockState(blockPos);
        if (blockState.isAir()) return false;

        ItemStack itemstack = blockState.getCloneItemStack(client.hitResult, client.level, blockPos, client.player);

        GenericStack genericStack = GenericStack.fromItemStack(itemstack);
        if (genericStack == null) return false;

        openWirelessTerminal();

        AEKey key = genericStack.what();

        TaskQueue.schedule(
                client_ -> {
                    if (client_.screen instanceof MEStorageScreen<?> screen) {
                        autoCraft(screen, key);
                        return true;
                    }
                    return false;
                },
                5
        );

        return true;
    }

    private static boolean autoCraft(MEStorageScreen<?> screen, AEKey key) {
        Repo repo = AccessUtil.getRepo(screen);
        Optional<GridInventoryEntry> optional = repo.getAllEntries().stream()
                .filter(GridInventoryEntry::isCraftable)
                .filter(entry -> {
                    AEKey what = entry.getWhat();
                    if (what == null) return false;
                    return what.equals(key);
                })
                .findFirst();
        if (optional.isPresent()) {
            long serial = optional.get().getSerial();
            screen.getMenu().handleInteraction(serial, InventoryAction.AUTO_CRAFT);
            return true;
        }
        return false;
    }

    private static void openWirelessTerminal() {
        Hotkey hotkey = Hotkeys.getHotkeyMapping(HotkeyAction.WIRELESS_TERMINAL);
        if (hotkey == null) return;
        NetworkHandler.instance().sendToServer(new HotkeyPacket(hotkey));
    }
}
