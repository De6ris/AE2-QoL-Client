package com.github.debris.aeqc.localization;

import appeng.core.localization.LocalizationEnum;

public enum TooltipText implements LocalizationEnum {
    BOOKMARK_MISSING("aeqc.gui.bookmark_missing", "Bookmark Missing"),
    SKIP_MERGING("aeqc.gui.skip_merging", "Skip Merging")
    ;

    private final String key;
    private final String englishText;

    TooltipText(String key, String englishText) {
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
