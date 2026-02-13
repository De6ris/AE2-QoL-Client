package com.github.debris.aeqc.feat;

import net.minecraft.client.Minecraft;

import java.util.ArrayList;
import java.util.List;

public class TaskQueue {
    private static int COUNTER = 0;
    private static final List<Entry> ENTRIES = new ArrayList<>();

    public static void tick(Minecraft client) {
        if (client.level == null || client.player == null) {
            ENTRIES.clear();
        }
        ENTRIES.removeIf(entry -> entry.task.execute(client));
        ENTRIES.removeIf(entry -> entry.time == COUNTER);

        COUNTER++;
    }

    public static void schedule(Task task, int time) {
        ENTRIES.add(new Entry(task, COUNTER + time));
    }

    private record Entry(Task task, int time) {
    }
}
