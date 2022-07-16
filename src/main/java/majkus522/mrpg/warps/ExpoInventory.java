package majkus522.mrpg.warps;

import majkus522.mrpg.Functions;
import majkus522.mrpg.level.LevelController;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ExpoInventory implements InventoryHolder
{
    Inventory inventory;

    public ExpoInventory(Player player)
    {
        inventory = Bukkit.createInventory(this, 27, "Expowiska");

        for(int index = 0; index < 9; index++)
        {
            inventory.setItem(index, getItem(Functions.colors(ChatColor.GREEN) + "EXPO " + (index * 5), LevelController.getLevel(player)));
        }
    }

    ItemStack getItem(String name, int level)
    {
        int requiedLevel = Integer.parseInt(name.split(" ")[1]);
        ItemStack item = new ItemStack(level >= requiedLevel ? Material.EMERALD : Material.REDSTONE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        item.setItemMeta(meta);
        return item;
    }

    @Override
    public Inventory getInventory()
    {
        return inventory;
    }
}