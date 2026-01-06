package de.nova.security.util;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ViolationCounter {

    private static final Map<UUID, Integer> violations = new HashMap<>();

    public static void increment(UUID uuid) {
        violations.put(uuid, get(uuid) + 1);
    }

    public static int get(UUID uuid) {
        return violations.getOrDefault(uuid, 0);
    }

    public static void reset(UUID uuid) {
        violations.remove(uuid);
    }

    public static void clearAll() {
        violations.clear();
    }
}
