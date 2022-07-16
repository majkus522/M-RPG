package majkus522.mrpg.items;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CommandItem implements CommandExecutor
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
        ItemStack item = new ItemStack(Material.AIR);
        switch(args[0])
        {
            case "leatherCow":
                item = ItemManager.leatherCow;
                break;

            case "leatherWolf":
                item = ItemManager.leatherWolf;
                break;

            case "wool":
                item = ItemManager.wool;
                break;

            case "lumberAxe":
                item = ItemManager.lumberAxe;
                break;

            case "mniszek":
                item = ItemManager.mniszek;
                break;

            case "rumianek":
                item = ItemManager.rumianek;
                break;
        }
        int count;
        if(args.length == 2)
            count = Integer.parseInt(args[1]);
        else
            count = 1;
        item.setAmount(count);
        player.getInventory().addItem(item);
        return true;
    }
}