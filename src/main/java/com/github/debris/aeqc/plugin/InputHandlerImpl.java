package com.github.debris.aeqc.plugin;

import com.github.debris.aeqc.AEQC;
import com.github.debris.aeqc.config.AEQCConfig;
import fi.dy.masa.malilib.hotkeys.IKeybindManager;
import fi.dy.masa.malilib.hotkeys.IKeybindProvider;

public class InputHandlerImpl implements IKeybindProvider {
    @Override
    public void addKeysToMap(IKeybindManager iKeybindManager) {
        AEQCConfig.Hotkey.forEach(x -> iKeybindManager.addKeybindToMap(x.getKeybind()));
    }

    @Override
    public void addHotkeys(IKeybindManager iKeybindManager) {
        iKeybindManager.addHotkeysForCategory(AEQC.MOD_NAME, "热键", AEQCConfig.Hotkey);
    }
}
