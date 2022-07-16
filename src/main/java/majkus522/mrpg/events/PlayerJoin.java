package majkus522.mrpg.events;

import majkus522.mrpg.Functions;
import majkus522.mrpg.ScoreboardController;
import majkus522.mrpg.abilities.AbilityController;
import majkus522.mrpg.clan.ClanController;
import majkus522.mrpg.enderchest.EnderchestController;
import majkus522.mrpg.level.LevelController;
import majkus522.mrpg.magic.ManaController;
import majkus522.mrpg.money.MoneyController;
import majkus522.mrpg.playerClass.ClassController;
import majkus522.mrpg.playerClass.PlayerClass;
import majkus522.mrpg.rank.Rank;
import majkus522.mrpg.rank.RankController;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PlayerJoin implements Listener
{
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event)
    {
        Player player = event.getPlayer();
        ClanController.clanChatEnabled.put(player, false);
        if(player.hasPlayedBefore() && ClassController.getClass(player) != null && ClassController.getClass(player) != PlayerClass.Dummy)
        {
            Functions.setPlayerName(player);
            ManaController.setPlayerMana(player, 10);
            ManaController.setPlayerMaxMana(player, 10);
        }
        else
        {
            player.getInventory().clear();
            RankController.setRank(player, Rank.Gracz);
            ClassController.setClass(player, PlayerClass.Dummy);
            Functions.teleport(player, "world", 1000, 11, 0, -90, 0);
            EnderchestController.enderchests.put(player.getUniqueId().toString(), new ItemStack[27]);
            LevelController.setLevel(player, 1);
            LevelController.setExp(player, 0);
            MoneyController.setMoney(player, 0);
            ManaController.setPlayerMana(player, 10);
            ManaController.setPlayerMaxMana(player, 10);
            AbilityController.setAbilities(player, "ffftfftff");
            AbilityController.setPoints(player, 2);

            ItemStack item = new ItemStack(Material.WRITTEN_BOOK, 1);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(ChatColor.YELLOW + "Księga wiedzy");
            meta.addEnchant(Enchantment.DURABILITY, 10, true);
            item.setItemMeta(meta);
            player.getInventory().setItem(4, item);
        }
        ScoreboardController.createScoreboard(player);
        event.setJoinMessage("");
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event)
    {
        ClanController.clanChatEnabled.remove(event.getPlayer());
    }
}