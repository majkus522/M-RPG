package majkus522.mrpg.level;

import majkus522.mrpg.Functions;
import majkus522.mrpg.Main;
import majkus522.mrpg.ScoreboardController;
import majkus522.mrpg.comunication.Add;
import majkus522.mrpg.events.custom.PlayerLevelUpEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LevelController
{
    public static void setLevel(Player player, int input)
    {
        Main.config.set("Level." + player.getUniqueId(), input);
    }

    public static int getLevel(Player player)
    {
        String value = Main.config.getString("Level." + player.getUniqueId());
        return Integer.parseInt(value);
    }

    public static void addLevel(Player player, int input)
    {
        setLevel(player, getLevel(player) + input);
    }

    public static void addExp(Player player, int input)
    {
        int exp = Math.max(getExp(player) + input, 0);
        setExp(player, exp);
        while(exp >= getRequiedExp(player))
        {
            addLevel(player, 1);
            Bukkit.getServer().getPluginManager().callEvent(new PlayerLevelUpEvent(player, getLevel(player)));
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

    public static int getRequiedExp(Player player)
    {
        try
        {
            File file = new File("data/levels.data");
            FileReader fileReader = new FileReader(file);
            List<String> value = new ArrayList<String>();
            String line;
            BufferedReader br = new BufferedReader(fileReader);
            while((line = br.readLine()) != null)
            {
                value.add(line);
            }
            fileReader.close();
            return Integer.parseInt(value.get(getLevel(player)));
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        return 0;
    }

    public static String getPlayerText(Player player)
    {
        return Functions.colors(ChatColor.BOLD, ChatColor.YELLOW) + "[" + getLevel(player) + "]";
    }
}