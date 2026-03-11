package com.github.debris.aeqc.feat;

import net.minecraft.client.Minecraft;

public interface Task {
    /**
     * return true if finished
     */
    boolean execute(Minecraft client);
}
