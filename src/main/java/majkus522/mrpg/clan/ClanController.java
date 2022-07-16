package majkus522.mrpg.clan;

import majkus522.mrpg.Main;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ClanController
{
    public static List<Clan> clans;
    public static HashMap<Player, Boolean> clanChatEnabled = new HashMap<Player, Boolean>();

    public static void saveClan()
    {
        for(Clan clan : clans)
        {
            Main.config.set("Klan." + clan.name, clan.toString());
        }
    }

    public static void loadClan()
    {
        clans = new ArrayList<Clan>();
        Main.config.getConfigurationSection("Klan").getKeys(false).forEach(key ->{
            Clan clan = new Clan(key, Main.config.getString("Klan." + key));
            System.out.println(key);
            clans.add(clan);
        });
    }

    public static Clan getPlayerClan(Player player)
    {
        if(clans.isEmpty())
            return null;
        for(Clan clan : clans)
        {
            if(clan.leader.equals(player.getName()))
                return clan;
            else if(clan.members.contains(player.getName()))
                return clan;
        }
        return null;
    }

    public static String getPlayerText(Player player)
    {
        if(getPlayerClan(player) != null)
            return ChatColor.AQUA + "[" + getPlayerClan(player).name + "]";
        return "";
    }

    public static boolean clanExist(String name)
    {
        return Main.config.contains(name);
    }

    public static Clan getClan(String name)
    {
        if(clans.isEmpty())
            return null;
        for(Clan clan : clans)
            if(clan.name.toLowerCase() == name.toLowerCase())
                return clan;
        return null;
    }

    public static List<String> getClanMembers(Clan clan)
    {
        List<String> members = clan.members;
        members.add(0, clan.leader);
        return members;
    }
}