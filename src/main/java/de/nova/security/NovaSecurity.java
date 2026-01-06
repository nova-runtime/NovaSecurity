package de.nova.security;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import de.nova.security.command.NovaSecurityCommand;
import de.nova.security.listener.BookExploitListener;
import de.nova.security.listener.ChatExploitListener;
import de.nova.security.listener.ChunkExploitListener;
import de.nova.security.listener.CommandSpamListener;
import de.nova.security.listener.EntityChunkExploitListener;
import de.nova.security.listener.InteractSpamListener;
import de.nova.security.listener.InventorySpamListener;
import de.nova.security.listener.NBTExploitListener;
import de.nova.security.listener.PlayerDataListener;
import de.nova.security.listener.TabCompleteSpamListener;
import de.nova.security.listener.TileEntityChunkExploitListener;
import de.nova.security.util.ViolationStorage;

public final class NovaSecurity extends JavaPlugin {

    private static NovaSecurity instance;

    @Override
    public void onEnable() {
        instance = this;
        long start = System.currentTimeMillis();
        ViolationStorage.init(this);
        saveDefaultConfig();

        Bukkit.getPluginManager().registerEvents(new BookExploitListener(this), this);
        Bukkit.getPluginManager().registerEvents(new NBTExploitListener(this), this);
        Bukkit.getPluginManager().registerEvents(new ChatExploitListener(this), this);
        Bukkit.getPluginManager().registerEvents(new CommandSpamListener(this), this);
        Bukkit.getPluginManager().registerEvents(new ChunkExploitListener(this), this);
        Bukkit.getPluginManager().registerEvents(new EntityChunkExploitListener(this), this);
        Bukkit.getPluginManager().registerEvents(new TileEntityChunkExploitListener(this), this);
        Bukkit.getPluginManager().registerEvents(new InventorySpamListener(this), this);
        Bukkit.getPluginManager().registerEvents(
                new TabCompleteSpamListener(this), this
        );
        
        Bukkit.getPluginManager().registerEvents(
                new InteractSpamListener(this), this
        );
        
        Bukkit.getPluginManager().registerEvents(
                new PlayerDataListener(this), this
        );



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
        
        ViolationStorage.saveAll();
    }

    public static NovaSecurity getInstance() {
        return instance;
    }

    private void log(String msg) {
        Bukkit.getConsoleSender().sendMessage("§8[§bNovaSecurity§8] §r" + msg);
    }
}
