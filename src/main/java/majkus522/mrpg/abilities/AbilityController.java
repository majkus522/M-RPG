package majkus522.mrpg.abilities;

import majkus522.mrpg.Functions;
import majkus522.mrpg.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AbilityController implements Listener
{
    public static Map<String, Integer> abilitiesMap = new HashMap<String, Integer>()
    {{
        put("Punktacja I", 0);
        put("Punktacja II", 1);
        put("Punktacja III", 2);

        //Wojownik
        put("Siła I", 3);
        put("Siła II", 4);
        put("Siła III", 5);

        //Strzelec

        //Paladyn

        //Mag
        put("Mana I", 3);
        put("Mana II", 4);
        put("Mana III", 5);
        put("Mana Regen I", 6);
        put("Mana Regen II", 7);
        put("Mana Regen III", 8);

        //Alchemik
        put("Podstawy Alchemii", 3);
        put("Alchemia Zaawansowana", 4);
        put("Alchemia Mistrzowska", 5);
    }};

    public static void setPoints(Player player, int input)
    {
        Main.config.set("AbilityPoints." + player.getUniqueId(), input);
    }

    public static int getPoints(Player player)
    {
        return Integer.parseInt(Main.config.getString("AbilityPoints." + player.getUniqueId()));
    }

    public static void addPoints(Player player, int input)
    {
        setPoints(player, getPoints(player) + input);
    }

    public static String getAbilities(Player player)
    {
        return (String)Main.config.get("Abilities." + player.getUniqueId());
    }

    public static void setAbilities(Player player, String abilities)
    {
        Main.config.set("Abilities." + player.getUniqueId(), abilities);
    }

    public static void learnAbility(Player player, String abilityName)
    {
        String abilities = getAbilities(player);
        int index = abilitiesMap.get(abilityName);
        char[] chars = abilities.toCharArray();
        chars[index] = 't';
        abilities = String.valueOf(chars);
        setAbilities(player, abilities);
    }

    public static boolean abilityLearned(Player player, String abilityName)
    {
        return getAbilities(player).toCharArray()[abilitiesMap.get(abilityName)] == 't';
    }

    @EventHandler
    public void itemTake(InventoryClickEvent event)
    {
        if(event.getInventory().getHolder() instanceof AbilityTree)
        {
            event.setCancelled(true);
            ItemStack item = event.getCurrentItem();
            Player player = (Player)event.getWhoClicked();
            if(item.getType() == Material.GOLD_BLOCK)
            {
                ItemMeta meta = item.getItemMeta();
                List<String> lore = meta.getLore();
                String[] line = lore.get(lore.size() - 1).split(" ");
                int cost = Integer.parseInt(line[line.length - 1]);
                if(cost <= getPoints(player))
                {
                    addPoints(player, -cost);
                    learnAbility(player, item.getItemMeta().getDisplayName().substring(2));
                    player.closeInventory();
                    player.openInventory(new AbilityTree(player).getInventory());
                }
                else
                    player.sendMessage(Functions.colors(ChatColor.RED, ChatColor.BOLD) + "Brak wystarczającej ilości punktór");
            }
        }
    }
}