package majkus522.mrpg.blacksmith;

import majkus522.mrpg.Functions;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class BlacksmithController implements Listener
{
    @EventHandler
    public void itemTake(InventoryClickEvent event)
    {
        if(event.getClickedInventory() == null)
            return;
        Player player = (Player)event.getWhoClicked();
        if(event.getClickedInventory().getHolder() instanceof BlacksmithGui)
        {
            if(event.getView().getTitle() == "Kowal")
            {
                BlacksmithJob type = BlacksmithJob.main;
                switch (event.getCurrentItem().getType())
                {
                    case IRON_HELMET:
                        type = BlacksmithJob.Helmet;
                        break;

                    case IRON_CHESTPLATE:
                        type = BlacksmithJob.Chestplate;
                        break;

                    case IRON_SWORD:
                        type = BlacksmithJob.Weapon;
                        break;

                    case IRON_LEGGINGS:
                        type = BlacksmithJob.Leggings;
                        break;

                    case IRON_BOOTS:
                        type = BlacksmithJob.Boots;
                        break;
                }
                if(event.getCurrentItem().getType() != Material.GRAY_STAINED_GLASS_PANE)
                {
                    player.closeInventory();
                    player.openInventory(new BlacksmithGui(type).getInventory());
                }
                event.setCancelled(true);
            }
            else if(event.getView().getTitle() == "Wytwórz : Buty" || event.getView().getTitle() == "Wytwórz : Hełm" ||
                    event.getView().getTitle() == "Wytwórz : Napierśnik" || event.getView().getTitle() == "Wytwórz : Spodnie" ||
                    event.getView().getTitle() == "Wytwórz : Broń")
            {
                if(event.getCurrentItem().getType() == Material.GRAY_STAINED_GLASS_PANE)
                    event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void finish(InventoryCloseEvent event)
    {
        if(event.getInventory().getHolder() instanceof BlacksmithGui)
        {
            Player player = (Player)event.getPlayer();
            boolean valid = true;
            for(ItemStack item : event.getInventory().getContents())
            {
                if(item == null)
                {
                    player.sendMessage(Functions.colors(ChatColor.RED, ChatColor.BOLD) + "Wszystkie sloty muszą być zapełnione");
                    valid = false;
                    break;
                }
            }
            if(!valid)
                for(ItemStack item : event.getInventory().getContents())
                    if(item != null && item.getType() != Material.GRAY_STAINED_GLASS_PANE)
                        player.getInventory().addItem(item);
            if(event.getView().getTitle() != "Kowal")
                itemsFilled((Player)event.getPlayer(), BlacksmithJob.getJob(event.getView().getTitle().split(" ")[2]), event.getInventory());
        }
    }

    void itemsFilled(Player player, BlacksmithJob type, Inventory inventory)
    {

    }
}
