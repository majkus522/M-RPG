package majkus522.mrpg.blacksmith;

import majkus522.mrpg.events.custom.ItemRightClickEvent;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SchematicController implements Listener
{
    static FileConfiguration config;

    public static void load()
    {
        config = YamlConfiguration.loadConfiguration(new File("data/schematics.data"));
    }

    ItemStack[] getSchematic(String name)
    {
        return ((List<ItemStack>) config.get("Schematic." + name)).toArray(new ItemStack[0]);
    }

    public static List<ItemStack[]> getSchematics()
    {
        List<ItemStack[]> placeholder = new ArrayList<ItemStack[]>();
        config.getConfigurationSection("Schematic").getKeys(false).forEach(key ->{
            ItemStack[] content = ((List<ItemStack>) config.get("Schematic." + key)).toArray(new ItemStack[0]);
            placeholder.add(content);
        });
        return placeholder;
    }

    @EventHandler
    public void onSchematicOpen(ItemRightClickEvent event)
    {
        ItemStack item = event.getItem();
        if(item.getType() == Material.MAP)
        {
            Player player = event.getPlayer();
            String name = item.getItemMeta().getDisplayName().split(" ")[2];
            player.openInventory(new SchematicGui(name, getSchematic(name)).getInventory());
        }
    }

    @EventHandler
    public void clicked(InventoryClickEvent event)
    {
        if(event.getClickedInventory() == null)
            return;
        if(event.getClickedInventory().getHolder() instanceof SchematicGui)
            event.setCancelled(true);
    }
}