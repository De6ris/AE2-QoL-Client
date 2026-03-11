package com.github.debris.aeqc.util;

import appeng.api.stacks.AEKey;
import appeng.menu.me.common.GridInventoryEntry;
import appeng.menu.me.common.IClientRepo;
import com.google.common.base.Predicates;

import java.util.Optional;
import java.util.function.Predicate;

public class AE2Util {
    public static Optional<GridInventoryEntry> findEntry(IClientRepo repo, AEKey key) {
        return findEntry(repo, key, Predicates.alwaysTrue());
    }

    public static Optional<GridInventoryEntry> findEntry(IClientRepo repo, AEKey key, Predicate<GridInventoryEntry> predicate) {
        return repo.getAllEntries().stream()
                .filter(entry -> {
                    AEKey what = entry.getWhat();
                    if (what == null) return false;
                    return what.equals(key);
                })
                .filter(predicate)
                .findFirst();
    }
}
