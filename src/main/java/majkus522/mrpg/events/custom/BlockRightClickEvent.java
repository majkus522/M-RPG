package majkus522.mrpg.events.custom;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class BlockRightClickEvent extends Event
{
    private static final HandlerList handlers = new HandlerList();

    Player player;
    Block block;

    public BlockRightClickEvent(Player player, Block block)
    {
        this.player = player;
        this.block = block;
    }

    public Player getPlayer()
    {
        return player;
    }

    public Block getBlock()
    {
        return block;
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
