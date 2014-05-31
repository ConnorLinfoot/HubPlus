package com.connorlinfoot.hubplus.Player;

import com.connorlinfoot.hubplus.HubPlus;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class PlayerListener implements Listener {

    private Plugin instance = HubPlus.getInstance();

    private List<Player> cantUseClock = new ArrayList<Player>();

    @EventHandler
    public void onItemDrop( PlayerDropItemEvent event ){
        Player player = event.getPlayer();
        String world =  player.getWorld().getName();
        String currentworld = instance.getConfig().getString("Hub World");
        if(world.equals(currentworld)) {
            if (!player.hasPermission("hubplus.inv.bypass")) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onLoginEvent( PlayerLoginEvent event ){
        // Currently does fuck all :-)
    }

    /*@EventHandler
    public void onServerPing( ServerListPingEvent event ){
        String IP = String.valueOf( event.getAddress() );
        if(!IP.equals("/127.0.0.1")){
            event.setMotd("Your IP is: " + IP);
            CachedServerIcon serverIcon = instance.getServer().getServerIcon();
        } else {
            event.setMotd( "Your User is: " + ChatColor.GREEN + "MoogleMan!" );
        }

        event.setMaxPlayers(1);
    }*/

    @EventHandler
    public void onJoinBroadcast( PlayerJoinEvent event ){
        Player player = event.getPlayer();
        String msg = instance.getConfig().getString("On Join Broadcast");
        String msgnew = msg.replace( "<playername>", player.getDisplayName() );
        msgnew = textColors(msgnew);
        event.setJoinMessage(msgnew);
    }

    @EventHandler
    public void onQuitBroadcast( PlayerQuitEvent event ){
        Player player = event.getPlayer();
        String msg = instance.getConfig().getString("On Quit Broadcast");
        String msgnew = msg.replace( "<playername>", player.getDisplayName() );
        event.setQuitMessage(msgnew);
    }

    @EventHandler
    public void onItemPickup( PlayerPickupItemEvent event ){
        Player player = event.getPlayer();
        String world =  player.getWorld().getName();
        String currentworld = instance.getConfig().getString("Hub World");
        if(world.equals(currentworld)) {
            if (!player.hasPermission("hubplus.inv.bypass")) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onPlayerSprint( PlayerToggleSprintEvent event ){
        Player player = event.getPlayer();
        String world =  player.getWorld().getName();
        String currentworld = instance.getConfig().getString("Hub World");
        if(world.equals(currentworld)) {
            //player.sendMessage("I see you sprinting! Have a boost!");
            Integer time = 1;
            Integer time2 = time * 60;
            Integer time3 = time2 * 20;
            player.addPotionEffect((new PotionEffect(PotionEffectType.SPEED, time3, 1)));
        }
    }

    @EventHandler
    public void onInvDrag( InventoryClickEvent event ){
        Player player = (Player) event.getWhoClicked();
        String world =  player.getWorld().getName();
        String currentworld = instance.getConfig().getString("Hub World");
        if(world.equals(currentworld)) {
            if (!player.hasPermission("hubplus.inv.bypass")) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onJoinTP( PlayerJoinEvent event ){
        Player player = event.getPlayer();
        if( !player.hasPermission( "hubplus.jointp.bypass" ) && instance.getConfig().getString( "Hub On Join" ).equals( "true" ) ) {
            Location newLocation = player.getLocation();
            newLocation.setX((Double) instance.getConfig().get("Hub TP X"));
            newLocation.setY((Double) instance.getConfig().get("Hub TP Y"));
            newLocation.setZ((Double) instance.getConfig().get("Hub TP Z"));
            newLocation.setPitch(0);
            newLocation.setYaw(180);
            String world = instance.getConfig().getString("Hub World");
            newLocation.setWorld(Bukkit.getServer().getWorld(world));
            player.teleport(newLocation);
        }
    }

    @EventHandler
    public void onQuitTP( PlayerQuitEvent event ){
        Player player = event.getPlayer();
        if( !player.hasPermission( "hubplus.jointp.bypass" ) && instance.getConfig().getString( "Hub On Join" ).equals( "true" ) ) {
            Location newLocation = player.getLocation();
            newLocation.setX((Double) instance.getConfig().get("Hub TP X"));
            newLocation.setY((Double) instance.getConfig().get("Hub TP Y"));
            newLocation.setZ((Double) instance.getConfig().get("Hub TP Z"));
            newLocation.setPitch(0);
            newLocation.setYaw(180);
            String world = instance.getConfig().getString("Hub World");
            newLocation.setWorld(Bukkit.getServer().getWorld(world));
            player.teleport(newLocation);
        }
    }

    @EventHandler
    public void onPlayerJoin( PlayerJoinEvent event){
        Player player = event.getPlayer();
        String world =  player.getWorld().getName();
        String currentworld = instance.getConfig().getString("Hub World");
        if(world.equals(currentworld)) {
            PlayerInventory inventory = player.getInventory();
            Plugin instance = HubPlus.getInstance();
            if (instance.getConfig().getString("Clear Inventory On Join").equals("true")) {
                player.getInventory().clear();
                instance.getLogger().info("Works!!");
            }

            Inventory inv = player.getInventory();
            if (inv.contains(Material.WATCH)) {

            } else {
                inventory.addItem(new ItemStack(Material.WATCH));
            }


            for (int n = 0; n < inv.getSize(); n++) { //loop threw all items in the inventory
                ItemStack itm = inv.getItem(n); //get the items
                if (itm != null) { //make sure the item is not null, or you'll get a NullPointerException
                    if (itm.getType().equals(Material.WATCH)) { //if the item equals a contraband item
                        ItemMeta imeta = itm.getItemMeta();
                        imeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Hide Players!");
                        List<String> lore = new ArrayList<String>();
                        lore.add("Hide and Show players with this magic tool!");
                        imeta.setLore(lore);
                        itm.setItemMeta(imeta);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onClockClick( PlayerInteractEvent event ){
        Plugin instance = HubPlus.getInstance();
        Player player = event.getPlayer();
        String world =  player.getWorld().getName();
        String currentworld = instance.getConfig().getString("Hub World");
        if(world.equals(currentworld)) {
            String item = "NULL";
            if (event.hasItem()) {
                item = String.valueOf(event.getItem().getData());
            }
            item = String.valueOf(item);
            if (!item.equals("NULL") || !item.equals("")) {
                if (item.equals("WATCH(0)")) {
                    ArrayList hidden = (ArrayList) instance.getConfig().getList("PlayersHiding");
                    ClockCountdown d = new ClockCountdown();

                    if (!cantUseClock.contains(player)) {
                        if (hidden.contains(player.getName())) {
                            hidden.remove(player.getName());
                            instance.getConfig().set("PlayersHiding", hidden);
                            instance.saveConfig();
                            for (Player online : Bukkit.getServer().getOnlinePlayers()) {
                                player.showPlayer(online);
                            }
                            player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "Players no longer hidden!");
                            ItemStack inHand = player.getItemInHand();
                            ItemMeta imeta = inHand.getItemMeta();
                            imeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Hide Players!");
                            inHand.setItemMeta(imeta);
                        } else {
                            hidden.add(player.getName());
                            instance.getConfig().set("PlayersHiding", hidden);
                            instance.saveConfig();
                            for (Player online : Bukkit.getServer().getOnlinePlayers()) {
                                player.hidePlayer(online);
                            }
                            player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "Players hidden!");
                            ItemStack inHand = player.getItemInHand();
                            ItemMeta imeta = inHand.getItemMeta();
                            imeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Show Players!");
                            inHand.setItemMeta(imeta);
                        }

                        cantUseClock.add(player);
                        d.setList(cantUseClock);
                        d.setPlayer(player);
                        new Thread(d).start();
                    } else {
                        Integer time = instance.getConfig().getInt("Clock Cooldown");
                        String seconds;
                        if (time == 1) {
                            seconds = "second";
                        } else {
                            seconds = "seconds";
                        }
                        player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "Please wait " + time + " " + seconds + "!");
                    }

                }
            }
        }
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

    public class ClockCountdown implements Runnable {
        public Player player1 = null;
        public List<Player> cantUseClock1 = new ArrayList<Player>();
        Plugin instance = HubPlus.getInstance();

        public void setPlayer(Player player){
            player1 = player;
        }

        public void setList(List<Player> list){
            cantUseClock1 = list;
        }

        public List<Player> getList() {
            return cantUseClock1;
        }

        public void run(){
            try{
                Integer time = instance.getConfig().getInt("Clock Cooldown");
                Integer fulltime = time * 1000;
                Thread.sleep(fulltime);
                cantUseClock1.remove(player1);
            } catch (Exception ignored){
            }
        }
    }
}
