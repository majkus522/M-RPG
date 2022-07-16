package majkus522.mrpg.events;

import majkus522.mrpg.events.custom.BlockRightClickEvent;
import majkus522.mrpg.events.custom.ItemRightClickEvent;
import majkus522.mrpg.events.custom.PlatePressedEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Arrays;

public class PlayerInteract implements Listener
{
    @EventHandler
    public void playerInteract(PlayerInteractEvent event)
    {
        Player player = event.getPlayer();
        Action action = event.getAction();

        if(action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK)
        {
            Material[] cancelItems = { Material.WRITTEN_BOOK, Material.MAP };
            event.setCancelled(Arrays.asList(cancelItems).contains(player.getInventory().getItemInMainHand().getType()));
            Bukkit.getServer().getPluginManager().callEvent(new ItemRightClickEvent(player, player.getInventory().getItemInMainHand()));
            if(event.getClickedBlock() != null)
            {
                Material[] canceledBlocks = { Material.ENDER_CHEST, Material.BREWING_STAND };
                event.setCancelled(Arrays.asList(canceledBlocks).contains(event.getClickedBlock().getType()));
                Bukkit.getServer().getPluginManager().callEvent(new BlockRightClickEvent(player, event.getClickedBlock()));
            }
        }
        else if(action == Action.PHYSICAL)
        {
            Material[] plates = { Material.WARPED_PRESSURE_PLATE, Material.ACACIA_PRESSURE_PLATE, Material.BIRCH_PRESSURE_PLATE, Material.CRIMSON_PRESSURE_PLATE,
                    Material.DARK_OAK_PRESSURE_PLATE, Material.HEAVY_WEIGHTED_PRESSURE_PLATE, Material.LIGHT_WEIGHTED_PRESSURE_PLATE, Material.OAK_PRESSURE_PLATE,
                    Material.POLISHED_BLACKSTONE_PRESSURE_PLATE, Material.SPRUCE_PRESSURE_PLATE, Material.STONE_PRESSURE_PLATE };
            if(Arrays.asList(plates).contains(event.getClickedBlock().getType()))
                Bukkit.getServer().getPluginManager().callEvent(new PlatePressedEvent(player, event.getClickedBlock()));
        }
    }
}