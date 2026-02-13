package com.github.debris.aeqc.mixin.mod.mafglib;

import fi.dy.masa.malilib.gui.GuiTextFieldGeneric;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = GuiTextFieldGeneric.class, remap = false)
public abstract class GuiTextFieldGenericMixin extends EditBox {
    public GuiTextFieldGenericMixin(Font arg, int i, int j, int k, int l, Component arg2) {
        super(arg, i, j, k, l, arg2);
    }

    /**
     * @author
     * @reason fix a strange stack overflow in dev environment
     */
    @Overwrite(remap = true)
    public void setCursorPosition(int pos) {
        super.setCursorPosition(pos);
    }
}
