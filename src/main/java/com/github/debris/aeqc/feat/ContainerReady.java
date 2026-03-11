package com.github.debris.aeqc.feat;

import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;

public class ContainerReady {
    private static int readyContainerId = -1;

    public static void setReady(int containerId) {
        readyContainerId = containerId;
    }

    public static boolean isReady(int containerId) {
        return readyContainerId == containerId;
    }

    public static boolean isReady(AbstractContainerScreen<?> screen) {
        return isReady(screen.getMenu().containerId);
    }
}
