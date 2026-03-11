package com.github.debris.aeqc.feat;

import com.github.debris.aeqc.config.AEQCConfig;
import com.github.debris.aeqc.gui.AEQCConfigUI;
import net.minecraft.client.Minecraft;

public class Callbacks {
    public static void init(Minecraft client) {
        AEQCConfig.OpenConfigUI.getKeybind().setCallback((keyAction, iKeybind) -> {
            client.setScreen(new AEQCConfigUI());
            return true;
        });

        AEQCConfig.FastSearch.getKeybind().setCallback((keyAction, iKeybind) -> SearchTweaks.searchHovered(client));

        AEQCConfig.FastAutoCraftGui.getKeybind().setCallback(((keyAction, iKeybind) -> FastAutoCraft.onKeyGui(client)));
        AEQCConfig.FastAutoCraftBlock.getKeybind().setCallback(((keyAction, iKeybind) -> FastAutoCraft.onKeyBlock(client)));

        AEQCConfig.ClearSearch.getKeybind().setCallback((keyAction, iKeybind) -> SearchTweaks.clear(client));

        AEQCConfig.FastPullOne.getKeybind().setCallback((keyAction, iKeybind) -> FastPull.pullHovered(client, FastPull.Type.ONE));
        AEQCConfig.FastPullStack.getKeybind().setCallback((keyAction, iKeybind) -> FastPull.pullHovered(client, FastPull.Type.STACK));
    }
}
