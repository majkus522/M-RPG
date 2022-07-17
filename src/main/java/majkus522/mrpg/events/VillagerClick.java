package majkus522.mrpg.events;

import majkus522.mrpg.abilities.AbilityTree;
import majkus522.mrpg.blacksmith.BlacksmithGui;
import majkus522.mrpg.blacksmith.BlacksmithJob;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class VillagerClick implements Listener
{
    @EventHandler
    public void onVillagerClick(PlayerInteractEntityEvent event)
    {
        Entity entity = event.getRightClicked();
        if (entity instanceof Villager)
        {
            if(entity.getCustomName().equalsIgnoreCase(ChatColor.GOLD + "Mentor"))
            {
                Player player = event.getPlayer();
                player.openInventory(new AbilityTree(player).getInventory());
            }
            else if(entity.getCustomName().equalsIgnoreCase(ChatColor.GOLD + "Kowal"))
            {
                Player player = event.getPlayer();
                player.openInventory(new BlacksmithGui(BlacksmithJob.main).getInventory());
            }
        }
    }
}
