package com.connorlinfoot.hubplus;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChatSensor implements Listener {

    private Plugin instance = HubPlus.getInstance();

    @EventHandler
    public void onChat( AsyncPlayerChatEvent event ){
        Player player = event.getPlayer();
        String chat = event.getMessage();
        ArrayList bannedWords = new ArrayList<List>();
        String[] wordsList = new String[] {"fucking","fucks","fuck","bitch","skets","sket","sluts","slut","bastards","bastard","twat","faggots","faggot","whore","shit","pussy","dicks","dick","cocks","cock","vagina","tits","tit","boobs","boob","penis"};
        bannedWords.addAll(Arrays.asList(wordsList));

        // Future feature, disable for ops or users with hubplus.chat.sensor.bypass permission!

        if(instance.getConfig().getString("Chat Sensor").equalsIgnoreCase("replace")){ // Checks if config is set to replace
            for( Object sensor1 : bannedWords ) { // Changes the word into stars
                String sensor = String.valueOf(sensor1);
                chat = chat.replace(sensor, "****" ); // Replaces string with stars
                //chat = chat.replace(sensor, ChatColor.MAGIC + "****" + ChatColor.RESET ); // Replaces string with magic
                event.setMessage(chat); // Return the new sensor message
            }
        } else if(instance.getConfig().getString("Chat Sensor").equalsIgnoreCase("block")){ // Checks if config is set to block
            for( Object sensor1 : bannedWords ) { // Blocks the chat message all together!
                String sensor = String.valueOf(sensor1);
                if (chat.toLowerCase().contains(sensor.toLowerCase())) { // Checks if any words from bannedWords is in the message
                    player.sendMessage(ChatColor.BOLD + "" + ChatColor.RED + "--------------- Hub Plus! ---------------"); // Custom server name here?
                    player.sendMessage(ChatColor.BOLD + "" + ChatColor.RED + "Swearing is not allowed on this server!"); // Custom message here?
                    player.sendMessage(ChatColor.BOLD + "" + ChatColor.RED + "---------------------------------------");
                    event.setCancelled(true); // Stops chat message showing
                }
            }
        }
    }
}
