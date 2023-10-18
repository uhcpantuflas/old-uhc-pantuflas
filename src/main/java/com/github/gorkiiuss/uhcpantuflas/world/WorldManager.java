package com.github.gorkiiuss.uhcpantuflas.world;

import com.github.gorkiiuss.uhcpantuflas.gameplay.GameplayManager;
import com.github.gorkiiuss.uhcpantuflas.player.PlayerManager;
import com.github.gorkiiuss.uhcpantuflas.teams.TeamManager;
import org.bukkit.*;
import org.bukkit.command.CommandSender;

import java.util.List;

public class WorldManager {
    private static WorldManager instance;
    private int diameter;

    private WorldManager() {

    }

    public void init() {
        List<World> worlds = Bukkit.getWorlds();

        WorldBorder overworldWorldBorder = worlds.get(0).getWorldBorder();

        final int initialWorldSize = 100; // TODO: 15/10/2023 make configurable

        overworldWorldBorder.setCenter(0, 0);
        overworldWorldBorder.setSize(initialWorldSize);

        worlds.forEach(world -> world.setGameRule(GameRule.NATURAL_REGENERATION, false));
        setDaylightCycle(false);
        worlds.get(0).setTime(0);

        if (
                // Creation of spawn platform
                worlds.get(0).getHighestBlockYAt(0, 0) <= 40 ||
                worlds.get(0).getBlockAt(getOverworld00().subtract(0, 1, 0)).isLiquid()
        ) {
            createPlatform(0, 0, 90, 0);
        }

        createLobby();
    }

    private void createLobby() {
        createPlatform(2, 500, 100, 500);
    }

    private void createPlatform(int worldIdx, int x0, int y, int z0) {
        World world = Bukkit.getWorlds().get(worldIdx);
        for (int x = 0; x < 25; x++) {
            for (int z = 0; z < 25; z++) {
                world.getBlockAt(x - 12 + x0, y, z - 12 + z0).setType(Material.GLASS);
            }
        }
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

    public int getY(int x, int yOffset, int z) {
        return Bukkit.getWorlds().get(0).getHighestBlockYAt(x, z) + yOffset;
    }

    public void wipe() {
        deleteSpawnPlatform();
    }

    public void deleteSpawnPlatform() {
        World world = Bukkit.getWorlds().get(0);
        for (int x = 0; x < 25; x++) {
            for (int z = 0; z < 25; z++) {
                world.getBlockAt(x - 12, 90, z - 12).setType(Material.AIR);
            }
        }
    }

    public void setDaylightCycle(boolean daylightCycle) {
        Bukkit.getWorlds().forEach(world -> world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, daylightCycle));
    }

    public void preGenerateChunks() {
        Server server = Bukkit.getServer();
        CommandSender sender = server.getConsoleSender();
        System.out.println("Selecting chunky world");
        server.dispatchCommand(
                sender,
                "chunky world " + Bukkit.getWorlds().get(0).getName()
        );
        System.out.println("Selecting worldborder chunky");
        server.dispatchCommand(
                sender,
                "chunky worldborder"
        );
        System.out.println("Starting chunky");
        server.dispatchCommand(
                sender,
                "chunky start"
        );
    }
}
