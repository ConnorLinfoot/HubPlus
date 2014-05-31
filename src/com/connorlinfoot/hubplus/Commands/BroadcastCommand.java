package com.connorlinfoot.hubplus.Commands;

import com.connorlinfoot.hubplus.HubPlus;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

/**
 * Created by Connor.Linfoot on 20/05/14.
 */
public class BroadcastCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Plugin instance = HubPlus.getInstance();
        if (label.equalsIgnoreCase("broadcast") || label.equalsIgnoreCase("bc")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.RED + "Please use this command as a player!");
            } else {
                Player player = (Player) sender;
                if (player.hasPermission("hubplus.broadcast")) {
                    if (args.length == 0) {
                        player.sendMessage(ChatColor.RED + "Please use the command by doing /broadcast <message>");
                    } else {
                        //String msg = Arrays.toString(args);
                        StringBuilder builder = new StringBuilder();
                        for (String value : args) {
                            builder.append(value).append(" ");
                        }
                        String msg = builder.toString();
                        msg = textColors(msg);
                        instance.getServer().broadcastMessage(ChatColor.GOLD + instance.getConfig().getString("Broadcast Prefix") + " " + ChatColor.RESET + msg);
                        //player.sendMessage("");
                    }
                }
            }


        }
        return false;
    }

    public String textColors( String msg ){
        msg = msg.replace("&0", ChatColor.BLACK + "" );
        msg = msg.replace("&1", ChatColor.DARK_BLUE + "" );
        msg = msg.replace("&2", ChatColor.DARK_GREEN + "" );
        msg = msg.replace("&3", ChatColor.DARK_AQUA + "" );
        msg = msg.replace("&4", ChatColor.DARK_RED + "" );
        msg = msg.replace("&5", ChatColor.DARK_PURPLE + "" );
        msg = msg.replace("&6", ChatColor.GOLD + "" );
        msg = msg.replace("&7", ChatColor.GRAY + "" );
        msg = msg.replace("&8", ChatColor.DARK_GRAY + "" );
        msg = msg.replace("&9", ChatColor.BLUE + "" );
        msg = msg.replace("&a", ChatColor.GREEN + "" );
        msg = msg.replace("&b", ChatColor.AQUA + "" );
        msg = msg.replace("&c", ChatColor.RED + "" );
        msg = msg.replace("&e", ChatColor.YELLOW + "" );
        msg = msg.replace("&f", ChatColor.WHITE + "" );
        msg = msg.replace("&k", ChatColor.MAGIC + "" );
        msg = msg.replace("&l", ChatColor.BOLD + "" );
        msg = msg.replace("&n", ChatColor.UNDERLINE + "" );
        msg = msg.replace("&o", ChatColor.ITALIC + "" );
        msg = msg.replace("&m", ChatColor.STRIKETHROUGH + "" );
        msg = msg.replace("&r", ChatColor.RESET + "" );
        return msg;
    }
}
