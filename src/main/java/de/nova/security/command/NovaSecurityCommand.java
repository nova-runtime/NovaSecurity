package de.nova.security.command;

import de.nova.security.NovaSecurity;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class NovaSecurityCommand implements CommandExecutor {

    private final NovaSecurity plugin;

    public NovaSecurityCommand(NovaSecurity plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!sender.hasPermission("novasecurity.admin")) {
            sender.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
            return true;
        }

        // /novasecurity
        if (args.length == 0) {
            sendInfo(sender);
            return true;
        }

        // /novasecurity reload
        if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
            plugin.reloadConfig();
            sender.sendMessage(ChatColor.GREEN + "NovaSecurity configuration reloaded.");
            return true;
        }

        // /novasecurity debug on|off
        if (args.length == 2 && args[0].equalsIgnoreCase("debug")) {
            boolean enable;

            if (args[1].equalsIgnoreCase("on")) {
                enable = true;
            } else if (args[1].equalsIgnoreCase("off")) {
                enable = false;
            } else {
                sender.sendMessage(ChatColor.RED + "Usage: /novasecurity debug <on|off>");
                return true;
            }

            plugin.getConfig().set("logging.debug", enable);
            plugin.saveConfig();

            sender.sendMessage(ChatColor.GREEN + "Debug mode "
                    + (enable ? "enabled" : "disabled") + ".");
            return true;
        }

        sender.sendMessage(ChatColor.RED + "Usage:");
        sender.sendMessage(ChatColor.GRAY + "/novasecurity");
        sender.sendMessage(ChatColor.GRAY + "/novasecurity reload");
        sender.sendMessage(ChatColor.GRAY + "/novasecurity debug <on|off>");
        return true;
    }


    private void sendInfo(CommandSender sender) {
        sender.sendMessage("");
        sender.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "NovaSecurity "
                + ChatColor.WHITE + "v" + plugin.getDescription().getVersion());
        sender.sendMessage(ChatColor.GRAY + "by Nova Runtime");
        sender.sendMessage("");

        sender.sendMessage(ChatColor.GREEN + "✔ Active protections:");
        sender.sendMessage(ChatColor.GRAY + "• Book exploit protection");
        sender.sendMessage(ChatColor.GRAY + "• NBT / ItemMeta size protection");
        sender.sendMessage("");

        sender.sendMessage(ChatColor.GRAY + "Running on "
                + ChatColor.WHITE + Bukkit.getName()
                + ChatColor.GRAY + " (" + Bukkit.getVersion() + ")");
        sender.sendMessage("");
    }
}
