package com.github.debris.aeqc.plugin;

import com.github.debris.aeqc.AEQC;
import com.github.debris.aeqc.config.AEQCConfig;
import com.github.debris.aeqc.feat.Callbacks;
import com.github.debris.aeqc.feat.TaskQueue;
import fi.dy.masa.malilib.config.ConfigManager;
import fi.dy.masa.malilib.event.InputEventHandler;
import fi.dy.masa.malilib.event.TickHandler;
import fi.dy.masa.malilib.interfaces.IInitializationHandler;
import net.minecraft.client.Minecraft;

public class InitHandlerImpl implements IInitializationHandler {
    @Override
    public void registerModHandlers() {
        ConfigManager.getInstance().registerConfigHandler(AEQC.MOD_ID, AEQCConfig.getInstance());

        InputEventHandler.getKeybindManager().registerKeybindProvider(new InputHandlerImpl());

        Callbacks.init(Minecraft.getInstance());

        TickHandler.getInstance().registerClientTickHandler(TaskQueue::tick);
    }
}
