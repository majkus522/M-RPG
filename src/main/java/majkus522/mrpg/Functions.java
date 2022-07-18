package majkus522.mrpg;

import majkus522.mrpg.clan.ClanController;
import majkus522.mrpg.level.LevelController;
import majkus522.mrpg.playerClass.ClassController;
import majkus522.mrpg.rank.RankController;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.checkerframework.common.returnsreceiver.qual.This;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class Functions
{
    public static void wait(int seconds)
    {
        try
        {
            TimeUnit.SECONDS.sleep(seconds);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    public static void teleport(Player player, String name, int x, int y, int z)
    {
        teleport(player, name, x, y, z, 0, 0);
    }

    public static void teleport(Player player, String name, int x, int y, int z, int yaw, int pitch)
    {
        Location loc = new Location(Bukkit.getWorld("world"), x + 0.5, y + 0.5, z + 0.5, yaw, pitch);
        player.teleport(loc);
    }

    public static void setPlayerName(Player player)
    {
        String name = LevelController.getPlayerText(player) + ClanController.getPlayerText(player) + ClassController.getPlayerText(player) + RankController.getPlayerText(player) + player.getName();
        player.setDisplayName(name);
        player.setPlayerListName(name);
    }

    public static boolean isBetween(int x, int lower, int upper)
    {
        return lower <= x && x <= upper;
    }

    public static int getRandomNumber(int min, int max)
    {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public static String getCommandExecutor(String input)
    {
        input = input.toUpperCase();
        return colors(ChatColor.BOLD, ChatColor.RED) + "[" + ChatColor.GREEN + input + ChatColor.RED + "] " + ChatColor.RESET;
    }

    public static ItemStack emptySlot()
    {
        ItemStack item = new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(" ");
        item.setItemMeta(meta);
        return item;
    }

    public static String colors(ChatColor input)
    {
        return ChatColor.RESET + "" + input;
    }

    public static String colors(ChatColor input, ChatColor adder)
    {
        return ChatColor.RESET + "" + input + "" + adder;
    }

    public static String colors(ChatColor input, ChatColor adder, ChatColor addition)
    {
        return ChatColor.RESET + "" + input + "" + adder + "" + addition;
    }

    public static String colors(String msg)
    {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    public static <T, E> T getKeyByValue(Map<T, E> map, E value)
    {
        for (Map.Entry<T, E> entry : map.entrySet())
            if (Objects.equals(value, entry.getValue()))
                return entry.getKey();
        return null;
    }

    public static Inventory fillEmpty(@This Inventory input)
    {
        ItemStack empty = emptySlot();
        for(int index = 0; index < input.getSize(); index++)
        {
            input.setItem(index, empty);
        }
        return input;
    }
}