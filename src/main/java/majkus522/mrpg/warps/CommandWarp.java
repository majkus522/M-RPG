package majkus522.mrpg.warps;

import majkus522.mrpg.Functions;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandWarp implements CommandExecutor
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
        Location loc;
        switch(args[0].toLowerCase())
        {
            case "portale":
                player.sendMessage(Functions.getCommandExecutor("warp") + ChatColor.YELLOW + "Teleportowanie");
                Functions.wait(2);
                Functions.teleport(player, "world", 10, 208, 0);
                break;

            case "targ":
                player.sendMessage(Functions.getCommandExecutor("warp") + ChatColor.YELLOW + "Teleportowanie");
                Functions.wait(2);
                Functions.teleport(player, "world", -10, 208, 0);
                break;

            default:
                player.sendMessage(Functions.getCommandExecutor("warp") + ChatColor.YELLOW + "Taki warp nie istnieje");
                break;
        }
        return true;
    }
}