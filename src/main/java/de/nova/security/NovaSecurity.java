package de.nova.security;

import de.nova.security.command.NovaSecurityCommand;
import de.nova.security.listener.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class NovaSecurity extends JavaPlugin {

    private static NovaSecurity instance;

    @Override
    public void onEnable() {
        instance = this;
        long start = System.currentTimeMillis();

        saveDefaultConfig();

        Bukkit.getPluginManager().registerEvents(new BookExploitListener(this), this);
        Bukkit.getPluginManager().registerEvents(new NBTExploitListener(this), this);
        Bukkit.getPluginManager().registerEvents(new ChatExploitListener(this), this);
        Bukkit.getPluginManager().registerEvents(new CommandSpamListener(this), this);
        Bukkit.getPluginManager().registerEvents(new ChunkExploitListener(this), this);
        Bukkit.getPluginManager().registerEvents(new EntityChunkExploitListener(this), this);
        Bukkit.getPluginManager().registerEvents(new TileEntityChunkExploitListener(this), this);
        Bukkit.getPluginManager().registerEvents(new InventorySpamListener(this), this);

        getCommand("novasecurity").setExecutor(new NovaSecurityCommand(this));

        long took = System.currentTimeMillis() - start;

        log("");
        log("§b§lNovaSecurity §7v" + getDescription().getVersion());
        log("§7by §fNova Runtime");
        log("");
        log("§a✔ Security systems loaded");
        log("§7• Book exploit protection");
        log("§7• NBT / ItemMeta protection");
        log("§7• Chat flood protection");
        log("§7• Command spam protection");
        log("§7• Chunk load spam protection");
        log("§7• Chunk entity flood protection");
        log("§7• TileEntity flood protection");
        log("§7• Inventory click spam protection");
        log("");
        log("§7Startup completed in §f" + took + "ms");
        log("");
    }

    @Override
    public void onDisable() {
        log("§cNovaSecurity disabled");
    }

    public static NovaSecurity getInstance() {
        return instance;
    }

    private void log(String msg) {
        Bukkit.getConsoleSender().sendMessage("§8[§bNovaSecurity§8] §r" + msg);
    }
}
