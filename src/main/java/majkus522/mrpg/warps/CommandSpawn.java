package majkus522.mrpg.warps;

import majkus522.mrpg.Functions;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSpawn implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (!(sender instanceof Player))
        {
            sender.sendMessage("Te komenda może być wykonana tylko przez gracza");
            return true;
        }
        Player player = (Player) sender;
        player.sendMessage(Functions.getCommandExecutor("spawn") + ChatColor.YELLOW + "Teleportowanie");
        Functions.wait(2);
        Functions.teleport(player, "world", 0, 11, 0);
        return true;
    }
}