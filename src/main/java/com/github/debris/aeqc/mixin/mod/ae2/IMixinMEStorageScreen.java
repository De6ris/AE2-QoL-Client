package com.github.debris.aeqc.mixin.mod.ae2;

import appeng.client.gui.me.common.MEStorageScreen;
import appeng.client.gui.me.common.Repo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value = MEStorageScreen.class, remap = false)
public interface IMixinMEStorageScreen {
    @Accessor(remap = false)
    Repo getRepo();
}
