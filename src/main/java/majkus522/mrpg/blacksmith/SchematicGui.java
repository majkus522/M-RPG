package majkus522.mrpg.blacksmith;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public class SchematicGui implements InventoryHolder
{
    Inventory inventory;

    public SchematicGui(String name, ItemStack[] blueprint)
    {
        inventory = Bukkit.createInventory(this, 54, "Schemat : " + name);
        inventory.setContents(blueprint);
    }

    public Inventory getInventory()
    {
        return inventory;
    }
}
