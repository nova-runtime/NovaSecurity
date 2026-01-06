package de.nova.security.listener;

import de.nova.security.NovaSecurity;
import de.nova.security.action.SecurityAction;
import de.nova.security.util.SecurityUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class InteractSpamListener implements Listener {

    private final NovaSecurity plugin;

    // UUID -> interaction window
    private final static Map<UUID, InteractWindow> interactMap = new HashMap<>();

    public InteractSpamListener(NovaSecurity plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (player.hasPermission("novasecurity.bypass")) return;

        Action action = event.getAction();
        if (action == Action.PHYSICAL) return; // pressure plates etc.

        if (check(player)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onEntityInteract(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        if (player.hasPermission("novasecurity.bypass")) return;

        if (check(player)) {
            event.setCancelled(true);
        }
    }

    private boolean check(Player player) {
        int max = plugin.getConfig()
                .getInt("security.interact.max-per-second", 20);

        long windowMs = plugin.getConfig()
                .getLong("security.interact.window-ms", 1000);

        SecurityAction action = SecurityAction.fromString(
                plugin.getConfig().getString("security.interact.action", "CANCEL")
        );

        UUID uuid = player.getUniqueId();
        long now = System.currentTimeMillis();

        InteractWindow window = interactMap.get(uuid);

        if (window == null || now - window.windowStart > windowMs) {
            window = new InteractWindow(now);
            interactMap.put(uuid, window);
        }

        window.count++;

        if (window.count > max) {
            SecurityUtil.handleViolation(
                    plugin,
                    player,
                    action,
                    "Interact spam detected"
            );
            return true;
        }

        return false;
    }

    private static class InteractWindow {
        int count = 0;
        long windowStart;

        InteractWindow(long start) {
            this.windowStart = start;
        }
    }

    public static void cleanup(UUID uuid) {
        interactMap.remove(uuid);
    }

}
