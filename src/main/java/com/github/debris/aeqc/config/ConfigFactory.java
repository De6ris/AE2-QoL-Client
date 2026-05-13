package com.github.debris.aeqc.config;

import com.google.common.collect.ImmutableList;
import fi.dy.masa.malilib.config.options.*;
import fi.dy.masa.malilib.hotkeys.KeybindSettings;

public class ConfigFactory {
    private static final String NAME_TRANSLATION_PREFIX = "aeqc.config.name.";
    private static final String COMMENT_TRANSLATION_PREFIX = "aeqc.config.comment.";

    static ConfigStringList ofStringList(String name, ImmutableList<String> defaultValue) {
        return ofStringList(name, defaultValue, name);
    }

    static ConfigStringList ofStringList(String name, ImmutableList<String> defaultValue, String comment) {
        return new ConfigStringList(NAME_TRANSLATION_PREFIX + name, defaultValue, COMMENT_TRANSLATION_PREFIX + comment);
    }

    static ConfigInteger ofInteger(String name, int defaultValue, int min, int max) {
        return ofInteger(name, defaultValue, min, max, name);
    }

    static ConfigInteger ofInteger(String name, int defaultValue, int min, int max, String comment) {
        return new ConfigInteger(NAME_TRANSLATION_PREFIX + name, defaultValue, min, max, COMMENT_TRANSLATION_PREFIX + comment);
    }

    static ConfigBoolean ofBoolean(String name, boolean defaultValue) {
        return ofBoolean(name, defaultValue, name);
    }

    static ConfigBoolean ofBoolean(String name, boolean defaultValue, String comment) {
        return new ConfigBoolean(NAME_TRANSLATION_PREFIX + name, defaultValue, COMMENT_TRANSLATION_PREFIX + comment);
    }

    static ConfigHotkey ofHotkey(String name, String defaultKey) {
        return ofHotkey(name, defaultKey, KeybindSettings.DEFAULT, name);
    }

    static ConfigHotkey ofHotkey(String name, String defaultKey, String comment) {
        return ofHotkey(name, defaultKey, KeybindSettings.DEFAULT, comment);
    }

    static ConfigHotkey ofHotkey(String name, String defaultKey, KeybindSettings settings) {
        return ofHotkey(name, defaultKey, settings, name);
    }

    static ConfigHotkey ofHotkey(String name, String defaultKey, KeybindSettings settings, String comment) {
        return new ConfigHotkey(NAME_TRANSLATION_PREFIX + name, defaultKey, settings, COMMENT_TRANSLATION_PREFIX + comment);
    }

    static ConfigBooleanHotkeyed ofBooleanHotkeyed(String name, boolean defaultValue, String defaultKey) {
        return ofBooleanHotkeyed(name, defaultValue, defaultKey, name);
    }

    static ConfigBooleanHotkeyed ofBooleanHotkeyed(String name, boolean defaultValue, String defaultKey, String comment) {
        return ofBooleanHotkeyed(name, defaultValue, defaultKey, KeybindSettings.DEFAULT, comment);
    }

    static ConfigBooleanHotkeyed ofBooleanHotkeyed(String name, boolean defaultValue, String defaultKey, KeybindSettings settings, String comment) {
        return new ConfigBooleanHotkeyed(NAME_TRANSLATION_PREFIX + name, defaultValue, defaultKey, settings, COMMENT_TRANSLATION_PREFIX + comment, NAME_TRANSLATION_PREFIX + name);
    }

    static ConfigString ofString(String name, String defaultValue) {
        return new ConfigString(NAME_TRANSLATION_PREFIX + name, defaultValue, COMMENT_TRANSLATION_PREFIX + name);
    }
}
