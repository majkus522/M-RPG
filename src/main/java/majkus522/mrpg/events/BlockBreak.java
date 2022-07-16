package majkus522.mrpg.events;

import majkus522.mrpg.items.ItemManager;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class BlockBreak implements Listener
{
    @EventHandler
    public void onPlayerDestroyBlock(BlockBreakEvent event)
    {
        event.setExpToDrop(0);
        ItemStack drop = new ItemStack(Material.AIR);
        boolean canBreak = false;
        if(event.getPlayer().isOp())
            return;
        if(event.getPlayer().getItemInUse().equals(ItemManager.lumberAxe) && event.getBlock().getType().equals(Material.SPRUCE_LOG))
        {
            canBreak = true;
            drop = ItemManager.wool;
        }

        if(!canBreak)
        {
            event.setCancelled(true);
            event.getBlock().getLocation().getBlock().setType(event.getBlock().getType());
        }
        else
        {
            event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), drop);
        }
    }
}