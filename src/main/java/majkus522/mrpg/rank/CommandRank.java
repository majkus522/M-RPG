package majkus522.mrpg.rank;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandRank implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        switch(args[1].toLowerCase())
        {
            case "player":
            case "gracz":
                RankController.setRank(Bukkit.getPlayer(args[0]), Rank.Gracz);
                Bukkit.getPlayer(args[0]).setOp(false);
                break;

            case "admin":
                RankController.setRank(Bukkit.getPlayer(args[0]), Rank.Admin);
                Bukkit.getPlayer(args[0]).setOp(true);
                break;
        }
        return true;
    }
}