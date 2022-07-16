package majkus522.mrpg.mobs;

import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;

public class CommandMob implements CommandExecutor
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
        LivingEntity mob;
        switch(args[0])
        {
            case "wildCow":
                mob = (LivingEntity) player.getWorld().spawnEntity(player.getLocation(), EntityType.COW);
                Cow cow = (Cow) mob;
                cow.setCustomName("§2Dzika krowa");
                cow.setCustomNameVisible(true);
                cow.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(50);
                cow.setHealth(50);
                break;

            case "wildSheep":
                mob = (LivingEntity) player.getWorld().spawnEntity(player.getLocation(), EntityType.SHEEP);
                Sheep sheep = (Sheep) mob;
                sheep.setCustomName("§2Dzika owca");
                sheep.setCustomNameVisible(true);
                sheep.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(50);
                sheep.setHealth(50);
                break;

            case "wolf":
                mob = (LivingEntity) player.getWorld().spawnEntity(player.getLocation(), EntityType.WOLF);
                Wolf wolf = (Wolf) mob;
                wolf.setCustomName("§4Wilk");
                wolf.setCustomNameVisible(true);
                wolf.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(100);
                wolf.setHealth(100);
                wolf.setAngry(true);
                break;

            case "mentor":
                mob = (LivingEntity) player.getWorld().spawnEntity(player.getLocation(), EntityType.VILLAGER);
                Villager mentor = (Villager)mob;
                mentor.setCustomName(ChatColor.GOLD + "Mentor");
                mentor.setAI(false);
                mentor.setCollidable(false);
                mentor.setInvulnerable(true);
                mentor.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(2048);
                mentor.setHealth(2048);
                break;

            case "kowal":
                mob = (LivingEntity) player.getWorld().spawnEntity(player.getLocation(), EntityType.VILLAGER);
                Villager kowal = (Villager)mob;
                kowal.setCustomName(ChatColor.GOLD + "Kowal");
                kowal.setAI(false);
                kowal.setCollidable(false);
                kowal.setInvulnerable(true);
                kowal.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(2048);
                kowal.setHealth(2048);
                break;
        }
        return true;
    }
}
