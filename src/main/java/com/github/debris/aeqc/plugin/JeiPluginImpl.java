package com.github.debris.aeqc.plugin;

import com.github.debris.aeqc.AEQC;
import com.github.debris.aeqc.util.JeiUtil;
import com.github.debris.aeqc.util.Platform;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.runtime.IJeiRuntime;
import net.minecraft.resources.ResourceLocation;

@JeiPlugin
public class JeiPluginImpl implements IModPlugin {
    private static final ResourceLocation ID = Platform.identifier(AEQC.MOD_ID, "core");

    public JeiPluginImpl() {
    }

    @Override
    public ResourceLocation getPluginUid() {
        return ID;
    }

    @Override
    public void onRuntimeAvailable(IJeiRuntime jeiRuntime) {
        JeiUtil.jeiRuntime = jeiRuntime;
    }
}
