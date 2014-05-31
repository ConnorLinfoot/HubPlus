package com.connorlinfoot.hubplus;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.Plugin;

import java.util.Arrays;
import java.util.List;

public class BlockListener implements Listener {
    private Plugin instance = HubPlus.getInstance();

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Plugin instance = HubPlus.getInstance();
        Player player = event.getPlayer();
        String playername = player.getName();
        Material material = event.getBlock().getType();
        String world =  player.getWorld().getName();
        String worlds = null;
        worlds = instance.getConfig().getString("Protected Worlds");
        List<String> worldList = Arrays.asList(worlds.split(","));
        if(worldList.contains(world)) {
            if (!player.hasPermission("hubplus.protection.bypass")) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event){
        Plugin instance = HubPlus.getInstance();
        Player player = event.getPlayer();
        String playername = player.getName();
        Material material = event.getBlock().getType();
        String world =  player.getWorld().getName();
        String worlds = null;
        worlds = instance.getConfig().getString("Protected Worlds");
        List<String> worldList = Arrays.asList(worlds.split(","));
        if(worldList.contains(world)) {
            if (!player.hasPermission("hubplus.protection.bypass")) {
                event.setCancelled(true);
            }
        }
    }

}

