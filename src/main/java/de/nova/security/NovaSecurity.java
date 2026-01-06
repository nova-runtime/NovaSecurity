package de.nova.security;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import de.nova.security.command.NovaSecurityCommand;
import de.nova.security.listener.BookExploitListener;
import de.nova.security.listener.NBTExploitListener;

public final class NovaSecurity extends JavaPlugin {

    private static NovaSecurity instance;

    @Override
    public void onEnable() {
        instance = this;

        long startTime = System.currentTimeMillis();

        // Config
        saveDefaultConfig();

        // Listener
        Bukkit.getPluginManager().registerEvents(new BookExploitListener(this), this);
        Bukkit.getPluginManager().registerEvents(new NBTExploitListener(this), this);
        
        getCommand("novasecurity").setExecutor(
                new NovaSecurityCommand(this)
        );


        long took = System.currentTimeMillis() - startTime;

        // Startup banner
        log("");
        log("§b§lNovaSecurity §7v" + getDescription().getVersion());
        log("§7by §fNova Runtime");
        log("");
        log("§a✔ Security systems loaded:");
        log("§7• Book exploit protection");
        log("§7• NBT / ItemMeta size protection");
        log("");
        log("§7Running on §f" + Bukkit.getName() + " §7(" + Bukkit.getVersion() + ")");
        log("§7Startup completed in §f" + took + "ms");
        log("");
    }

    @Override
    public void onDisable() {
        log("");
        log("§cNovaSecurity disabled");
        log("");
    }

    private void log(String message) {
        Bukkit.getConsoleSender().sendMessage("§8[§bNovaSecurity§8] §r" + message);
    }

    public static NovaSecurity getInstance() {
        return instance;
    }
}
