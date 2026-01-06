package de.nova.security.util;

import de.nova.security.NovaSecurity;
import de.nova.security.action.SecurityAction;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SecurityUtil {

    private static final Map<UUID, Long> cooldownMap = new HashMap<>();

    public static void handleViolation(
            NovaSecurity plugin,
            Player player,
            SecurityAction action,
            String reason
    ) {

        if (player.hasPermission("novasecurity.bypass")) return;

        long now = System.currentTimeMillis();

        if (plugin.getConfig().getBoolean("security.cooldown.enabled", true)) {
            long last = cooldownMap.getOrDefault(player.getUniqueId(), 0L);
            long cooldown = plugin.getConfig().getLong("security.cooldown.time-ms", 5000);
            if (now - last < cooldown) return;
            cooldownMap.put(player.getUniqueId(), now);
        }

        ViolationCounter.increment(player.getUniqueId());
        int violations = ViolationCounter.get(player.getUniqueId());

        SecurityAction finalAction = EscalationUtil.resolveAction(
                plugin,
                violations,
                action
        );

        if (plugin.getConfig().getBoolean("logging.debug", false)) {
            plugin.getLogger().warning(
                    "[NovaSecurity] " + player.getName()
                            + " (" + violations + "): "
                            + reason + " -> " + finalAction
            );
        }

        switch (finalAction) {
            case WARN -> player.sendMessage("§c[Security] §7" + reason);
            case CANCEL -> { }
            case KICK -> player.kickPlayer(
                    plugin.getConfig().getString(
                            "messages.kick",
                            "§cInvalid client data detected."
                    )
            );
        }
    }
}
