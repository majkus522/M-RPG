package majkus522.mrpg.events.custom;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerLevelUpEvent extends Event
{
    static final HandlerList handlers = new HandlerList();

    Player player;
    int level;

    public PlayerLevelUpEvent(Player player, int level)
    {
        this.player = player;
        this.level = level;
    }

    public Player getPlayer()
    {
        return player;
    }

    public int getLevel()
    {
        return level;
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