package majkus522.mrpg.items;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class Tools
{
    public static void init()
    {
        initLumberAxe();
    }

    static void initLumberAxe()
    {
        ItemStack item = new ItemStack(Material.IRON_AXE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§aSiekiera drwala");
        meta.addEnchant(Enchantment.DURABILITY, 5, true);
        meta.setLore(Arrays.asList(new String[] {"Standardowa siekierka uzywana przez drwali"}));
        item.setItemMeta(meta);
        ItemManager.lumberAxe = item;
    }
}