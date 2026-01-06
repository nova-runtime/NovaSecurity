package de.nova.security;

import org.bukkit.plugin.java.JavaPlugin;

import de.nova.security.listener.BookExploitListener;

public final class NovaSecurity extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        getLogger().info("NovaSecurity enabled");
        
        getServer().getPluginManager().registerEvents(
                new BookExploitListener(this), this
            );
    }

    @Override
    public void onDisable() {
        getLogger().info("NovaSecurity disabled");
    }
}
