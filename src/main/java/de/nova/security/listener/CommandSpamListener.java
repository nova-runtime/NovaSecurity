package de.nova.security.listener;

import de.nova.security.NovaSecurity;
import de.nova.security.action.SecurityAction;
import de.nova.security.util.SecurityUtil;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CommandSpamListener implements Listener {

    private final NovaSecurity plugin;

    // UUID -> [count, windowStart]
    private final Map<UUID, CommandWindow> commandMap = new HashMap<>();

    public CommandSpamListener(NovaSecurity plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        if (event.getPlayer().hasPermission("novasecurity.bypass")) return;

        int maxCommands = plugin.getConfig()
                .getInt("security.command.max-per-second", 10);

        long windowMs = plugin.getConfig()
                .getLong("security.command.window-ms", 1000);

        SecurityAction action = SecurityAction.fromString(
                plugin.getConfig().getString("security.command.action", "WARN")
        );

        UUID uuid = event.getPlayer().getUniqueId();
        long now = System.currentTimeMillis();

        CommandWindow window = commandMap.get(uuid);

        if (window == null || now - window.windowStart > windowMs) {
            window = new CommandWindow(now);
            commandMap.put(uuid, window);
        }

        window.count++;

        if (window.count > maxCommands) {
            event.setCancelled(true);
            SecurityUtil.handleViolation(
                    plugin,
                    event.getPlayer(),
                    action,
                    "Command spam detected"
            );
        }
    }

    private static class CommandWindow {
        int count = 0;
        long windowStart;

        CommandWindow(long start) {
            this.windowStart = start;
        }
    }
}
