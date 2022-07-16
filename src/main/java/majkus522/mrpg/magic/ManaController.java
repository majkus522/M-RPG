package majkus522.mrpg.magic;

import majkus522.mrpg.ScoreboardController;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class ManaController
{
    public static HashMap<Player, Integer> playerMana = new HashMap<Player, Integer>();
    public static HashMap<Player, Integer> playerMaxMana = new HashMap<Player, Integer>();

    public static int getPlayerMana(Player player)
    {
        return playerMana.get(player);
    }

    public static int getPlayerMaxMana(Player player)
    {
        return playerMaxMana.get(player);
    }

    public static void setPlayerMana(Player player, int value)
    {
        playerMana.put(player, value);
    }

    public static void setPlayerMaxMana(Player player, int value)
    {
        playerMaxMana.put(player, value);
    }

    public static void addMana(Player player, int value)
    {
        int mana = playerMana.get(player) + value;
        int result = Math.min(mana, playerMaxMana.get(player));
        playerMana.put(player, result);
        ScoreboardController.createScoreboard(player);
    }

    public static boolean removeMana(Player player, int value)
    {
        int mana = playerMana.get(player);
        if(mana >= value)
        {
            playerMana.put(player, mana - value);
            ScoreboardController.createScoreboard(player);
            return true;
        }
        return false;
    }
}