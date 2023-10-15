package com.github.gorkiiuss.uhcpantuflas.world;

import com.github.gorkiiuss.uhcpantuflas.gameplay.GameplayManager;
import com.github.gorkiiuss.uhcpantuflas.player.PlayerManager;
import com.github.gorkiiuss.uhcpantuflas.teams.TeamManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldBorder;

import java.util.List;

public class WorldManager {
    private static WorldManager instance;
    private int diameter;

    private WorldManager() {

    }

    public void init() {
        List<World> worlds = Bukkit.getWorlds();

        WorldBorder overworldWorldBorder = worlds.get(0).getWorldBorder();
//        WorldBorder netherWorldBorder = worlds.get(1).getWorldBorder(); todo 15/10/2023 fix nether tiny size

        final int initialWorldSize = 100; // TODO: 15/10/2023 make configurable
//        int initialNetherSize = initialWorldSize / 8;

        overworldWorldBorder.setCenter(0, 0);
        overworldWorldBorder.setSize(initialWorldSize);

//        netherWorldBorder.setCenter(0, 0);
//        netherWorldBorder.setSize(initialNetherSize);
    }

    public Location getOverworld00() {
        World overworld = Bukkit.getWorlds().get(0);
        return new Location(overworld, 0, overworld.getHighestBlockYAt(0, 0) + 1, 0);
    }

    public static synchronized WorldManager get() {
        if (instance == null) instance = new WorldManager();
        return instance;
    }

    public void updateDiameter() {
        int playerCount = PlayerManager.get().count();
        double density = GameplayManager.get().getDensity();

        double onlyPlayerDiameter = Math.sqrt((double) playerCount / density);
        int onlyTeamDiameter = (int) Math.sqrt((double) TeamManager.get().count() / density);
        double teamSizeFactor = ((double)(playerCount - TeamManager.get().getTeamsSize())) / (playerCount - 1);

        int factoredDiameter = (int) (teamSizeFactor * onlyPlayerDiameter);

        diameter = Math.max(factoredDiameter, onlyTeamDiameter);
    }

    public int getDiameter() {
        return diameter;
    }

    public void setWorldBorder(int size, long seconds) {
        Bukkit.getWorlds().get(0).getWorldBorder().setSize(size, seconds);
    }

    public void shrink() {
        final double battleLandDensityRatio = 0.01; // TODO: 15/10/2023 make configurable
        final int shrinkSpeed = 4; // TODO: 15/10/2023 make configurable
        int battleLandSize = (int) Math.sqrt(TeamManager.get().count() / GameplayManager.get().getDensity() * battleLandDensityRatio);
        long seconds = (diameter - battleLandSize) / shrinkSpeed;

        setWorldBorder(battleLandSize, seconds);
    }
}
