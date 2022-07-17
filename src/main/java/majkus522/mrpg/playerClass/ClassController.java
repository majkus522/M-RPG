package majkus522.mrpg.playerClass;

import majkus522.mrpg.Functions;
import majkus522.mrpg.Main;
import majkus522.mrpg.ScoreboardController;
import majkus522.mrpg.events.custom.ItemRightClickEvent;
import majkus522.mrpg.events.custom.PlatePressedEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class ClassController implements Listener
{
    public static void setClass(Player player, PlayerClass playerClass)
    {
        Main.config.set("Class." + player.getUniqueId(), playerClass.toString());
        Functions.setPlayerName(player);
        ScoreboardController.createScoreboard(player);
    }

    public static PlayerClass getClass(Player player)
    {
        String value = Main.config.getString("Class." + player.getUniqueId());
        return PlayerClass.getClass(value);
    }

    public static String getPlayerText(Player player)
    {
        switch(getClass(player))
        {
            case Wojownik:
                return (ChatColor.RED + "[Woj]");

            case Mag:
                return (ChatColor.AQUA + "[Mag]");

            case Paladyn:
                return (ChatColor.YELLOW + "[Pal]");

            case Strzelec:
                return (ChatColor.GREEN + "[Str]");

            case Zabójca:
                return (ChatColor.BLACK + "[Zab]");

            case Poszukiwacz:
                return (ChatColor.WHITE + "[Pos]");

            case Alchemik:
                return (ChatColor.BLUE + "[Alch]");
        }
        return "";
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent event)
    {
        if(event.getDamager() instanceof Player)
        {
            Player player = (Player)event.getDamager();
            if(ClassController.getClass(player) == PlayerClass.Poszukiwacz)
                event.setDamage(event.getDamage() * 0.75);
        }
    }

    @EventHandler
    public void onBookClick(ItemRightClickEvent event)
    {
        Player player = event.getPlayer();
        if(player.getInventory().getItemInMainHand().getType() != Material.WRITTEN_BOOK)
            return;

        if(player.getLocation().distance(new Location(Bukkit.getWorld("world"), 1007, 11, -5)) < 5)
        {
            player.sendMessage("Wojownicy cechują się dużymi obrażeniami ale niską obroną");
        }
        else if(player.getLocation().distance(new Location(Bukkit.getWorld("world"), 1007, 11, 5)) < 5)
        {
            player.sendMessage("Strzelcy potrafią używać broni dystansowej takiej jak łuki czy kusze");
        }
        else if(player.getLocation().distance(new Location(Bukkit.getWorld("world"), 1014, 11, -5)) < 5)
        {
            player.sendMessage("Madzy potrafią władać magią i użyć jej do zniszczenia przeciwnika");
        }
        else if(player.getLocation().distance(new Location(Bukkit.getWorld("world"), 1014, 11, 5)) < 5)
        {
            player.sendMessage("Paladyni cechują się dużą obroną ale niskimi obrażeniami");
        }
        else if(player.getLocation().distance(new Location(Bukkit.getWorld("world"), 1021, 11, -5)) < 5)
        {
            player.sendMessage("Poszukiwacze przygód potrafią używać każdego wyposarzenia bez względu dla kodo jest ono przystosowane, ale brak widzy w konkretnej dziedzinie zmniejsza ich obrażenia");
        }
        else if(player.getLocation().distance(new Location(Bukkit.getWorld("world"), 1021, 11, 5)) < 5)
        {
            player.sendMessage("Zabójcy potrafią atakować z ukrycia co zwiększa ich obrażenia");
        }
    }

    @EventHandler
    public void onPlatePressed(PlatePressedEvent event)
    {
        Player player = event.getPlayer();
        if(event.getPlate().getType() == Material.WARPED_PRESSURE_PLATE)
        {
            if(player.getLocation().distance(new Location(Bukkit.getWorld("world"), 1007, 11, -5)) < 3)
            {
                ClassController.setClass(player, PlayerClass.Wojownik);
            }
            else if(player.getLocation().distance(new Location(Bukkit.getWorld("world"), 1007, 11, 5)) < 3)
            {
                ClassController.setClass(player, PlayerClass.Strzelec);
            }
            else if(player.getLocation().distance(new Location(Bukkit.getWorld("world"), 1014, 11, -5)) < 3)
            {
                ClassController.setClass(player, PlayerClass.Mag);
            }
            else if(player.getLocation().distance(new Location(Bukkit.getWorld("world"), 1014, 11, 5)) < 3)
            {
                ClassController.setClass(player, PlayerClass.Paladyn);
            }
            else if(player.getLocation().distance(new Location(Bukkit.getWorld("world"), 1021, 11, -5)) < 3)
            {
                ClassController.setClass(player, PlayerClass.Poszukiwacz);
            }
            else if(player.getLocation().distance(new Location(Bukkit.getWorld("world"), 1021, 11, 5)) < 3)
            {
                ClassController.setClass(player, PlayerClass.Zabójca);
            }
        }
    }
}