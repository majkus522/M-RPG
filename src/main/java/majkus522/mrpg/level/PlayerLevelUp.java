package majkus522.mrpg.level;

import majkus522.mrpg.Functions;
import majkus522.mrpg.abilities.AbilityController;
import majkus522.mrpg.events.custom.PlayerLevelUpEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerLevelUp implements Listener
{
    @EventHandler
    public void onPlayerUp(PlayerLevelUpEvent event)
    {
        Player player = event.getPlayer();
        Bukkit.getServer().broadcastMessage(Functions.colors(ChatColor.GOLD, ChatColor.BOLD) + player.getName() + " awansował na poziom " + event.getLevel());
        int points = 2;
        points += AbilityController.abilityLearned(player, "Punktacja I") ?
                (AbilityController.abilityLearned(player, "Punktacja II") ?
                        (AbilityController.abilityLearned(player, "Punktacja III") ?
                                3 : 2)
                        : 1)
                : 0;
        AbilityController.addPoints(player, points);
    }
}