package com.github.debris.aeqc.gui.button;

import appeng.client.gui.Icon;
import appeng.client.gui.widgets.IconButton;
import com.github.debris.aeqc.localization.TooltipText;

public class BookmarkMissingButton extends IconButton {
    public BookmarkMissingButton(OnPress onPress) {
        super(onPress);
        this.setMessage(TooltipText.BOOKMARK_MISSING.text());
    }

    @Override
    protected Icon getIcon() {
        return Icon.PATTERN_ACCESS_SHOW;
    }
}
