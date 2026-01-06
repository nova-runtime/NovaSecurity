package de.nova.security.listener;

import de.nova.security.NovaSecurity;
import de.nova.security.action.SecurityAction;
import de.nova.security.util.SecurityUtil;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class InventorySpamListener implements Listener {

    private final NovaSecurity plugin;

    // UUID -> click window
    private final static Map<UUID, ClickWindow> clickMap = new HashMap<>();

    public InventorySpamListener(NovaSecurity plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player player)) return;
        if (player.hasPermission("novasecurity.bypass")) return;

        int maxClicks = plugin.getConfig()
                .getInt("security.inventory.max-clicks", 30);

        long windowMs = plugin.getConfig()
                .getLong("security.inventory.window-ms", 1000);

        SecurityAction action = SecurityAction.fromString(
                plugin.getConfig().getString("security.inventory.action", "WARN")
        );

        UUID uuid = player.getUniqueId();
        long now = System.currentTimeMillis();

        ClickWindow window = clickMap.get(uuid);

        if (window == null || now - window.windowStart > windowMs) {
            window = new ClickWindow(now);
            clickMap.put(uuid, window);
        }

        window.count++;

        if (window.count > maxClicks) {
            event.setCancelled(true);
            SecurityUtil.handleViolation(
                    plugin,
                    player,
                    action,
                    "Inventory click spam detected"
            );
        }
    }

    private static class ClickWindow {
        int count = 0;
        long windowStart;

        ClickWindow(long start) {
            this.windowStart = start;
        }
    }

    public static void cleanup(UUID uuid) {
    	clickMap.remove(uuid);
    }

}
