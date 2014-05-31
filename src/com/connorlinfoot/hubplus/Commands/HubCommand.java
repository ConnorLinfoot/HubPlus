package com.connorlinfoot.hubplus.Commands;

import com.connorlinfoot.hubplus.HubPlus;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class HubCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Plugin instance = HubPlus.getInstance();
        if( label.equalsIgnoreCase( "hub" ) ){
            if( !(sender instanceof Player) ) {
                sender.sendMessage(ChatColor.RED + "Please use this command as a player!");
            } else {
                Player player = (Player) sender;
                if( player.hasPermission( "hubplus.hub" ) ) {
                    if (args.length == 0) {
                        if (instance.getConfig().isSet("Hub TP X") && instance.getConfig().isSet("Hub TP Y") && instance.getConfig().isSet("Hub TP Z")) {
                            Location newLocation = player.getLocation();
                            newLocation.setX((Double) instance.getConfig().get("Hub TP X"));
                            newLocation.setY((Double) instance.getConfig().get("Hub TP Y"));
                            newLocation.setZ((Double) instance.getConfig().get("Hub TP Z"));
                            Float newpitch = Float.valueOf((float) instance.getConfig().getDouble("Hub TP Pitch"));
                            Float newyaw = Float.valueOf((float) instance.getConfig().getDouble("Hub TP Yaw"));
                            newLocation.setPitch(newpitch);
                            newLocation.setYaw(newyaw);
                            String world = instance.getConfig().getString("Hub World");
                            newLocation.setWorld(Bukkit.getServer().getWorld(world));
                            player.teleport(newLocation);
                            player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "Welcome to the hub!");
                        } else {
                            player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "No hub is set!");
                        }
                    } else {
                        player.sendMessage("Please use /hubplus help");
                    }
                }
            }
        }

        return false;
    }
}
