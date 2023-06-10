package sk.ibadtomas.dzavorkasystem;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import sk.ibadtomas.dzavorkasystem.commands.GamemodeChanger;
import sk.ibadtomas.dzavorkasystem.listener.*;

import java.util.ArrayList;
import java.util.UUID;

public final class MainClass extends JavaPlugin {
    private static MainClass instance;

    public static MainClass getInstance() {
        return instance;
    }
    private ArrayList<UUID> buildModeStatus = new ArrayList<>();
    private static ArrayList<UUID> flyList = new ArrayList<>();

    @Override
    public void onEnable() {
        instance = this;
        getInstance().getCommand("gmsp").setExecutor(new GamemodeChanger());
        getInstance().getCommand("gms").setExecutor(new GamemodeChanger());
        getInstance().getCommand("gmc").setExecutor(new GamemodeChanger());
        getInstance().getCommand("buildmode").setExecutor(new BuildMode());

        getServer().getPluginManager().registerEvents(new BuildMode(), getInstance());
        getServer().getPluginManager().registerEvents(new PlayerJoin(), getInstance());
        getServer().getPluginManager().registerEvents(new PlayerQuit(), getInstance());
        getServer().getPluginManager().registerEvents(new FoodLevelChange(), getInstance());
        getServer().getPluginManager().registerEvents(new ExplosionListener(), getInstance());

        Bukkit.getLogger().info("----------------------------");
        Bukkit.getLogger().warning("  9.A Džavorka Systém");
        Bukkit.getLogger().info("Díky za tie roky <3");
        Bukkit.getLogger().info("Plugin bol zapnutý!");
        Bukkit.getLogger().info("----------------------------");

    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("----------------------------");
        Bukkit.getLogger().warning("  9.A Džavorka Systém");
        Bukkit.getLogger().info("Díky za tie roky <3");
        Bukkit.getLogger().info("Plugin bol vypnutý!");
        Bukkit.getLogger().info("----------------------------");
    }

    public ArrayList getBuildModeStatus() {
        return buildModeStatus;
    }

    public static ArrayList<UUID> getFlyList() {
        return flyList;
    }
}
