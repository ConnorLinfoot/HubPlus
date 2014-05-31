package com.connorlinfoot.hubplus.Commands;

import com.connorlinfoot.hubplus.HubPlus;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

/**
 * Created by Connor.Linfoot on 21/05/14.
 */
public class HubPlusCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Plugin instance = HubPlus.getInstance();
        if( label.equalsIgnoreCase( "hubplus" ) || label.equalsIgnoreCase( "hp" ) ){
            if( !(sender instanceof Player) ) {
                sender.sendMessage(ChatColor.RED + "Please use this command as a player!");
            } else {
                Player player = (Player) sender;
                if( player.hasPermission( "hubplus.admin" ) ) {
                    if (args.length == 0) {
                        player.sendMessage(ChatColor.GREEN + "====== HUB PLUS ======");
                        player.sendMessage(ChatColor.GREEN + "Version: " + instance.getDescription().getVersion() );
                        player.sendMessage(ChatColor.GREEN + "Created By: " + ChatColor.GOLD + "" + ChatColor.MAGIC + "CL" + ChatColor.RESET + "" + ChatColor.GREEN + "Connor Linfoot" + ChatColor.GOLD + "" + ChatColor.MAGIC + "CL");
                    } else if( args[0].equalsIgnoreCase("help") || args[0].equalsIgnoreCase("h")) {
                        player.sendMessage(ChatColor.GREEN + "====== HUB PLUS ======");
                        player.sendMessage(ChatColor.GREEN + "/hubplus sethub - Used to set the 'hub' point.");
                        player.sendMessage(ChatColor.GREEN + "/hubplus reload - Reload Hub Plus Config.");
                        player.sendMessage(ChatColor.GREEN + "/hubplus stats <enable/disable> - Enable/Disable MC Stats.");
                        player.sendMessage(ChatColor.GREEN + "/hubplus clockcooldown <seconds> - Set Clock Cooldown.");
                        player.sendMessage(ChatColor.GREEN + "/hub - Go to Hub.");
                        player.sendMessage(ChatColor.GREEN + "/broadcast <message> - Send a broadcast to the server.");
                        player.sendMessage(ChatColor.GREEN + "/hubplus help - Shows this screen.");
                        player.sendMessage(ChatColor.GREEN + "/fw - Launches a firework.");
                    } else if( args[0].equalsIgnoreCase( "sethub" ) ) {
                        if( player.hasPermission( "hubplus.admin" ) ) {
                            Location location = player.getLocation();
                            double xlocation = location.getX();
                            double ylocation = location.getY();
                            double zlocation = location.getZ();
                            double locationYaw = location.getYaw();
                            double locationPitch = location.getPitch();
                            instance.getConfig().set("Hub TP X", xlocation);
                            instance.getConfig().set("Hub TP Y", ylocation);
                            instance.getConfig().set("Hub TP Z", zlocation);
                            instance.getConfig().set("Hub TP Yaw", locationYaw);
                            instance.getConfig().set("Hub TP Pitch", locationPitch);
                            instance.saveConfig();
                            player.sendMessage(ChatColor.GREEN + "Hub Point Set!");
                        } else {
                            player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "No permission!");
                        }
                    } else if( args[0].equalsIgnoreCase( "reload" ) ){
                        if( player.hasPermission( "hubplus.admin" ) ) {
                            instance.reloadConfig();
                            player.sendMessage( "Hub Plus Config Reloaded!" );
                        }
                    } else if( args[0].equalsIgnoreCase( "stats" ) ){
                        if( player.hasPermission( "hubplus.admin" ) ) {
                            if( args.length == 2 && args[1].equals( "enable" ) ) {
                                // Enable stats
                                instance.getConfig().set( "Send Stats", "true" );
                                player.sendMessage( ChatColor.GREEN + "You have enabled MC Stats!" );
                            } else if( args.length == 2 && args[1].equals( "disable" ) ){
                                // Disable Stats
                                instance.getConfig().set( "Send Stats", "false" );
                                player.sendMessage( ChatColor.GREEN + "You have disabled MC Stats!" );
                            } else {
                                // Show Help Message
                                player.sendMessage( ChatColor.GREEN + "Please use the command /hubplus stats <enable/disable>" );
                            }
                        }
                    } else if( args[0].equalsIgnoreCase( "clockcooldown" ) ){
                        if( player.hasPermission( "hubplus.admin" ) ) {
                            if( args.length == 2 ) {
                                // Enable stats
                                Integer count = Integer.valueOf(args[1]);
                                instance.getConfig().set( "Clock Cooldown", count );
                                player.sendMessage( ChatColor.GREEN + "Clock Cooldown is now set to " + count );
                            } else {
                                // Show Help Message
                                player.sendMessage( ChatColor.GREEN + "Please use the command /hubplus clockcooldown <seconds>" );
                            }
                        }
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "No permission!");
                }
            }
        }

        return false;
    }
}
