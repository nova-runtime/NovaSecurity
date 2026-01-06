package de.nova.security.util;

import de.nova.security.NovaSecurity;
import de.nova.security.action.SecurityAction;
import org.bukkit.entity.Player;

public class SecurityUtil {

    public static void handleViolation(
            NovaSecurity plugin,
            Player player,
            SecurityAction action,
            String reason
    ) {
        boolean debug = plugin.getConfig().getBoolean("logging.debug", false);

        if (debug) {
            plugin.getLogger().warning(
                    "Security violation by " + player.getName() + ": " + reason
            );
        }

        switch (action) {
            case WARN -> player.sendMessage(
                    "§c[Security] §7" + reason
            );

            case CANCEL -> {
                // handled by event cancellation
            }

            case KICK -> player.kickPlayer(
                    plugin.getConfig().getString(
                            "messages.kick",
                            "§cInvalid client data detected."
                    )
            );
        }
    }
}
