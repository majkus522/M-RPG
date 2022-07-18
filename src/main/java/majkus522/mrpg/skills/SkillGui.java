package majkus522.mrpg.skills;

import majkus522.mrpg.Functions;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SkillGui implements InventoryHolder
{
    Inventory inventory;

    public SkillGui(Player player, SkillRarity tab)
    {
        if(tab == null)
        {
            inventory = Functions.fillEmpty(Bukkit.createInventory(this, 27, "Umiejętności"));
            inventory.setItem(10, createBook(ChatColor.WHITE + "Common"));
            inventory.setItem(12, createBook(ChatColor.YELLOW + "Extra"));
            if(SkillController.haveSkill(player, SkillRarity.unique))
                inventory.setItem(14, createBook(ChatColor.AQUA + "Unique"));
            if(SkillController.haveSkill(player, SkillRarity.ultimate))
                inventory.setItem(16, createBook(ChatColor.LIGHT_PURPLE + "Ultimate"));
        }
        else
        {
            inventory = Functions.fillEmpty(Bukkit.createInventory(this, 54, "Umiejętności - " + tab));
        }
    }

    ItemStack createBook(String name)
    {
        ItemStack item = new ItemStack(Material.BOOK);
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