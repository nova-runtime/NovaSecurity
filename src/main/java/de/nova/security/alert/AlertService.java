package de.nova.security.alert;

import de.nova.security.NovaSecurity;
import de.nova.security.action.SecurityAction;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class AlertService {

    private static final Set<UUID> disabledAlerts = new HashSet<>();

    public static void toggle(Player player) {
        UUID uuid = player.getUniqueId();

        if (disabledAlerts.contains(uuid)) {
            disabledAlerts.remove(uuid);
            player.sendMessage("§aNovaSecurity alerts enabled.");
        } else {
            disabledAlerts.add(uuid);
            player.sendMessage("§cNovaSecurity alerts disabled.");
        }
    }

    public static void send(
            NovaSecurity plugin,
            Player offender,
            String reason,
            int violations,
            SecurityAction action
    ) {

        if (!plugin.getConfig().getBoolean("alerts.enabled", true)) {
            return;
        }

        String message = plugin.getConfig().getString(
                "alerts.format",
                "§8[§bNovaSecurity§8] §f{player} §7→ §c{reason} §8(| {violations}, {action}, {world})"
        );

        message = message
                .replace("{player}", offender.getName())
                .replace("{reason}", reason)
                .replace("{violations}", String.valueOf(violations))
                .replace("{action}", action.name())
                .replace("{world}", offender.getWorld().getName());

        for (CommandSender sender : Bukkit.getOnlinePlayers()) {
            if (!(sender instanceof Player admin)) continue;
            if (!admin.hasPermission("novasecurity.alerts")) continue;
            if (disabledAlerts.contains(admin.getUniqueId())) continue;

            admin.sendMessage(message);
        }
    }
}
