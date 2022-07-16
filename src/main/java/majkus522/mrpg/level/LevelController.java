package majkus522.mrpg.level;

import majkus522.mrpg.Functions;
import majkus522.mrpg.Main;
import majkus522.mrpg.ScoreboardController;
import majkus522.mrpg.events.custom.PlayerLevelUpEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class LevelController
{
    static int[] levels = new int[] {
        0, 1000, 2500, 3700, 5000
    };

    public static void setLevel(Player player, int input)
    {
        Main.config.set("Level." + player.getUniqueId(), input);
    }

    public static int getLevel(Player player)
    {
        String value = Main.config.getString("Level." + player.getUniqueId());
        return Integer.parseInt(value);
    }

    public static void addExp(Player player, int input)
    {
        int exp = getExp(player) + input;
        int level = getLevel(player);
        setExp(player, exp);
        if(levels[level] <= exp)
        {
            setLevel(player, level + 1);
            Bukkit.getServer().getPluginManager().callEvent(new PlayerLevelUpEvent(player, level));
        }
        ScoreboardController.createScoreboard(player);
    }

    public static int getExp(Player player)
    {
        String value = Main.config.getString("Exp." + player.getUniqueId());
        return Integer.parseInt(value);
    }

    public static void setExp(Player player, int input)
    {
        Main.config.set("Exp." + player.getUniqueId(), input);
    }

    public static String getPlayerText(Player player)
    {
        return Functions.colors(ChatColor.BOLD, ChatColor.YELLOW) + "[" + getLevel(player) + "]";
    }
}