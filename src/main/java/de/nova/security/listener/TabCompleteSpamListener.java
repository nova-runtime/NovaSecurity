package de.nova.security.listener;

import de.nova.security.NovaSecurity;
import de.nova.security.action.SecurityAction;
import de.nova.security.util.SecurityUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.TabCompleteEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TabCompleteSpamListener implements Listener {

    private final NovaSecurity plugin;

    // UUID -> tab window
    private final static Map<UUID, TabWindow> tabMap = new HashMap<>();

    public TabCompleteSpamListener(NovaSecurity plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onTabComplete(TabCompleteEvent event) {
        if (!(event.getSender() instanceof Player player)) return;
        if (player.hasPermission("novasecurity.bypass")) return;

        int maxRequests = plugin.getConfig()
                .getInt("security.tabcomplete.max-per-second", 15);

        long windowMs = plugin.getConfig()
                .getLong("security.tabcomplete.window-ms", 1000);

        SecurityAction action = SecurityAction.fromString(
                plugin.getConfig().getString("security.tabcomplete.action", "CANCEL")
        );

        UUID uuid = player.getUniqueId();
        long now = System.currentTimeMillis();

        TabWindow window = tabMap.get(uuid);

        if (window == null || now - window.windowStart > windowMs) {
            window = new TabWindow(now);
            tabMap.put(uuid, window);
        }

        window.count++;

        if (window.count > maxRequests) {
            event.setCancelled(true);
            event.setCompletions(java.util.Collections.emptyList());

            SecurityUtil.handleViolation(
                    plugin,
                    player,
                    action,
                    "Tab-complete spam detected"
            );
        }
    }

    private static class TabWindow {
        int count = 0;
        long windowStart;

        TabWindow(long start) {
            this.windowStart = start;
        }
    }

    public static void cleanup(UUID uuid) {
        tabMap.remove(uuid);
    }

}
