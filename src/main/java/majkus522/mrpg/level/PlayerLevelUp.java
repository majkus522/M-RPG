package majkus522.mrpg.level;

import majkus522.mrpg.Functions;
import majkus522.mrpg.events.custom.PlayerLevelUpEvent;
import majkus522.mrpg.skills.SkillController;
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
        points += SkillController.haveSkill(player, "Punktacja I") ?
                (SkillController.haveSkill(player, "Punktacja II") ?
                        (SkillController.haveSkill(player, "Punktacja III") ?
                                3 : 2)
                        : 1)
                : 0;
        SkillController.addSkillPoints(player, points);
    }
}