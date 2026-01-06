package de.nova.security.util;

import de.nova.security.NovaSecurity;
import org.bukkit.World;

import java.util.List;

public class WorldCheckUtil {

    /**
     * Returns true if a security check is enabled in this world
     */
    public static boolean isEnabled(NovaSecurity plugin, World world, String path) {
        if (world == null) return false;

        // Global toggle
        if (!plugin.getConfig().getBoolean(path + ".enabled", true)) {
            return false;
        }

        String worldName = world.getName();

        List<String> enabled = plugin.getConfig().getStringList(path + ".enabled-worlds");
        List<String> disabled = plugin.getConfig().getStringList(path + ".disabled-worlds");

        // Explicit whitelist
        if (!enabled.isEmpty()) {
            return enabled.contains(worldName);
        }

        // Explicit blacklist
        if (!disabled.isEmpty()) {
            return !disabled.contains(worldName);
        }

        // Default: enabled everywhere
        return true;
    }
}
