package majkus522.mrpg.comunication;

import majkus522.mrpg.Functions;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandMsg implements CommandExecutor
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
        Player reciver = Bukkit.getPlayer(args[0]);
        if(reciver != null)
        {
            String message = "";
            for(int index = 1; index < args.length; index++)
            {
                if(index != 1)
                    message += " ";
                message += args[index];
            }
            player.sendMessage(Functions.getCommandExecutor("msg") + ChatColor.YELLOW + "[" + ChatColor.WHITE + "ja" + ChatColor.YELLOW + " >>> " + ChatColor.WHITE + args[0] + ChatColor.YELLOW + "] " + ChatColor.RESET + message);
            reciver.sendMessage(Functions.getCommandExecutor("msg") + ChatColor.YELLOW + "[" + ChatColor.WHITE + args[0] + ChatColor.YELLOW + " >>> " + ChatColor.WHITE + "ja" + ChatColor.YELLOW + "] " + ChatColor.RESET + message);
        }
        else
        {
            player.sendMessage(Functions.getCommandExecutor("msg") + ChatColor.RED + "Taki gracz nie istnieje");
        }
        return true;
    }
}
