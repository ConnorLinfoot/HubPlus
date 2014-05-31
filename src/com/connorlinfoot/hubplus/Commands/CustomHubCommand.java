package com.connorlinfoot.hubplus.Commands;

import com.connorlinfoot.hubplus.HubPlus;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.Plugin;

/**
 * Created by Connor.Linfoot on 30/05/14.
 */
public class CustomHubCommand implements Listener {

    private Plugin instance = HubPlus.getInstance();

    @EventHandler
    public void command( PlayerCommandPreprocessEvent event ){
        String test = event.getMessage();
        Player player = event.getPlayer();
        if( test.equals( "/" + instance.getConfig().getString( "Custom Hub Command" ) ) ) {
            if( player.hasPermission( "hubplus.hub" ) ) {
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
            //player.sendMessage("You did, " + test);
            event.setCancelled(true);
        }
    }
}
