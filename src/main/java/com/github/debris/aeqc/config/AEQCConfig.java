package com.github.debris.aeqc.config;

import com.github.debris.aeqc.AEQC;
import com.github.debris.aeqc.util.Platform;
import com.google.common.collect.ImmutableList;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import fi.dy.masa.malilib.config.ConfigUtils;
import fi.dy.masa.malilib.config.IConfigBase;
import fi.dy.masa.malilib.config.IConfigHandler;
import fi.dy.masa.malilib.config.options.ConfigBoolean;
import fi.dy.masa.malilib.config.options.ConfigHotkey;
import fi.dy.masa.malilib.hotkeys.IHotkey;
import fi.dy.masa.malilib.hotkeys.KeybindSettings;
import fi.dy.masa.malilib.util.JsonUtils;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

import static com.github.debris.aeqc.AEQC.MOD_NAME;
import static com.github.debris.aeqc.config.ConfigFactory.ofBoolean;
import static com.github.debris.aeqc.config.ConfigFactory.ofHotkey;

public class AEQCConfig implements IConfigHandler {
    public static final AEQCConfig INSTANCE = new AEQCConfig();
    private static final Path FILE_PATH = Platform.getConfigDir().resolve(AEQC.MOD_ID + ".json");

    // value
    public static final ConfigBoolean DebugMode = ofBoolean("调试模式", false);


    // compat


    // hotkey
    public static final ConfigHotkey OpenConfigUI = ofHotkey("打开配置界面", "C,Q");

    public static final ConfigHotkey FastSearch = ofHotkey("快速搜索", "F", KeybindSettings.GUI);
    public static final ConfigHotkey FastAutoCraftGui = ofHotkey("快速下单", "BUTTON_3", KeybindSettings.GUI);
    public static final ConfigHotkey FastAutoCraftBlock = ofHotkey("快速下单方块", "LEFT_CONTROL,BUTTON_3", KeybindSettings.DEFAULT);
    public static final ConfigHotkey ClearSearch = ofHotkey("清空搜索", "C", KeybindSettings.GUI);

    public static final List<IConfigBase> ALL_CONFIGS;


    public static final ImmutableList<IConfigBase> Values;
    public static final ImmutableList<IConfigBase> Compat;
    public static final ImmutableList<IHotkey> Hotkey;


    public static AEQCConfig getInstance() {
        return INSTANCE;
    }

    @Override
    public void load() {
        File settingFile = FILE_PATH.toFile();
        if (settingFile.isFile() && settingFile.exists()) {
            JsonElement jsonElement = JsonUtils.parseJsonFile(settingFile);
            if (jsonElement != null && jsonElement.isJsonObject()) {
                JsonObject obj = jsonElement.getAsJsonObject();
                ConfigUtils.readConfigBase(obj, MOD_NAME, ALL_CONFIGS);
            }
        }
    }

    @Override
    public void save() {
        File folder = Platform.getConfigDir().toFile();
        if ((folder.exists() && folder.isDirectory()) || folder.mkdirs()) {
            JsonObject configRoot = new JsonObject();
            ConfigUtils.writeConfigBase(configRoot, MOD_NAME, ALL_CONFIGS);
            JsonUtils.writeJsonToFile(configRoot, FILE_PATH.toFile());
        }
    }

    private static ImmutableList<IConfigBase> getCompat() {
        ImmutableList.Builder<IConfigBase> builder = ImmutableList.builder();
        return builder.build();
    }

    static {
        Values = ImmutableList.of(DebugMode);
        Compat = getCompat();
        Hotkey = ImmutableList.of(OpenConfigUI, FastSearch, FastAutoCraftGui, FastAutoCraftBlock, ClearSearch);

        ImmutableList.Builder<IConfigBase> builder = ImmutableList.builder();
        ALL_CONFIGS = builder.addAll(Values).addAll(Compat).addAll(Hotkey).build();
    }

}
