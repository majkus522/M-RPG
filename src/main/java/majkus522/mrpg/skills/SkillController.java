package majkus522.mrpg.skills;

import majkus522.mrpg.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SkillController implements Listener
{
    public static List<Skill> skills = Arrays.asList(new Skill[] {
        new Skill("Regeneracja", SkillRarity.common, 3),
        new Skill("Podstawy alchemii", SkillRarity.common),
        new Skill("Zaawansowana alchemia", SkillRarity.common, 10)
    });

    public static void setSkillPoints(Player player, int input)
    {
        Main.config.set("SkillPoints." + player.getUniqueId(), input);
    }

    public static int getSkillPoints(Player player)
    {
        return Integer.parseInt(Main.config.getString("SkillPoints." + player.getUniqueId()));
    }

    public static void addSkillPoints(Player player, int input)
    {
        setSkillPoints(player, input + getSkillPoints(player));
    }

    public static boolean haveSkillPoints(Player player, int input)
    {
        return getSkillPoints(player) >= input;
    }

    public static void learSkill(Player player, String name)
    {
        List<Skill> playerSkills = getSkills(player);
        playerSkills.add(getSkill(name));
        String value = "";
        for(Skill skill : playerSkills)
            value += ";" + skills.indexOf(skill);
        Main.config.set("Skills." + player.getUniqueId(), value);
    }

    public static Skill getSkill(String name)
    {
        for(Skill skill : skills)
            if(skill.name.toLowerCase() == name.toLowerCase())
                return skill;
        return null;
    }

    public static List<Skill> getSkills(Player player)
    {
        List<Skill> haved =  new ArrayList<>(0);
        String[] split = Main.config.getString("Skills." + player.getUniqueId()).split(";");
        for(int index = 1; index < split.length; index++)
            haved.add(skills.get(Integer.parseInt(split[index])));
        return haved;
    }

    public static boolean haveSkill(Player player, String name)
    {
        List<Skill> playerSkills = getSkills(player);
        return playerSkills.contains(getSkill(name));
    }

    public static boolean haveSkill(Player player, SkillRarity rarity)
    {
        for(Skill skill : getSkills(player))
            if(skill.rarity == rarity)
                return true;
        return false;
    }

    @EventHandler
    public void itemTake(InventoryClickEvent event)
    {
        if(event.getClickedInventory() == null)
            return;
        if(event.getClickedInventory().getHolder() instanceof SkillGui)
        {
            Player player = (Player)event.getWhoClicked();
            event.setCancelled(true);
            if(event.getClickedInventory().getSize() == 27)
            {
                if(event.getCurrentItem().getType() != Material.GRAY_STAINED_GLASS_PANE)
                    player.closeInventory();
                switch(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()))
                {
                    case "Common":
                        player.openInventory(new SkillGui(player, SkillRarity.common).getInventory());
                        break;

                    case "Extra":
                        player.openInventory(new SkillGui(player, SkillRarity.extra).getInventory());
                        break;

                    case "Unique":
                        player.openInventory(new SkillGui(player, SkillRarity.unique).getInventory());
                        break;

                    case "Ultimate":
                        player.openInventory(new SkillGui(player, SkillRarity.ultimate).getInventory());
                        break;
                }
            }
        }
    }
}