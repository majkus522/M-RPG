package majkus522.mrpg.alchemy;

import majkus522.mrpg.Functions;
import majkus522.mrpg.events.custom.BlockRightClickEvent;
import majkus522.mrpg.playerClass.ClassController;
import majkus522.mrpg.playerClass.PlayerClass;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.inventory.ItemStack;

public class AlchemyController implements Listener
{
    @EventHandler
    public void itemTake(InventoryClickEvent event)
    {
        if(event.getInventory().getHolder() instanceof AlchemyGui)
        {
            if(event.getCurrentItem().getType() != Material.WATER_BUCKET)
            {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void itemMove(InventoryMoveItemEvent event)
    {
        if(event.getDestination() instanceof AlchemyGui && event.getItem().getType() == Material.WATER_BUCKET)
            for(int index = 0; index < 7; index++)
                event.getDestination().setItem(28 + index, new ItemStack(Material.AIR));
    }

    @EventHandler
    public void brewingStandRightClick(BlockRightClickEvent event)
    {
        if(event.getBlock().getType() == Material.BREWING_STAND)
        {
            Player player = event.getPlayer();
            if(ClassController.getClass(player) == PlayerClass.Alchemik)
                player.openInventory(new AlchemyGui(event.getPlayer()).getInventory());
            else
                player.sendMessage(Functions.colors(ChatColor.RED, ChatColor.BOLD) + "Tylko alchemicy mogą warzyć mikstyry");
        }
    }
}