package majkus522.mrpg.events;

import majkus522.mrpg.Functions;
import majkus522.mrpg.items.ItemManager;
import majkus522.mrpg.level.LevelController;
import org.bukkit.ChatColor;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public class MobKilled  implements Listener
{
    Player player;

    @EventHandler
    public void onMobKilled(EntityDeathEvent event)
    {
        event.getDrops().clear();
        event.setDroppedExp(0);
        LivingEntity entity = event.getEntity();
        player = event.getEntity().getKiller();
        if(entity.getCustomName().equalsIgnoreCase("§2Dzika owca"))
        {
            ItemStack drop = ItemManager.wool;
            double random = Math.random() * 100;
            if(random < 50)
                drop.setAmount(1);
            else if(random < 70)
                drop.setAmount(2);
            else
                drop.setAmount(0);

            if(drop.getAmount() > 0)
                entity.getLocation().getWorld().dropItem(entity.getLocation(), drop);

            drop = ItemManager.meat;
            if(random < 70)
                drop.setAmount(1);
            else
                drop.setAmount(0);

            if(drop.getAmount() > 0)
                entity.getLocation().getWorld().dropItem(entity.getLocation(), drop);
            exp(3, 8);
        }
        else if(entity.getCustomName().equalsIgnoreCase("§2Dzika krowa"))
        {
            ItemStack drop = ItemManager.leatherCow;
            double random = Math.random() * 100;
            if(random < 60)
                drop.setAmount(1);
            else if(random < 70)
                drop.setAmount(2);
            else
                drop.setAmount(0);

            if(drop.getAmount() > 0)
                entity.getLocation().getWorld().dropItem(entity.getLocation(), drop);

            drop = ItemManager.meat;
            if(random < 70)
                drop.setAmount(1);
            else if(random < 80)
                drop.setAmount(2);
            else
                drop.setAmount(0);

            if(drop.getAmount() > 0)
                entity.getLocation().getWorld().dropItem(entity.getLocation(), drop);
            exp(4, 8);
        }
        else if(entity.getCustomName().equalsIgnoreCase("§4Wilk"))
        {
            ItemStack drop = ItemManager.leatherWolf;
            double random = Math.random() * 100;
            if(random < 60)
                drop.setAmount(1);
            else if(random < 65)
                drop.setAmount(2);
            else
                drop.setAmount(0);

            if(drop.getAmount() > 0)
                entity.getLocation().getWorld().dropItem(entity.getLocation(), drop);

            drop = ItemManager.meat;
            if(random < 60)
                drop.setAmount(1);
            else
                drop.setAmount(0);

            if(drop.getAmount() > 0)
                entity.getLocation().getWorld().dropItem(entity.getLocation(), drop);
            exp(6, 12);
        }
    }

    void exp(int min, int max)
    {
        int randomExp = Functions.getRandomNumber(min, max);
        player.sendMessage(Functions.colors(ChatColor.GREEN, ChatColor.BOLD) + "Otrzymałeś " + randomExp + " EXPa");
        LevelController.addExp(player, randomExp);
    }
}