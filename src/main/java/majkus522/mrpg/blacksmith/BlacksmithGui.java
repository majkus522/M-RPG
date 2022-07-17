package majkus522.mrpg.blacksmith;

import majkus522.mrpg.Functions;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BlacksmithGui implements InventoryHolder
{
    Inventory inventory;

    public BlacksmithGui(BlacksmithJob type)
    {
        inventory = Bukkit.createInventory(this, type == BlacksmithJob.main ? 27 : 54, getName(type));
        ItemStack empty = Functions.emptySlot();
        if(type != BlacksmithJob.main)
            for(int index = 0; index < 54; index++)
                if(index < 9 || index >= 45 || index % 9 == 0 || index % 9 == 8)
                    inventory.setItem(index, empty);

        switch(type)
        {
            case main:
                for(int index = 0; index < inventory.getSize(); index++)
                    inventory.setItem(index, empty);
                inventory.setItem(11, getOption(Material.IRON_HELMET, "Hełm"));
                inventory.setItem(12, getOption(Material.IRON_CHESTPLATE, "Tunika"));
                inventory.setItem(13, getOption(Material.IRON_SWORD, "Broń"));
                inventory.setItem(14, getOption(Material.IRON_LEGGINGS, "Spodnie"));
                inventory.setItem(15, getOption(Material.IRON_BOOTS, "Buty"));
                break;

            case Boots:
                for(int index = 4; index < 54; index += 9)
                    inventory.setItem(index, empty);
                inventory.setItem(10, empty);
                inventory.setItem(19, empty);
                inventory.setItem(16, empty);
                inventory.setItem(25, empty);
                break;

            case Leggings:
                for(int index = 10; index < 54; index += 9)
                {
                    inventory.setItem(index, empty);
                    inventory.setItem(index + 6, empty);
                }
                for(int index = 22; index < 54; index += 9)
                    inventory.setItem(index, empty);
                break;

            case Helmet:
                inventory.setItem(10, empty);
                inventory.setItem(16, empty);
                inventory.setItem(31, empty);
                for(int index = 39; index < 42; index++)
                    inventory.setItem(index, empty);
                break;
        }
    }

    static ItemStack getOption(Material material, String name)
    {
        ItemStack item = new ItemStack(material, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Functions.colors(ChatColor.RED) + "Wytwórz: " + Functions.colors(ChatColor.BLUE) + name);
        item.setItemMeta(meta);
        return item;
    }

    @Override
    public Inventory getInventory()
    {
        return inventory;
    }

    String getName(BlacksmithJob type)
    {
        switch(type)
        {
            case Helmet:
                return "Wytwórz : Hełm";

            case Chestplate:
                return "Wytwórz : Napierśnik";

            case Leggings:
                return "Wytwórz : Spodnie";

            case Boots:
                return "Wytwórz : Buty";

            case Weapon:
                return "Wytwórz : Broń";

            default:
                return "Kowal";
        }
    }
}