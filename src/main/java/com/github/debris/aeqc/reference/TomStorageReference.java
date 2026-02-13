package com.github.debris.aeqc.reference;

import com.github.debris.aeqc.util.Platform;
import net.minecraft.resources.ResourceLocation;

import java.util.List;

public class TomStorageReference {
    public static final List<ResourceLocation> Terminals =
            List.of(
                    Platform.identifier(ModReference.TomStorage, "ts.storage_terminal.container"),
                    Platform.identifier(ModReference.TomStorage, "ts.crafting_terminal.container")
            );
}
