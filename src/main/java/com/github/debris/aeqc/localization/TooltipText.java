package com.github.debris.aeqc.localization;

import appeng.core.localization.LocalizationEnum;
import com.github.debris.aeqc.AEQC;

public enum TooltipText implements LocalizationEnum {
    BOOKMARK_MISSING("Bookmark Missing"),
    ;

    private final String englishText;

    TooltipText(String englishText) {
        this.englishText = englishText;
    }

    @Override
    public String getTranslationKey() {
        return AEQC.MOD_ID + ".gui." + this.name().toLowerCase();
    }

    @Override
    public String getEnglishText() {
        return this.englishText;
    }
}
