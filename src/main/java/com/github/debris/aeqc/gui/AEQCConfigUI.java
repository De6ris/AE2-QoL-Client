package com.github.debris.aeqc.gui;

import com.github.debris.aeqc.config.AEQCConfig;
import com.github.debris.aeqc.localization.ConfigScreenText;
import fi.dy.masa.malilib.config.IConfigBase;
import fi.dy.masa.malilib.gui.GuiConfigsBase;
import fi.dy.masa.malilib.gui.button.ButtonBase;
import fi.dy.masa.malilib.gui.button.ButtonGeneric;
import fi.dy.masa.malilib.gui.button.IButtonActionListener;
import fi.dy.masa.malilib.util.StringUtils;

import java.util.List;

import static com.github.debris.aeqc.AEQC.MOD_NAME;
import static com.github.debris.aeqc.AEQC.MOD_VERSION;

public class AEQCConfigUI extends GuiConfigsBase {
    private static Tab tab = Tab.ALL;

    public AEQCConfigUI() {
        super(10, 50, MOD_NAME, null, ConfigScreenText.TITLE.getTranslationKey(), MOD_NAME, MOD_VERSION);
    }

    @Override
    public void initGui() {
        super.initGui();
        this.clearOptions();

        int x = 10;
        int y = 26;
        for (Tab tab : Tab.values()) {
            x += this.createButton(x, y, -1, tab);
        }

    }

    private int createButton(int x, int y, int width, Tab tab) {
        ButtonGeneric button = new ButtonGeneric(x, y, width, 20, StringUtils.translate(tab.getTranslationKey()));
        button.setEnabled(AEQCConfigUI.tab != tab);
        this.addButton(button, new ButtonListener(tab, this));

        return button.getWidth() + 2;
    }

    @Override
    public List<ConfigOptionWrapper> getConfigs() {
        List<? extends IConfigBase> configs;
        Tab tab = AEQCConfigUI.tab;

        configs = switch (tab) {
            case VALUE -> AEQCConfig.Values;
            case INTEGRATION -> AEQCConfig.Integration;
            case HOTKEY -> AEQCConfig.Hotkey;
            default -> AEQCConfig.ALL_CONFIGS;
        };

        return ConfigOptionWrapper.createFor(configs);
    }

    private record ButtonListener(Tab tab, AEQCConfigUI parent) implements IButtonActionListener {
        @SuppressWarnings("DataFlowIssue")
        @Override
        public void actionPerformedWithButton(ButtonBase button, int mouseButton) {
            AEQCConfigUI.tab = this.tab;
            this.parent.reCreateListWidget();
            this.parent.getListWidget().resetScrollbarPosition();
            this.parent.initGui();
        }
    }

    public enum Tab {
        ALL(ConfigScreenText.TAB_ALL.getTranslationKey()),
        VALUE(ConfigScreenText.TAB_VALUE.getTranslationKey()),
        INTEGRATION(ConfigScreenText.TAB_INTEGRATION.getTranslationKey()),
        HOTKEY(ConfigScreenText.TAB_HOTKEY.getTranslationKey()),
        ;

        private final String key;

        Tab(String str) {
            key = str;
        }

        public String getTranslationKey() {
            return this.key;
        }
    }
}
