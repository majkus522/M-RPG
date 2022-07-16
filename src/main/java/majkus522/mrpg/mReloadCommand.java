package majkus522.mrpg;

import majkus522.mrpg.blacksmith.SchematicController;
import majkus522.mrpg.comunication.AddsController;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;

import java.io.IOException;

public class mReloadCommand implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if(args.length == 1)
        {
            switch (args[0].toLowerCase())
            {
                case "main":
                    try
                    {
                        Main.config.load("data/config.data");
                    }
                    catch (IOException | InvalidConfigurationException e)
                    {
                        e.printStackTrace();
                    }
                    break;

                case "adds":
                    AddsController.load();
                    break;

                case "schematics":
                    SchematicController.load();
                    break;
            }
        }
        return true;
    }
}
