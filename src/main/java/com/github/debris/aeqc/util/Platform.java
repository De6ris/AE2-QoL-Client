package com.github.debris.aeqc.util;

import net.minecraft.resources.ResourceLocation;
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.fml.loading.FMLPaths;

import java.nio.file.Path;

public class Platform {
    public static Path getConfigDir() {
        return FMLPaths.CONFIGDIR.get();
    }

    public static boolean isClient() {
        return FMLLoader.getDist().isClient();
    }

    public static boolean hasMod(String modid) {
        return ModList.get().isLoaded(modid);
    }

    public static ResourceLocation identifier(String namespace, String path) {
        return ResourceLocation.fromNamespaceAndPath(namespace, path);
    }
}
