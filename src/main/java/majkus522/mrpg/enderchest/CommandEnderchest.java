package majkus522.mrpg.enderchest;

import majkus522.mrpg.Functions;
import majkus522.mrpg.money.MoneyController;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandEnderchest implements CommandExecutor
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
        EnderchestGui inventory = new EnderchestGui(EnderchestController.enderchests.get(player.getUniqueId().toString()));
        if(args.length >= 1 && args[0].equalsIgnoreCase("upgrade") && inventory.getInventory().getSize() == 27)
        {
            if(MoneyController.haveMoney(player, 2000))
            {
                inventory = new EnderchestGui(inventory);
                MoneyController.addMoney(player, -2000);
                player.sendMessage(Functions.getCommandExecutor("enderchest") + Functions.colors(ChatColor.GREEN) + "Twój enderchest został ulepszony");
            }
            else
                player.sendMessage(Functions.getCommandExecutor("enderchest") + Functions.colors(ChatColor.RED) + "Potrzebujesz 2000$ aby ulepszyć swojego Enderchesta");
        }
        player.openInventory(inventory.getInventory());
        return true;
    }
}