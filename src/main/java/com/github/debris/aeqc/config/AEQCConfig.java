package com.github.debris.aeqc.config;

import com.github.debris.aeqc.AEQC;
import com.github.debris.aeqc.reference.ModReference;
import com.github.debris.aeqc.util.Platform;
import com.google.common.collect.ImmutableList;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import fi.dy.masa.malilib.config.ConfigUtils;
import fi.dy.masa.malilib.config.IConfigBase;
import fi.dy.masa.malilib.config.IConfigHandler;
import fi.dy.masa.malilib.config.options.ConfigBoolean;
import fi.dy.masa.malilib.config.options.ConfigHotkey;
import fi.dy.masa.malilib.config.options.ConfigString;
import fi.dy.masa.malilib.hotkeys.IHotkey;
import fi.dy.masa.malilib.hotkeys.KeybindSettings;
import fi.dy.masa.malilib.util.JsonUtils;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

import static com.github.debris.aeqc.AEQC.MOD_NAME;
import static com.github.debris.aeqc.config.ConfigFactory.*;

public class AEQCConfig implements IConfigHandler {
    public static final AEQCConfig INSTANCE = new AEQCConfig();
    private static final Path FILE_PATH = Platform.getConfigDir().resolve(AEQC.MOD_ID + ".json");

    // value
    public static final ConfigBoolean PatternPlaceholder = ofBoolean("pattern_placeholder", true);
    public static final ConfigString PlaceholderItem = ofString("placeholder_item", "minecraft:paper");
    public static final ConfigBoolean AutoBlankPatternRestock = ofBoolean("auto_blank_pattern_restock", true);
    public static final ConfigBoolean DebugMode = ofBoolean("debug_mode", false);


    // integration
    public static final ConfigBoolean GTConvertMoltenAlloy = ofBoolean("gt_convert_molten_alloy", true);
    public static final ConfigBoolean GTConvertHotIngot = ofBoolean("gt_convert_hot_ingot", true);


    // hotkey
    public static final ConfigHotkey OpenConfigUI = ofHotkey("open_config_ui", "C,Q");

    public static final ConfigHotkey FastSearch = ofHotkey("fast_search", "F", KeybindSettings.GUI);
    public static final ConfigHotkey FastAutoCraft = ofHotkey("fast_auto_craft", "BUTTON_3", KeybindSettings.GUI);
    public static final ConfigHotkey ClearSearch = ofHotkey("clear_search", "C", KeybindSettings.GUI);
    public static final ConfigHotkey ModifierSkipPatternMerging = ofHotkey("skip_pattern_merging", "LEFT_CONTROL", KeybindSettings.MODIFIER_GUI);
    public static final ConfigHotkey FastPullOne = ofHotkey("fast_pull_one", "HOME", KeybindSettings.GUI);
    public static final ConfigHotkey FastPullStack = ofHotkey("fast_pull_stack", "END", KeybindSettings.GUI);

    public static final List<IConfigBase> ALL_CONFIGS;


    public static final ImmutableList<IConfigBase> Values;
    public static final ImmutableList<IConfigBase> Integration;
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

    private static ImmutableList<IConfigBase> getIntegration() {
        ImmutableList.Builder<IConfigBase> builder = ImmutableList.builder();
        if (ModReference.has(ModReference.GTCEU)) builder.add(GTConvertMoltenAlloy, GTConvertHotIngot);
        return builder.build();
    }

    static {
        Values = ImmutableList.of(
                PatternPlaceholder,
                PlaceholderItem,
                AutoBlankPatternRestock,
                DebugMode
        );
        Integration = getIntegration();
        Hotkey = ImmutableList.of(
                OpenConfigUI,
                FastSearch,
                FastAutoCraft,
                ClearSearch,
                ModifierSkipPatternMerging,
                FastPullOne,
                FastPullStack
        );

        ImmutableList.Builder<IConfigBase> builder = ImmutableList.builder();
        ALL_CONFIGS = builder.addAll(Values).addAll(Integration).addAll(Hotkey).build();
    }

}
