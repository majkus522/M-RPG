package majkus522.mrpg.enderchest;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public class EnderchestGui implements InventoryHolder
{
    Inventory inventory;

    public EnderchestGui(ItemStack[] content)
    {
        inventory = Bukkit.createInventory(this, content.length > 27 ? 54 : 27, "Enderchest");
        inventory.setContents(content);
    }

    public EnderchestGui(EnderchestGui old)
    {
        inventory = Bukkit.createInventory(this, 54, "Enderchest");
        inventory.setContents(old.getInventory().getContents());
    }

    @Override
    public Inventory getInventory()
    {
        return inventory;
    }
}