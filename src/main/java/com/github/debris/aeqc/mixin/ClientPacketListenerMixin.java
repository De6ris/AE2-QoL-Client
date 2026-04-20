package com.github.debris.aeqc.mixin;

import com.github.debris.aeqc.feat.Hooks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientCommonPacketListenerImpl;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.client.multiplayer.CommonListenerCookie;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundContainerSetContentPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPacketListener.class)
public abstract class ClientPacketListenerMixin extends ClientCommonPacketListenerImpl {

    protected ClientPacketListenerMixin(Minecraft arg, Connection arg2, CommonListenerCookie arg3) {
        super(arg, arg2, arg3);
    }

    @Inject(method = "handleContainerContent", at = @At("RETURN"))
    private void hook(ClientboundContainerSetContentPacket arg, CallbackInfo ci) {
        Hooks.onContainerInitializeContents(this.minecraft, arg.getContainerId());
    }
}
