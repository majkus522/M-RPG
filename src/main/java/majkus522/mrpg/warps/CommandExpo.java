package majkus522.mrpg.warps;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandExpo implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (!(sender instanceof Player))
        {
            sender.sendMessage("Te komenda może być wykonana tylko przez administratora");
            return true;
        }
        Player player = (Player) sender;
        ExpoInventory inventory = new ExpoInventory(player);
        player.openInventory(inventory.getInventory());
        return true;
    }
}
