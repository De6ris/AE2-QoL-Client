package com.github.debris.aeqc.reference;

import com.github.debris.aeqc.util.Platform;
import com.google.common.base.Suppliers;
import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.ModList;
import org.apache.maven.artifact.versioning.DefaultArtifactVersion;

import java.util.Optional;
import java.util.function.Supplier;

public class ModReference {
    public static final String GTCEU = "gtceu";

    public static final Supplier<Boolean> IS_GTCEU_1_4_4 = Suppliers.memoize(() -> {
        Optional<? extends ModContainer> optional = ModList.get().getModContainerById(ModReference.GTCEU);
        return optional.isPresent() && optional.get().getModInfo().getVersion().compareTo(new DefaultArtifactVersion("1.4.4")) == 0;
    });

    public static boolean has(String modid) {
        return Platform.hasMod(modid);
    }
}
