package majkus522.mrpg.items;

import majkus522.mrpg.Functions;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class Herbs
{
    public static void init()
    {
        initRumianek();
        initMniszek();
    }

    static void initRumianek()
    {
        ItemStack item = new ItemStack(Material.OXEYE_DAISY, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Functions.colors(ChatColor.AQUA) + "Rumianek");
        meta.setLore(Arrays.asList(new String[]{Functions.colors(ChatColor.GREEN, ChatColor.BOLD) + "Przedmiot ziołowy"}));
        item.setItemMeta(meta);
        ItemManager.rumianek = item;
    }

    static void initMniszek()
    {
        ItemStack item = new ItemStack(Material.DANDELION, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Functions.colors(ChatColor.AQUA) + "Mniszek lekarski");
        meta.setLore(Arrays.asList(new String[]{Functions.colors(ChatColor.GREEN, ChatColor.BOLD) + "Przedmiot ziołowy"}));
        item.setItemMeta(meta);
        ItemManager.mniszek = item;
    }
}