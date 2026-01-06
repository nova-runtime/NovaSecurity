package de.nova.security.util;

import de.nova.security.NovaSecurity;
import de.nova.security.action.SecurityAction;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class EscalationUtil {

    @SuppressWarnings("unchecked")
    public static SecurityAction resolveAction(
            NovaSecurity plugin,
            int violations,
            SecurityAction defaultAction
    ) {

        if (!plugin.getConfig().getBoolean("security.escalation.enabled", true)) {
            return defaultAction;
        }

        List<?> rawLevels = plugin.getConfig().getList("security.escalation.levels");
        if (rawLevels == null || rawLevels.isEmpty()) {
            return defaultAction;
        }

        return rawLevels.stream()
                .filter(obj -> obj instanceof Map)
                .map(obj -> (Map<String, Object>) obj)
                .sorted(Comparator.comparingInt(
                        map -> ((Number) map.getOrDefault("violations", 0)).intValue()
                ))
                .filter(map -> violations >= ((Number) map.get("violations")).intValue())
                .map(map ->
                        SecurityAction.fromString(
                                String.valueOf(map.get("action"))
                        )
                )
                .reduce((first, second) -> second)
                .orElse(defaultAction);
    }
}
