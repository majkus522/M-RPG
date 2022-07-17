package majkus522.mrpg.enderchest;

import majkus522.mrpg.Main;
import majkus522.mrpg.events.custom.BlockRightClickEvent;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EnderchestController implements Listener
{
    public static HashMap<String, ItemStack[]> enderchests = new HashMap<String, ItemStack[]>();

    @EventHandler
    public void onEnderchestClose(InventoryCloseEvent event)
    {
        if(event.getView().getTitle() == "Enderchest")
        {
            enderchests.put(event.getPlayer().getUniqueId().toString(), event.getInventory().getContents());
        }
    }

    @EventHandler
    public void enderchestRightClick(BlockRightClickEvent event)
    {
        if (event.getBlock().getType() == Material.ENDER_CHEST)
        {
            Player player = event.getPlayer();
            player.openInventory(new EnderchestGui(enderchests.get(player.getUniqueId().toString())).getInventory());
        }
    }

    public static void saveEnderchests()
    {
        for(Map.Entry<String, ItemStack[]> entry : enderchests.entrySet())
        {
            Main.config.set("Enderchest." + entry.getKey(), entry.getValue());
        }
    }

    public static void loadEnderchests()
    {
        Main.config.getConfigurationSection("Enderchest").getKeys(false).forEach(key ->{
            ItemStack[] content = ((List<ItemStack>) Main.config.get("Enderchest." + key)).toArray(new ItemStack[0]);
            enderchests.put(key, content);
        });
    }
}