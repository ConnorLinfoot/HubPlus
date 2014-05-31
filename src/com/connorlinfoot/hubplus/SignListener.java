package com.connorlinfoot.hubplus;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;

/**
 * Created by Connor.Linfoot on 22/05/14.
 */
public class SignListener implements Listener {
    private Plugin instance = HubPlus.getInstance();

    @EventHandler
    public void onSignPlace( SignChangeEvent event ){
        if( event.getLine(0).equals( "[Hub]" ) ){
            Player player = event.getPlayer();
            if( player.hasPermission("hubplus.sign.create") ) {
                event.setLine( 0, ChatColor.GREEN + "[Hub]" );
                player.sendMessage( "You have just created a Hub TP Sign" );
            } else {
                event.setLine( 0, ChatColor.RED + "[HubPlus]"  );
                player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "No permission!");
            }
        }
    }

    @EventHandler
    public void onSignClick( PlayerInteractEvent event ){
        Block block = null;
        block = event.getClickedBlock();
        if ( block != null && ( block.getType() == Material.WALL_SIGN || block.getType() == Material.SIGN || block.getType() == Material.SIGN_POST ) ) {
            Player player = event.getPlayer();
            Sign sign = (Sign) block.getState();
            String signline1 = sign.getLine(0);
            if (signline1.equals(ChatColor.GREEN + "[Hub]")) {
                if (player.hasPermission("hubplus.sign.use")) {
                    String gamemode = String.valueOf(player.getGameMode());
                    if (!gamemode.equals("CREATIVE")) {
                        event.setCancelled(true);
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
                    }
                }
            }
        }
    }
}
