package com.github.gorkiiuss.uhcpantuflas.world;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldBorder;

import java.util.List;

public class WorldManager {
    private static WorldManager instance;

    private WorldManager() {

    }

    public void init() {
        List<World> worlds = Bukkit.getWorlds();

        WorldBorder overworldWorldBorder = worlds.get(0).getWorldBorder();
        WorldBorder netherWorldBorder = worlds.get(1).getWorldBorder();

        int initialWorldSize = 100; // TODO: 15/10/2023 make configurable
        int initialNetherSize = initialWorldSize / 8;

        overworldWorldBorder.setCenter(0, 0);
        overworldWorldBorder.setSize(initialWorldSize);

        netherWorldBorder.setCenter(0, 0);
        netherWorldBorder.setSize(initialNetherSize);
    }

    public Location getOverworld00() {
        World overworld = Bukkit.getWorlds().get(0);
        return new Location(overworld, 0, overworld.getHighestBlockYAt(0, 0) + 1, 0);
    }

    public static synchronized WorldManager get() {
        if (instance == null) instance = new WorldManager();
        return instance;
    }
}
