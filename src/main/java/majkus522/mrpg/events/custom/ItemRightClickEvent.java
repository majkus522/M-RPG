package majkus522.mrpg.events.custom;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

public class ItemRightClickEvent extends Event
{
    private static final HandlerList handlers = new HandlerList();

    Player player;
    ItemStack item;

    public ItemRightClickEvent(Player player, ItemStack item)
    {
        this.player = player;
        this.item = item;
    }

    public Player getPlayer()
    {
        return player;
    }

    public ItemStack getItem()
    {
        return item;
    }

    public HandlerList getHandlers()
    {
        return handlers;
    }

    public static HandlerList getHandlerList()
    {
        return handlers;
    }
}