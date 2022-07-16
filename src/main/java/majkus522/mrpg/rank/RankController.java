package majkus522.mrpg.rank;

import majkus522.mrpg.Functions;
import majkus522.mrpg.Main;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class RankController
{
    public static void setRank(Player player, Rank rank)
    {
        Main.config.set("Ranks." + player.getUniqueId(), rank.toString());
        switch (rank)
        {
            case Admin:
                player.setOp(true);
                break;

            case Gracz:
            case VIP:
                player.setOp(false);
                break;
        }
        Functions.setPlayerName(player);
    }

    public static Rank getRank(Player player)
    {
        String value = Main.config.getString("Ranks." + player.getUniqueId());
        return Rank.getRank(value);
    }

    public static String getPlayerText(Player player)
    {
        switch (getRank(player))
        {
            case Admin:
                return (ChatColor.RED + "[Admin] ");

            case Gracz:
                return (ChatColor.GREEN + "[Gracz] ");

            case VIP:
                return (ChatColor.GOLD + "[VIP] ");
        }
        return "";
    }
}