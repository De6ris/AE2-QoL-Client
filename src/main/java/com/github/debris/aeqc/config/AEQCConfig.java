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
    public static final ConfigBoolean PatternPlaceHolder = ofBoolean("样板占位符", true, "假合成");
    public static final ConfigString PlaceHolderItem = ofString("占位符物品", "minecraft:paper");
    public static final ConfigBoolean AutoBlankPatternRestock = ofBoolean("自动补充空白样板", true, "样板编码终端");
    public static final ConfigBoolean DebugMode = ofBoolean("调试模式", false);


    // integration
    public static final ConfigBoolean GTConvertMoltenAlloy = ofBoolean("GT转化熔融合金", true, "对应的锭");
    public static final ConfigBoolean GTConvertHotIngot = ofBoolean("GT转化热锭", true, "对应的锭");


    // hotkey
    public static final ConfigHotkey OpenConfigUI = ofHotkey("打开配置界面", "C,Q");

    public static final ConfigHotkey FastSearch = ofHotkey("快速搜索", "F", KeybindSettings.GUI);
    public static final ConfigHotkey FastAutoCraftGui = ofHotkey("快速下单", "BUTTON_3", KeybindSettings.GUI);
    public static final ConfigHotkey FastAutoCraftBlock = ofHotkey("快速下单方块", "LEFT_CONTROL,BUTTON_3", KeybindSettings.DEFAULT);
    public static final ConfigHotkey ClearSearch = ofHotkey("清空搜索", "C", KeybindSettings.GUI);
    public static final ConfigHotkey ModifierSkipPatternMerging = ofHotkey("跳过样板自动合并", "LEFT_CONTROL", KeybindSettings.MODIFIER_GUI, "保留样板堆叠为jei中原状\n用于GT装配线等");
    public static final ConfigHotkey FastPullOne = ofHotkey("快速拉取一个物品", "HOME", KeybindSettings.GUI);
    public static final ConfigHotkey FastPullStack = ofHotkey("快速拉取一组物品", "END", KeybindSettings.GUI);

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
                PatternPlaceHolder,
                PlaceHolderItem,
                AutoBlankPatternRestock,
                DebugMode
        );
        Integration = getIntegration();
        Hotkey = ImmutableList.of(
                OpenConfigUI,
                FastSearch,
                FastAutoCraftGui,
                FastAutoCraftBlock,
                ClearSearch,
                ModifierSkipPatternMerging,
                FastPullOne,
                FastPullStack
        );

        ImmutableList.Builder<IConfigBase> builder = ImmutableList.builder();
        ALL_CONFIGS = builder.addAll(Values).addAll(Integration).addAll(Hotkey).build();
    }

}
