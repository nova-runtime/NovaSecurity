package de.nova.security.command;

import de.nova.security.NovaSecurity;
import de.nova.security.util.ViolationCounter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NovaSecurityCommand implements CommandExecutor {

    private final NovaSecurity plugin;

    public NovaSecurityCommand(NovaSecurity plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!sender.hasPermission("novasecurity.admin")) {
            sender.sendMessage(ChatColor.RED + "No permission.");
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage("§bNovaSecurity §7v" + plugin.getDescription().getVersion());
            sender.sendMessage("§7/novasecurity reload");
            sender.sendMessage("§7/novasecurity debug <on|off>");
            sender.sendMessage("§7/novasecurity status <player>");
            return true;
        }

        if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
            plugin.reloadConfig();
            sender.sendMessage("§aConfiguration reloaded.");
            return true;
        }

        if (args.length == 2 && args[0].equalsIgnoreCase("debug")) {
            boolean value = args[1].equalsIgnoreCase("on");
            plugin.getConfig().set("logging.debug", value);
            plugin.saveConfig();
            sender.sendMessage("§aDebug mode " + (value ? "enabled" : "disabled"));
            return true;
        }

        if (args.length == 2 && args[0].equalsIgnoreCase("status")) {
            Player target = Bukkit.getPlayer(args[1]);
            if (target == null) {
                sender.sendMessage("§cPlayer not found.");
                return true;
            }

            sender.sendMessage("§bNovaSecurity Status");
            sender.sendMessage("§7Player: §f" + target.getName());
            sender.sendMessage("§7Violations: §c" +
                    ViolationCounter.get(target.getUniqueId()));
            return true;
        }

        return true;
    }
}
