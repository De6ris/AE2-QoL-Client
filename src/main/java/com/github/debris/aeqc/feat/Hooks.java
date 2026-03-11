package com.github.debris.aeqc.feat;

import appeng.menu.me.items.PatternEncodingTermMenu;
import com.github.debris.aeqc.config.AEQCConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;

public class Hooks {
    public static void onContainerInitializeContents(Minecraft client, int containerId) {
        Player player = client.player;
        assert player != null;
        if (containerId != 0 && containerId == player.containerMenu.containerId) {
            ContainerReady.setReady(containerId);
        }
        if (AEQCConfig.AutoBlankPatternRestock.getBooleanValue() && player.containerMenu instanceof PatternEncodingTermMenu menu) {
            BlankPatternRestock.execute(menu);
        }
    }

}
