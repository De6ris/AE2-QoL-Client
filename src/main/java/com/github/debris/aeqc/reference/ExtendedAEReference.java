package com.github.debris.aeqc.reference;

import appeng.core.AppEng;
import net.minecraft.resources.ResourceLocation;

import java.util.List;

public class ExtendedAEReference {
    public static final List<ResourceLocation> PatternAccessTerminals =
            List.of(
                    AppEng.makeId("ex_pattern_access_terminal"),
                    AppEng.makeId("wireless_ex_pat"),
                    AppEng.makeId("u_wireless_ex_pattern_access_terminal")
            );
}
