package de.nova.security.util;

import de.nova.security.NovaSecurity;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class ViolationStorage {

    private static File file;
    private static FileConfiguration config;

    public static void init(NovaSecurity plugin) {
        file = new File(plugin.getDataFolder(), "violations.yml");

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                plugin.getLogger().severe("Could not create violations.yml");
            }
        }

        config = YamlConfiguration.loadConfiguration(file);
    }

    public static void load(UUID uuid) {
        int violations = config.getInt(uuid.toString(), 0);
        ViolationCounter.set(uuid, violations);
    }

    public static void save(UUID uuid) {
        config.set(uuid.toString(), ViolationCounter.get(uuid));
        saveFile();
    }

    public static void saveAll() {
        for (UUID uuid : ViolationCounter.getAll().keySet()) {
            config.set(uuid.toString(), ViolationCounter.get(uuid));
        }
        saveFile();
    }

    private static void saveFile() {
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
