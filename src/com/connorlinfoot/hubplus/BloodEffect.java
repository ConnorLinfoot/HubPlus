package com.connorlinfoot.hubplus;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.Plugin;

import java.util.Arrays;
import java.util.List;

public class BloodEffect implements Listener {

    private Plugin instance = HubPlus.getInstance();

    @EventHandler
    public void onDamage(EntityDamageEvent e){
        if(instance.getConfig().getString("Blood Enabled").equals("true")) {
            Entity entity = e.getEntity();
            EntityType etype = entity.getType();
            String all = instance.getConfig().getString("Blood Entitys.All");
            String allow = "false";
            String worldallow = "false";
            if (instance.getConfig().getString("Blood Worlds").equalsIgnoreCase("all"))
                worldallow = "true";
            String world = entity.getWorld().getName();
            String currentworld = instance.getConfig().getString("Blood Worlds");
            List<String> worldList = Arrays.asList(currentworld.split(","));
            if (worldList.contains(world))
                worldallow = "true";

            if (worldallow.equals("true")) {
                if (all.equals("true")) allow = "true";

                if (etype == EntityType.BAT && instance.getConfig().getString("Blood Entitys.Bat").equalsIgnoreCase("true"))
                    allow = "true";
                if (etype == EntityType.BLAZE && instance.getConfig().getString("Blood Entitys.Blaze").equalsIgnoreCase("true"))
                    allow = "true";
                if (etype == EntityType.CAVE_SPIDER && instance.getConfig().getString("Blood Entitys.Cave Spider").equalsIgnoreCase("true"))
                    allow = "true";
                if (etype == EntityType.CHICKEN && instance.getConfig().getString("Blood Entitys.Chicken").equalsIgnoreCase("true"))
                    allow = "true";
                if (etype == EntityType.COW && instance.getConfig().getString("Blood Entitys.Cow").equalsIgnoreCase("true"))
                    allow = "true";
                if (etype == EntityType.CREEPER && instance.getConfig().getString("Blood Entitys.Creeper").equalsIgnoreCase("true"))
                    allow = "true";
                if (etype == EntityType.ENDERMAN && instance.getConfig().getString("Blood Entitys.Enderman").equalsIgnoreCase("true"))
                    allow = "true";
                if (etype == EntityType.GHAST && instance.getConfig().getString("Blood Entitys.Ghast").equalsIgnoreCase("true"))
                    allow = "true";
                if (etype == EntityType.GIANT && instance.getConfig().getString("Blood Entitys.Giant").equalsIgnoreCase("true"))
                    allow = "true";
                if (etype == EntityType.HORSE && instance.getConfig().getString("Blood Entitys.Horse").equalsIgnoreCase("true"))
                    allow = "true";
                if (etype == EntityType.IRON_GOLEM && instance.getConfig().getString("Blood Entitys.Iron Golem").equalsIgnoreCase("true"))
                    allow = "true";
                if (etype == EntityType.MAGMA_CUBE && instance.getConfig().getString("Blood Entitys.Magma Cube").equalsIgnoreCase("true"))
                    allow = "true";
                if (etype == EntityType.MUSHROOM_COW && instance.getConfig().getString("Blood Entitys.Mushroom Cow").equalsIgnoreCase("true"))
                    allow = "true";
                if (etype == EntityType.OCELOT && instance.getConfig().getString("Blood Entitys.Ocelot").equalsIgnoreCase("true"))
                    allow = "true";
                if (etype == EntityType.PIG && instance.getConfig().getString("Blood Entitys.Pig").equalsIgnoreCase("true"))
                    allow = "true";
                if (etype == EntityType.PLAYER && instance.getConfig().getString("Blood Entitys.Player").equalsIgnoreCase("true"))
                    allow = "true";
                if (etype == EntityType.SHEEP && instance.getConfig().getString("Blood Entitys.Sheep").equalsIgnoreCase("true"))
                    allow = "true";
                if (etype == EntityType.SILVERFISH && instance.getConfig().getString("Blood Entitys.Silverfish").equalsIgnoreCase("true"))
                    allow = "true";
                if (etype == EntityType.SKELETON && instance.getConfig().getString("Blood Entitys.Skeleton").equalsIgnoreCase("true"))
                    allow = "true";
                if (etype == EntityType.SLIME && instance.getConfig().getString("Blood Entitys.Slime").equalsIgnoreCase("true"))
                    allow = "true";
                if (etype == EntityType.SNOWMAN && instance.getConfig().getString("Blood Entitys.Snowman").equalsIgnoreCase("true"))
                    allow = "true";
                if (etype == EntityType.SPIDER && instance.getConfig().getString("Blood Entitys.Spider").equalsIgnoreCase("true"))
                    allow = "true";
                if (etype == EntityType.SQUID && instance.getConfig().getString("Blood Entitys.Squid").equalsIgnoreCase("true"))
                    allow = "true";
                if (etype == EntityType.VILLAGER && instance.getConfig().getString("Blood Entitys.Villager").equalsIgnoreCase("true"))
                    allow = "true";
                if (etype == EntityType.WITCH && instance.getConfig().getString("Blood Entitys.Witch").equalsIgnoreCase("true"))
                    allow = "true";
                if (etype == EntityType.WITHER && instance.getConfig().getString("Blood Entitys.Wither").equalsIgnoreCase("true"))
                    allow = "true";
                if (etype == EntityType.WOLF && instance.getConfig().getString("Blood Entitys.Wolf").equalsIgnoreCase("true"))
                    allow = "true";
                if (etype == EntityType.ZOMBIE && instance.getConfig().getString("Blood Entitys.Zombie").equalsIgnoreCase("true"))
                    allow = "true";
                if (etype == EntityType.PIG_ZOMBIE && instance.getConfig().getString("Blood Entitys.Zombie Pigman").equalsIgnoreCase("true"))
                    allow = "true";

                if (allow.equals("true")) {
                    Location loc = entity.getLocation();
                    Effect effect = Effect.STEP_SOUND;
                    e.getEntity().getLocation().getWorld().playEffect(loc.add(0, 1, 0), effect, 152);
                }
            }
        }
    }
}
