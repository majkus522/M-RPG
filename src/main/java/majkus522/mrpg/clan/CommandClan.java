package majkus522.mrpg.clan;

import majkus522.mrpg.Functions;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandClan implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        System.out.println(ClanController.clans.size());
        if (!(sender instanceof Player))
        {
            sender.sendMessage("Te komenda może być wykonana tylko przez gracza");
            return true;
        }
        Player player = (Player) sender;
        if(args.length == 0)
        {
            player.sendMessage(ChatColor.RED + "====" + ChatColor.GREEN + "Komendy klanowe" + ChatColor.RED + "====");
            player.sendMessage(ChatColor.RED + "/klan stwórz [nazwa]" + ChatColor.GREEN + " - Stworzenie klanu o określonej nazwie");
            player.sendMessage(ChatColor.RED + "/klan usuń" + ChatColor.GREEN + " - Usunięcie klanu (tylko leader może użyć tej komendy)");
            player.sendMessage(ChatColor.RED + "/klan członkowie [nazwa]" + ChatColor.GREEN + " - Sprawdzenie członków danego klanu / bez wprowadzenia nazwy wyświetli członków twojego klanu");
            player.sendMessage(ChatColor.RED + "/klan zaproś [gracz]" + ChatColor.GREEN + " - Zaproszenie gracza do klanu (tylko leader może użyć tej komendy)");
            player.sendMessage(ChatColor.RED + "/klan wyrzuć [gracz]" + ChatColor.GREEN + " - Wyrzucenie gracza do klanu (tylko leader może użyć tej komendy)");
            player.sendMessage(ChatColor.RED + "/klan chat" + ChatColor.GREEN + " - Przełącz na chat klanowy/serwerowy");
            player.sendMessage(ChatColor.RED + "/klan lista" + ChatColor.GREEN + " - Lista klanów");
            player.sendMessage(ChatColor.RED + "====" + ChatColor.GREEN + "Komendy klanowe" + ChatColor.RED + "====");
            return true;
        }

        if(args[0].equals("stwórz"))
        {
            if(ClanController.clanExist(args[1]))
            {
                player.sendMessage(Functions.getCommandExecutor("klan") + ChatColor.RED + "Taki klan już istnieje");
            }
            else
            {
                Clan clan = new Clan(player, args[1]);
                ClanController.clans.add(clan);
                Functions.setPlayerName(player);
                player.sendMessage(Functions.getCommandExecutor("klan") + ChatColor.GREEN + "Klan " + ChatColor.WHITE + args[1] + ChatColor.GREEN + " został utworzony");
            }

        }
        else if(args[0].equals("usuń"))
        {
            Clan clan = ClanController.getPlayerClan(player);
            if(player.equals(clan.leader))
            {
                ClanController.clans.remove(clan);
                player.sendMessage(Functions.getCommandExecutor("klan") + ChatColor.GREEN + "Klan został usunięty");
            }
            else
                player.sendMessage(Functions.getCommandExecutor("klan") + ChatColor.RED + "Tylko leader klanu może go usunąć");
        }
        else if(args[0].equals("członkowie"))
        {
            Clan clan;
            if(args.length >= 2)
            {
                if(ClanController.clanExist(args[1]))
                    clan = ClanController.getClan(args[1]);
                else
                {
                    player.sendMessage(Functions.getCommandExecutor("klan") + ChatColor.RED + "Klan nie istnieje");
                    return true;
                }
            }
            else
            {
                if(ClanController.getPlayerClan(player) != null)
                    clan = ClanController.getPlayerClan(player);
                else
                {
                    player.sendMessage(Functions.getCommandExecutor("klan") + ChatColor.RED + "Nie jesteś członkiem żadnego klan");
                    return true;
                }
            }
            player.sendMessage(Functions.getCommandExecutor("klan") + ChatColor.GREEN + "Członkowie klanu " + ChatColor.WHITE + clan.name + ChatColor.GREEN + ":");
            player.sendMessage(ChatColor.RED + "Leader " + ChatColor.GREEN + clan.leader);
            for(String member : clan.members)
            {
                player.sendMessage(ChatColor.GREEN + member);
            }
        }
        else if(args[0] == "zaproś")
        {

        }
        else if(args[0] == "wyrzuć")
        {

        }
        else if(args[0].equals("chat"))
        {
            if(ClanController.getPlayerClan(player) != null)
            {
                boolean value = !ClanController.clanChatEnabled.get(player);
                ClanController.clanChatEnabled.remove(player);
                ClanController.clanChatEnabled.put(player, value);
                String chat = "";
                if(value)
                    chat = "klanowy";
                else
                    chat = "publiczny";
                player.sendMessage(Functions.getCommandExecutor("klan") + ChatColor.GREEN + "Przełączono na chat " + chat);
            }
            else
            {
                player.sendMessage(Functions.getCommandExecutor("klan") + ChatColor.RED + "Nie jesteś członkiem klanu");
            }
        }
        else if(args[0].equals("lista"))
        {
            if(ClanController.clans.isEmpty())
                player.sendMessage(Functions.getCommandExecutor("klan") + ChatColor.RED + "Żaden klan nie istnieje");
            else
            {
                player.sendMessage(Functions.getCommandExecutor("klan") + ChatColor.GREEN + "Lista klanów:");
                for(Clan clan : ClanController.clans)
                    player.sendMessage(ChatColor.WHITE + " - " + clan.name);
            }
        }
        else
        {
            player.sendMessage(Functions.getCommandExecutor("klan") + ChatColor.RED + "Nieznana komenda");
        }
        return true;
    }
}
