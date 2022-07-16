package majkus522.mrpg.events.custom;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlatePressedEvent extends Event implements Cancellable
{
    static final HandlerList handlers = new HandlerList();

    Player player;
    Block plate;
    boolean cancelled;

    public PlatePressedEvent(Player player, Block plate)
    {
        this.player = player;
        this.plate = plate;
    }

    public Player getPlayer()
    {
        return player;
    }

    public Block getPlate()
    {
        return plate;
    }

    public boolean isCancelled()
    {
        return cancelled;
    }

    public void setCancelled(boolean cancel)
    {
        cancelled = cancel;
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
