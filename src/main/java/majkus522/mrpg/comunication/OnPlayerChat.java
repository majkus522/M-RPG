package majkus522.mrpg.comunication;

import majkus522.mrpg.clan.ClanController;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.ArrayList;
import java.util.List;

public class OnPlayerChat implements Listener
{
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event)
    {
        Player player = event.getPlayer();
        if(ClanController.clanChatEnabled.get(player))
        {
            event.getRecipients().clear();
            event.getRecipients().addAll(getPlayers(ClanController.getClanMembers(ClanController.getPlayerClan(player))));
            event.setFormat(event.getFormat().replace(">", "").replace("<", "").substring(1));
            event.setMessage(ChatColor.BLUE + "[Chat Klanu]" + ChatColor.RESET + event.getMessage());
        }
        else
        {
            event.setFormat(event.getFormat().replace(">", "").replace("<", "").substring(1));
            event.setMessage(ChatColor.RESET + event.getMessage());
        }
    }

    List<Player> getPlayers(List<String> input)
    {
        List<Player> players = new ArrayList<Player>();
        for(String player : input)
        {
            players.add(Bukkit.getPlayer(player));
        }
        return players;
    }
}