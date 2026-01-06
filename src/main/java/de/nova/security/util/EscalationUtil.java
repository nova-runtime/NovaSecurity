package de.nova.security.util;

import de.nova.security.NovaSecurity;
import de.nova.security.action.SecurityAction;
import org.bukkit.configuration.ConfigurationSection;

import java.util.Comparator;
import java.util.List;

public class EscalationUtil {

    public static SecurityAction resolveAction(
            NovaSecurity plugin,
            int violations,
            SecurityAction defaultAction
    ) {
        if (!plugin.getConfig().getBoolean("security.escalation.enabled", true)) {
            return defaultAction;
        }

        List<?> levels = plugin.getConfig().getList("security.escalation.levels");
        if (levels == null) return defaultAction;

        return levels.stream()
                .map(obj -> (ConfigurationSection) obj)
                .sorted(Comparator.comparingInt(
                        s -> s.getInt("violations")
                ))
                .filter(section -> violations >= section.getInt("violations"))
                .map(section ->
                        SecurityAction.fromString(section.getString("action"))
                )
                .reduce((first, second) -> second)
                .orElse(defaultAction);
    }
}
