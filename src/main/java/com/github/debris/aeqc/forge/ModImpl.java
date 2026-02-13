package com.github.debris.aeqc.forge;

import com.github.debris.aeqc.AEQC;
import com.github.debris.aeqc.gui.AEQCConfigUI;
import com.github.debris.aeqc.plugin.InitHandlerImpl;
import com.github.debris.aeqc.util.Platform;
import fi.dy.masa.malilib.event.InitializationHandler;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;

@Mod(AEQC.MOD_ID)
public class ModImpl {
    public ModImpl() {
        if (Platform.isClient()) {
            InitializationHandler.getInstance().registerInitializationHandler(new InitHandlerImpl());

            ModContainer modContainer = ModList.get().getModContainerById(AEQC.MOD_ID).orElseThrow();

            modContainer.registerExtensionPoint(
                    ConfigScreenHandler.ConfigScreenFactory.class,
                    () -> new ConfigScreenHandler.ConfigScreenFactory(
                            (client, screen) -> {
                                AEQCConfigUI gui = new AEQCConfigUI();
                                gui.setParent(screen);
                                return gui;
                            }
                    )
            );
        }
    }
}
