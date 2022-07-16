package majkus522.mrpg;

import majkus522.mrpg.enderchest.EnderchestController;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ScanCommand implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        Player player = Bukkit.getPlayer(args[1]);
        if(player != null)
        {
            sender.sendMessage("Przedmioty gracza " + args[1] + ":");
            switch(args[0].toLowerCase())
            {
                case "main":
                    for(ItemStack item : player.getInventory().getContents())
                        if(item != null)
                            sender.sendMessage(item.getItemMeta().getDisplayName() + " x " + item.getAmount());
                    break;

                case "armor":
                    for(ItemStack item : player.getInventory().getArmorContents())
                        if(item != null)
                            sender.sendMessage(item.getItemMeta().getDisplayName());
                    break;

                case "enderchest":
                    for(ItemStack item : EnderchestController.enderchests.get(player.getUniqueId().toString()))
                        if(item != null)
                            sender.sendMessage(item.getItemMeta().getDisplayName() + " x " + item.getAmount());
                    break;

                case "extra":
                    for(ItemStack item : player.getInventory().getExtraContents())
                        if(item != null)
                            sender.sendMessage(item.getItemMeta().getDisplayName() + " x " + item.getAmount());
                    break;
            }
        }
        return true;
    }
}
