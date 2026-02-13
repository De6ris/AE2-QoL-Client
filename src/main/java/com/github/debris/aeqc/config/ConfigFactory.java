package com.github.debris.aeqc.config;

import com.google.common.collect.ImmutableList;
import fi.dy.masa.malilib.config.options.*;
import fi.dy.masa.malilib.hotkeys.KeybindSettings;

public class ConfigFactory {
    private static final String DEFAULT_COMMENT = "no comment";

    static ConfigStringList ofStringList(String name, ImmutableList<String> defaultValue) {
        return ofStringList(name, defaultValue, DEFAULT_COMMENT);
    }

    static ConfigStringList ofStringList(String name, ImmutableList<String> defaultValue, String comment) {
        return new ConfigStringList(name, defaultValue, comment);
    }

    static ConfigInteger ofInteger(String name, int defaultValue, int min, int max) {
        return ofInteger(name, defaultValue, min, max, DEFAULT_COMMENT);
    }

    static ConfigInteger ofInteger(String name, int defaultValue, int min, int max, String comment) {
        return new ConfigInteger(name, defaultValue, min, max, comment);
    }

    static ConfigBoolean ofBoolean(String name, boolean defaultValue) {
        return ofBoolean(name, defaultValue, DEFAULT_COMMENT);
    }

    static ConfigBoolean ofBoolean(String name, boolean defaultValue, String comment) {
        return new ConfigBoolean(name, defaultValue, comment);
    }

    static ConfigHotkey ofHotkey(String name, String defaultKey) {
        return ofHotkey(name, defaultKey, KeybindSettings.DEFAULT, DEFAULT_COMMENT);
    }

    static ConfigHotkey ofHotkey(String name, String defaultKey, String comment) {
        return ofHotkey(name, defaultKey, KeybindSettings.DEFAULT, comment);
    }

    static ConfigHotkey ofHotkey(String name, String defaultKey, KeybindSettings settings) {
        return new ConfigHotkey(name, defaultKey, settings, DEFAULT_COMMENT);
    }

    static ConfigHotkey ofHotkey(String name, String defaultKey, KeybindSettings settings, String comment) {
        return new ConfigHotkey(name, defaultKey, settings, comment);
    }

    static ConfigBooleanHotkeyed ofBooleanHotkeyed(String name, boolean defaultValue, String defaultKey) {
        return ofBooleanHotkeyed(name, defaultValue, defaultKey, DEFAULT_COMMENT);
    }

    static ConfigBooleanHotkeyed ofBooleanHotkeyed(String name, boolean defaultValue, String defaultKey, String comment) {
        return new ConfigBooleanHotkeyed(name, defaultValue, defaultKey, comment);
    }

    static ConfigBooleanHotkeyed ofBooleanHotkeyed(String name, boolean defaultValue, String defaultKey, KeybindSettings settings, String comment) {
        return new ConfigBooleanHotkeyed(name, defaultValue, defaultKey, settings, comment, name);
    }
}
