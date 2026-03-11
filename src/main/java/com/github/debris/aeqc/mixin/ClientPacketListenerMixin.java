package com.github.debris.aeqc.mixin;

import com.github.debris.aeqc.feat.Hooks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.network.protocol.game.ClientboundContainerSetContentPacket;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPacketListener.class)
public class ClientPacketListenerMixin {
    @Shadow
    @Final
    private Minecraft minecraft;

    @Inject(method = "handleContainerContent", at = @At("RETURN"))
    private void hook(ClientboundContainerSetContentPacket arg, CallbackInfo ci) {
        Hooks.onContainerInitializeContents(this.minecraft, arg.getContainerId());
    }
}
