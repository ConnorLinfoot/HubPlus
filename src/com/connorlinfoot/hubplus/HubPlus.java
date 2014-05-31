package com.connorlinfoot.hubplus;

import com.connorlinfoot.hubplus.Commands.BroadcastCommand;
import com.connorlinfoot.hubplus.Commands.CustomHubCommand;
import com.connorlinfoot.hubplus.Commands.HubCommand;
import com.connorlinfoot.hubplus.Commands.HubPlusCommand;
import com.connorlinfoot.hubplus.Player.PlayerListener;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class HubPlus extends JavaPlugin implements Listener {
    private static Plugin instance;

    public void onEnable() {
        instance = this;
        Server server = getServer();
        ArrayList hidden = new ArrayList();
        getConfig().set("PlayersHiding", hidden);
        saveConfig();
        getConfig().options().copyDefaults(true);
        saveConfig();

        if(getConfig().getString( "Send Stats" ).equals(" true" ) ) {
            try {
                Metrics metrics = new Metrics(this);
                metrics.start();
            } catch (IOException e) {
                // Failed to submit the stats :-( <-- Dat face doe
            }
        }

        Bukkit.getPluginManager().registerEvents(new ChatSensor(), this);
        Bukkit.getPluginManager().registerEvents(new BlockListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
        Bukkit.getPluginManager().registerEvents(new BloodEffect(), this);
        Bukkit.getPluginManager().registerEvents(new SignListener(), this);
        Bukkit.getPluginManager().registerEvents(new CustomHubCommand(), this);

        getCommand("hub").setExecutor(new HubCommand());
        getCommand("hubplus").setExecutor(new HubPlusCommand());
        getCommand("hp").setExecutor(new HubPlusCommand());
        getCommand("broadcast").setExecutor(new BroadcastCommand());
        getCommand("bc").setExecutor(new BroadcastCommand());

        ConsoleCommandSender console = server.getConsoleSender();
        console.sendMessage(ChatColor.GREEN + "============= HUB PLUS =============");
        console.sendMessage(ChatColor.GREEN + "=========== VERSION: 0.3 ===========");
        console.sendMessage(ChatColor.GREEN + "======== BY CONNOR LINFOOT! ========");
        /*Updater updater = new Updater(this, 79988, this.getFile(), UpdateType.NO_DOWNLOAD, true);
        if (updater.getResult() == UpdateResult.UPDATE_AVAILABLE) {
            this.getLogger().info("New version available! " + updater.getLatestName());
        }*/
    }

    public void onDisable() {
        getLogger().info(getDescription().getName() + " has been disabled!");
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if( label.equalsIgnoreCase( "world" ) ) {
            Player player = (Player) sender;
            if( player.hasPermission( "hubplus.world" ) ) {
                String world = player.getWorld().getName().toLowerCase();
                player.sendMessage("Current World: " + ChatColor.RED + ChatColor.BOLD + world);
            }
        } else if( label.equalsIgnoreCase( "fw" ) ){
            Player player = (Player) sender;
            shootFirework(player);
            String launch = ChatColor.GREEN + "You launched a firework!";
            player.sendMessage(formatVariables(launch, player));
        }

        return false;
    }

    public String formatVariables( String string, Player player ) {
        int cdt = 1;
        return ChatColor.translateAlternateColorCodes("&".charAt(0), string).replace("%time", String.valueOf(cdt));
    }

    private void shootFirework( Player player ){
        Firework firework = ( Firework )player.getWorld().spawnEntity( player.getLocation(), EntityType.FIREWORK );
        FireworkMeta fm = firework.getFireworkMeta();
        Random r = new Random();
        FireworkEffect.Type type = null;
        int fType = r.nextInt(5) + 1;
        switch ( fType ) {
            case 1:
            default:
                type = FireworkEffect.Type.BALL;
                break;
            case 2:
                type = FireworkEffect.Type.BALL_LARGE;
                break;
            case 3:
                type = FireworkEffect.Type.BURST;
                break;
            case 4:
                type = FireworkEffect.Type.CREEPER;
                break;
            case 5:
                type = FireworkEffect.Type.STAR;
        }
        int c1i = r.nextInt(16) + 1;
        int c2i = r.nextInt(16) + 1;
        Color c1 = getColor(c1i);
        Color c2 = getColor(c2i);
        FireworkEffect effect = FireworkEffect.builder().flicker(r.nextBoolean()).withColor(c1).withFade(c2).with(type).trail(r.nextBoolean()).build();
        fm.addEffect(effect);
        int power = r.nextInt(2) + 1;
        fm.setPower(power);
        firework.setFireworkMeta(fm);
    }

    public Color getColor( int c ) {
        switch ( c ) {
            case 1:
            default:
                return Color.AQUA;
            case 2:
                return Color.BLACK;
            case 3:
                return Color.BLUE;
            case 4:
                return Color.FUCHSIA;
            case 5:
                return Color.GRAY;
            case 6:
                return Color.GREEN;
            case 7:
                return Color.LIME;
            case 8:
                return Color.MAROON;
            case 9:
                return Color.NAVY;
            case 10:
                return Color.OLIVE;
            case 11:
                return Color.ORANGE;
            case 12:
                return Color.PURPLE;
            case 13:
                return Color.RED;
            case 14:
                return Color.SILVER;
            case 15:
                return Color.TEAL;
            case 16:
                return Color.WHITE;
        }
        //return Color.YELLOW;
    }



    public static Plugin getInstance() {
        return instance;
    }

}
