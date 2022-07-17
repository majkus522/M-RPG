package majkus522.mrpg.warps;

import majkus522.mrpg.Functions;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ExpoRightClick implements Listener
{
    @EventHandler
    public void inventoryClick(InventoryClickEvent event)
    {
        if(event.getClickedInventory() == null)
            return;
        if(event.getClickedInventory().getHolder() instanceof ExpoGui)
        {
            event.setCancelled(true);
            Player player = (Player)event.getWhoClicked();
            if(event.getCurrentItem() == null)
                return;
            if(event.getCurrentItem().getType() == Material.REDSTONE)
            {
                player.sendMessage(Functions.getCommandExecutor("expo") + ChatColor.RED + "Twój poziom jest za niski");
                player.closeInventory();
            }
            else if(event.getCurrentItem().getType() == Material.EMERALD)
            {
                player.closeInventory();
                player.sendMessage(Functions.getCommandExecutor("warp") + ChatColor.YELLOW + "Teleportowanie");
                Functions.wait(2);
                int expoLevel = Integer.parseInt(event.getCurrentItem().getItemMeta().getDisplayName().split(" ")[1]);
                switch(expoLevel)
                {
                    case 0:
                        Functions.teleport(player, "expo/0", 0, 12, 0, 90, 0);
                        break;

                    case 5:
                        Functions.teleport(player, "expo/5", 0, 12, 0);
                        break;
                }
            }
        }
    }
}