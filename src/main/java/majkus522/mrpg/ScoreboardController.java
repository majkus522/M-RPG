package majkus522.mrpg;

import majkus522.mrpg.level.LevelController;
import majkus522.mrpg.magic.ManaController;
import majkus522.mrpg.money.MoneyController;
import majkus522.mrpg.playerClass.ClassController;
import majkus522.mrpg.playerClass.PlayerClass;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

public class ScoreboardController
{
    public static void createScoreboard(Player player)
    {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = manager.getNewScoreboard();
        Objective obj = scoreboard.registerNewObjective("stats", "dummy", "M-RPG");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        Score scoreLine = obj.getScore(ChatColor.BLUE + "=-=-=-=-=-=-=-=-=-=");
        scoreLine.setScore(5);
        Score scoreNick = obj.getScore(ChatColor.GREEN + "Nick: " + player.getName());
        scoreNick.setScore(4);
        Score scoreLevel = obj.getScore(ChatColor.GREEN + "Poziom: " + LevelController.getLevel(player));
        scoreLevel.setScore(3);
        Score scoreExp = obj.getScore(ChatColor.GREEN + "Exp: " + LevelController.getExp(player) + "/" + LevelController.getRequiedExp(player));
        scoreExp.setScore(2);
        Score scoreMoney = obj.getScore(ChatColor.GREEN + "Pieniądze: " + MoneyController.getMoney(player));
        scoreMoney.setScore(1);
        if(ClassController.getClass(player) != PlayerClass.Wojownik)
        {
            Score scoreMana = obj.getScore(ChatColor.LIGHT_PURPLE + "Mana: " + ManaController.getPlayerMana(player) + "/" + ManaController.getPlayerMaxMana(player));
            scoreMana.setScore(1);
        }
        Score scoreLineBottom = obj.getScore(ChatColor.BLUE + "=-=-=-=-=-=-=-=-=-= ");
        scoreLineBottom.setScore(0);
        player.setScoreboard(scoreboard);
    }
}
