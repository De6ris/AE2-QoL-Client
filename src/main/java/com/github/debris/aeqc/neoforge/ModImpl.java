package com.github.debris.aeqc.neoforge;

import com.github.debris.aeqc.AEQC;
import com.github.debris.aeqc.plugin.InitHandlerImpl;
import com.github.debris.aeqc.util.Platform;
import fi.dy.masa.malilib.event.InitializationHandler;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import org.thinkingstudio.mafglib.loader.entrypoints.ConfigScreenEntrypoint;

@Mod(AEQC.MOD_ID)
public class ModImpl {
    public ModImpl() {
        if (Platform.isClient()) {
            InitializationHandler.getInstance().registerInitializationHandler(new InitHandlerImpl());

            ModContainer modContainer = ModList.get().getModContainerById(AEQC.MOD_ID).orElseThrow();

            modContainer.registerExtensionPoint(ConfigScreenEntrypoint.class, new ConfigScreenEntrypointImpl());
        }
    }
}
