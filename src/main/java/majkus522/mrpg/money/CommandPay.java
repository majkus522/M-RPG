package majkus522.mrpg.money;

import majkus522.mrpg.Functions;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class CommandPay implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if(sender instanceof ConsoleCommandSender)
        {
            Player reciver = Bukkit.getPlayer(args[0]);
            if(reciver == null)
            {
                System.out.println("Gracza nie ma na serwerze");
                return true;
            }
            int value = Integer.parseInt(args[1]);
            MoneyController.addMoney(reciver, value);
            return true;
        }
        if (!(sender instanceof Player))
        {
            sender.sendMessage("Te komenda może być wykonana tylko przez gracza");
            return true;
        }
        Player player = (Player) sender;
        Player reciver = Bukkit.getPlayer(args[0]);
        if(reciver == null)
        {
            player.sendMessage(Functions.getCommandExecutor("pay") + ChatColor.RED + "Gracza nie ma na serwerze");
            return true;
        }
        int value = Integer.parseInt(args[1]);
        if(MoneyController.haveMoney(player, value))
        {
            MoneyController.addMoney(player, -value);
            player.sendMessage(Functions.getCommandExecutor("pay") + ChatColor.GREEN + "Zapłaciłeś " + ChatColor.YELLOW + value + "$ " + ChatColor.GREEN + args[0]);
            MoneyController.addMoney(reciver, value);
            player.sendMessage(Functions.getCommandExecutor("pay") + ChatColor.GREEN + player.getName() + " zapłacił ci " + ChatColor.YELLOW + value + "$");
        }
        else
            player.sendMessage(Functions.getCommandExecutor("pay") + ChatColor.RED + "Brak wystarczających środków");
        return true;
    }
}