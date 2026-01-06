package de.nova.security.listener;

import de.nova.security.NovaSecurity;
import de.nova.security.util.ViolationStorage;

import java.util.UUID;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerDataListener implements Listener {

    private final NovaSecurity plugin;

    public PlayerDataListener(NovaSecurity plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        ViolationStorage.load(event.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        UUID uuid = event.getPlayer().getUniqueId();

        CommandSpamListener.cleanup(uuid);
        InventorySpamListener.cleanup(uuid);
        InteractSpamListener.cleanup(uuid);
        TabCompleteSpamListener.cleanup(uuid);

        ViolationStorage.save(uuid);
    }

}
