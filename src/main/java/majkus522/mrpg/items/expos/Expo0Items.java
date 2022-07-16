package majkus522.mrpg.items.expos;

import majkus522.mrpg.items.ItemManager;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Expo0Items
{
    public static void init()
    {
        initWool();
        initLeatherCow();
        initLeatherWolf();
        initMeat();
    }

    static void initWool()
    {
        ItemStack item = new ItemStack(Material.WHITE_WOOL);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§aWełna");
        meta.addEnchant(Enchantment.DURABILITY, 10, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        ItemManager.wool = item;
    }

    static void initLeatherCow()
    {
        ItemStack item = new ItemStack(Material.LEATHER);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§aSkóra krowy");
        meta.addEnchant(Enchantment.DURABILITY, 10, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        ItemManager.leatherCow = item;
    }

    static void initLeatherWolf()
    {
        ItemStack item = new ItemStack(Material.LEATHER);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§aSkóra wilka");
        meta.addEnchant(Enchantment.DURABILITY, 10, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        ItemManager.leatherWolf = item;
    }

    static void initMeat()
    {
        ItemStack item = new ItemStack(Material.COOKED_CHICKEN);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§9Mięso");
        item.setItemMeta(meta);
        ItemManager.meat = item;
    }
}