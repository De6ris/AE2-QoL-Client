package com.github.debris.aeqc.localization;

import appeng.core.localization.LocalizationEnum;

public enum ConfigScreenText implements LocalizationEnum {
    TITLE("aeqc.gui.title.configs", "%s %s Configs"),
    TAB_ALL("aeqc.gui.tab.all", "All"),
    TAB_VALUE("aeqc.gui.tab.value", "Value"),
    TAB_INTEGRATION("aeqc.gui.tab.integration", "Integration"),
    TAB_HOTKEY("aeqc.gui.tab.hotkey", "Hotkey"),
    ;

    private final String key;
    private final String englishText;

    ConfigScreenText(String key, String englishText) {
        this.key = key;
        this.englishText = englishText;
    }

    @Override
    public String getEnglishText() {
        return this.englishText;
    }

    @Override
    public String getTranslationKey() {
        return this.key;
    }
}
