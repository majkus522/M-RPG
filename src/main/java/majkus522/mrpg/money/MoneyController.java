package majkus522.mrpg.money;

import majkus522.mrpg.Main;
import majkus522.mrpg.ScoreboardController;
import org.bukkit.entity.Player;

public class MoneyController
{
    public static void setMoney(Player player, int input)
    {
        Main.config.set("Money." + player.getUniqueId(), input);
    }

    public static int getMoney(Player player)
    {
        String value = Main.config.getString("Money." + player.getUniqueId());
        return Integer.parseInt(value);
    }

    public static void addMoney(Player player, int input)
    {
        setMoney(player, input + getMoney(player));
        ScoreboardController.createScoreboard(player);
    }

    public static boolean haveMoney(Player player, int input)
    {
        return getMoney(player) >= input;
    }
}