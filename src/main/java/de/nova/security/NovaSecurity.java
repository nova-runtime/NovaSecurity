package de.nova.security;

import org.bukkit.plugin.java.JavaPlugin;

public final class NovaSecurity extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        getLogger().info("NovaSecurity enabled");
    }

    @Override
    public void onDisable() {
        getLogger().info("NovaSecurity disabled");
    }
}
