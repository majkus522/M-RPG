package majkus522.mrpg.playerClass;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandClass implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        Player player = Bukkit.getPlayer(args[0]);
        switch(args[1].toLowerCase())
        {
            case "woj":
                ClassController.setClass(player, PlayerClass.Wojownik);
                break;

            case "mag":
                ClassController.setClass(player, PlayerClass.Mag);
                break;

            case "pal":
                ClassController.setClass(player, PlayerClass.Paladyn);
                break;

            case "str":
                ClassController.setClass(player, PlayerClass.Strzelec);
                break;

            case "zab":
                ClassController.setClass(player, PlayerClass.Zabójca);
                break;

            case "pos":
                ClassController.setClass(player, PlayerClass.Poszukiwacz);
                break;

            case "alch":
                ClassController.setClass(player, PlayerClass.Alchemik);
                break;

            case "dummy":
                ClassController.setClass(player, PlayerClass.Dummy);
                player.kickPlayer("Proszę o ponowne dołączenie");
                break;
        }
        return true;
    }
}