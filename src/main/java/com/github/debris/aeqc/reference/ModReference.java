package com.github.debris.aeqc.reference;

import com.github.debris.aeqc.util.Platform;

public class ModReference {
    public static final String TomStorage = "toms_storage";

    public static boolean has(String modid) {
        return Platform.hasMod(modid);
    }
}
