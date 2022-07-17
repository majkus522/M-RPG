package majkus522.mrpg.level;

import majkus522.mrpg.Functions;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandLevel implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        Player player = Bukkit.getPlayer(args[0]);
        if(player == null)
        {
            sender.sendMessage(Functions.getCommandExecutor("level") + ChatColor.RED + "Gracza nie ma na serwerze");
            return true;
        }
        int value = Integer.parseInt(args[3]);
        switch(args[1])
        {
            case "level":
                switch (args[2])
                {
                    case "add":
                        LevelController.addLevel(player, value);
                        break;

                    case "set":
                        LevelController.setLevel(player, value);
                        break;
                }
                break;

            case "exp":
                switch (args[2])
                {
                    case "add":
                        LevelController.addExp(player, value);
                        break;

                    case "set":
                        LevelController.setExp(player, value);
                        break;
                }
                break;
        }
        return true;
    }
}