package com.github.debris.aeqc.neoforge;

import com.github.debris.aeqc.gui.AEQCConfigUI;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import org.thinkingstudio.mafglib.loader.entrypoints.ConfigScreenEntrypoint;

public class ConfigScreenEntrypointImpl implements ConfigScreenEntrypoint {
    @Override
    public IConfigScreenFactory getModConfigScreenFactory() {
        return (container, screen) -> {
            AEQCConfigUI gui = new AEQCConfigUI();
            gui.setParent(screen);
            return gui;
        };
    }
}
