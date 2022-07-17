package majkus522.mrpg;

import majkus522.mrpg.abilities.AbilityController;
import majkus522.mrpg.alchemy.AlchemyController;
import majkus522.mrpg.blacksmith.BlacksmithController;
import majkus522.mrpg.blacksmith.SchematicController;
import majkus522.mrpg.clan.ClanController;
import majkus522.mrpg.clan.CommandClan;
import majkus522.mrpg.comunication.AddsController;
import majkus522.mrpg.comunication.CommandMsg;
import majkus522.mrpg.comunication.OnPlayerChat;
import majkus522.mrpg.enderchest.CommandEnderchest;
import majkus522.mrpg.enderchest.EnderchestController;
import majkus522.mrpg.events.*;
import majkus522.mrpg.items.CommandItem;
import majkus522.mrpg.items.ItemManager;
import majkus522.mrpg.level.CommandLevel;
import majkus522.mrpg.level.PlayerLevelUp;
import majkus522.mrpg.mobs.CommandMob;
import majkus522.mrpg.money.CommandPay;
import majkus522.mrpg.playerClass.ClassController;
import majkus522.mrpg.playerClass.CommandClass;
import majkus522.mrpg.rank.CommandRank;
import majkus522.mrpg.warps.*;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public final class Main extends JavaPlugin
{
    public static FileConfiguration config;

    @Override
    public void onEnable()
    {
        config = getConfig();
        try
        {
            config.load("data/config.data");
        }
        catch (IOException | InvalidConfigurationException e)
        {
            e.printStackTrace();
        }

        //Register Events
        getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
        getServer().getPluginManager().registerEvents(new BlockBreak(), this);
        getServer().getPluginManager().registerEvents(new MobKilled(), this);
        getServer().getPluginManager().registerEvents(new ClassController(), this);
        getServer().getPluginManager().registerEvents(new ExpoRightClick(), this);
        getServer().getPluginManager().registerEvents(new EnderchestController(), this);
        getServer().getPluginManager().registerEvents(new OnPlayerChat(), this);
        getServer().getPluginManager().registerEvents(new VillagerClick(), this);
        getServer().getPluginManager().registerEvents(new AlchemyController(), this);
        getServer().getPluginManager().registerEvents(new AbilityController(), this);
        getServer().getPluginManager().registerEvents(new PlayerInteract(), this);
        getServer().getPluginManager().registerEvents(new PlayerLevelUp(), this);
        getServer().getPluginManager().registerEvents(new BlacksmithController(), this);
        getServer().getPluginManager().registerEvents(new SchematicController(), this);

        //Initialize items
        ItemManager.init();

        //Register Commands
        this.getCommand("item").setExecutor(new CommandItem());
        this.getCommand("mob").setExecutor(new CommandMob());
        this.getCommand("warp").setExecutor(new CommandWarp());
        this.getCommand("warps").setExecutor(new CommandWarps());
        this.getCommand("spawn").setExecutor(new CommandSpawn());
        this.getCommand("rank").setExecutor(new CommandRank());
        this.getCommand("class").setExecutor(new CommandClass());
        this.getCommand("msg").setExecutor(new CommandMsg());
        this.getCommand("expo").setExecutor(new CommandExpo());
        this.getCommand("enderchest").setExecutor(new CommandEnderchest());
        this.getCommand("pay").setExecutor(new CommandPay());
        this.getCommand("klan").setExecutor(new CommandClan());
        this.getCommand("mreload").setExecutor(new mReloadCommand());
        this.getCommand("scan").setExecutor(new ScanCommand());
        this.getCommand("level").setExecutor(new CommandLevel());

        EnderchestController.loadEnderchests();
        AddsController.load();
        SchematicController.load();

        int minute = (int) (1200L * 5);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> AddsController.display(), 0L, minute);

        ClanController.loadClan();
    }

    @Override
    public void onDisable()
    {
        EnderchestController.saveEnderchests();
        ClanController.saveClan();
        try
        {
            config.save("data/config.data");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}