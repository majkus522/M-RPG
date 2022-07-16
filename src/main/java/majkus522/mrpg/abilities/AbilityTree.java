package majkus522.mrpg.abilities;

import majkus522.mrpg.Functions;
import majkus522.mrpg.level.LevelController;
import majkus522.mrpg.playerClass.ClassController;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AbilityTree implements InventoryHolder
{
    Inventory inventory;
    Player player;

    public AbilityTree(Player player)
    {
        this.player = player;
        inventory = Bukkit.createInventory(this, 54, "Umiejętności");
        ItemStack empty = Functions.emptySlot();
        for(int index = 0; index < inventory.getSize(); index++)
            inventory.setItem(index, empty);

        ItemStack book = new ItemStack(Material.BOOK, 1);
        ItemMeta meta = book.getItemMeta();
        meta.setLore(Arrays.asList(new String[] {"Twoje punkty umiejętnośći: " + AbilityController.getPoints(player)}));
        meta.setDisplayName(ChatColor.RESET + "Punkty umiejętności");
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addEnchant(Enchantment.DURABILITY, 1, true);
        book.setItemMeta(meta);
        inventory.setItem(8, book);

        inventory.setItem(45, createAbility("Punktacja I", new ArrayList<String>(0), 4, 10));
        inventory.setItem(46, createAbility("Punktacja II", new ArrayList<String>(0), 6, 20));
        inventory.setItem(47, createAbility("Punktacja III", new ArrayList<String>(0), 8, 30));


        switch (ClassController.getClass(player))
        {
            case Wojownik:
                inventory.setItem(0, createAbility("Siła I", new ArrayList<String>(0), 0));
                inventory.setItem(9, createAbility("Siła II", new ArrayList<String>(0), 1));
                inventory.setItem(18, createAbility("Siła III", new ArrayList<String>(0), 2));
                break;

            case Mag:
                inventory.setItem(0, createAbility("Mana I", new ArrayList<String>(0), 0));
                inventory.setItem(9, createAbility("Mana II", new ArrayList<String>(0), 1));
                inventory.setItem(18, createAbility("Mana III", new ArrayList<String>(0), 2));

                inventory.setItem(2, createAbility("Mana Regen I", new ArrayList<String>(0), 0));
                inventory.setItem(11, createAbility("Mana Regen II", new ArrayList<String>(0), 1));
                inventory.setItem(20, createAbility("Mana Regen III", new ArrayList<String>(0), 2));
                break;

            case Alchemik:
                inventory.setItem(0, createAbility("Podstawy Alchemii", new ArrayList<String>(0), 0));
                inventory.setItem(9, createAbility("Alchemia Zaawansowana", new ArrayList<String>(0), 4));
                inventory.setItem(18, createAbility("Alchemia Mistrzowska", new ArrayList<String>(0), 10));
                break;
        }
    }

    @Override
    public Inventory getInventory()
    {
        return inventory;
    }

    ItemStack createAbility(String name, List<String> description, int cost)
    {
        return createAbility(name, description, cost, 0);
    }

    ItemStack createAbility(String name, List<String> description, int cost, int level)
    {
        AbilityType type = getType(name, cost, level);
        Material material = Material.AIR;
        switch(type)
        {
            case learned:
                material = Material.EMERALD_BLOCK;
                name = Functions.colors(ChatColor.GREEN) + name;
                break;

            case allow:
                material = Material.GOLD_BLOCK;
                name = Functions.colors(ChatColor.YELLOW) + name;
                break;

            case block:
                material = Material.REDSTONE_BLOCK;
                name = Functions.colors(ChatColor.RED) + name;
                break;

            case error:
                material = Material.BEDROCK;
                break;
        }
        ItemStack item = new ItemStack(material, 1);
        ItemMeta meta = item.getItemMeta();
        if(type != AbilityType.learned)
        {
            if(level > 0)
                description.add(Functions.colors(ChatColor.RED) + "Wymagany poziom: " + ChatColor.GREEN + level);
            description.add(Functions.colors(ChatColor.GOLD) + "Koszt punktów: " + cost);
        }
        meta.setLore(description);
        meta.setDisplayName(name);
        if(type == AbilityType.learned)
        {
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            meta.addEnchant(Enchantment.DURABILITY, 1, true);
        }
        item.setItemMeta(meta);
        return item;
    }

    AbilityType getType(String name, int cost, int level)
    {
        switch(name)
        {
            case "Mana I":
            case "Mana Regen I":
            case "Siła I":
            case "Podstawy Alchemii":
            case "Punktacja I":
                return AbilityController.abilityLearned(player, name) ?
                        AbilityType.learned :
                        (AbilityController.getPoints(player) >= cost && LevelController.getLevel(player) >= level) ?
                                AbilityType.allow :
                                AbilityType.block;

            case "Mana II":
            case "Mana Regen II":
            case "Siła II":
            case "Punktacja II":
                return AbilityController.abilityLearned(player, name) ?
                        AbilityType.learned :
                        AbilityController.abilityLearned(player, name.replaceAll("II", "I")) ?
                                (AbilityController.getPoints(player) >= cost && LevelController.getLevel(player) >= level) ?
                                        AbilityType.allow :
                                        AbilityType.block :
                                AbilityType.block;

            case "Mana III":
            case "Mana Regen III":
            case "Siła III":
            case "Punktacja III":
                return AbilityController.abilityLearned(player, name) ?
                        AbilityType.learned :
                        AbilityController.abilityLearned(player, name.replaceAll("III", "II")) ?
                                (AbilityController.getPoints(player) >= cost && LevelController.getLevel(player) >= level) ?
                                        AbilityType.allow :
                                        AbilityType.block :
                                AbilityType.block;

            case "Alchemia Zaawansowana":
            case "Alchemia Mistrzowska":
                return AbilityController.abilityLearned(player, name) ?
                        AbilityType.learned :
                        AbilityController.abilityLearned(player, name.replaceAll("Alchemia Zaawansowana", "Podstawy Alchemii").replaceAll("Alchemia Mistrzowska", "Alchemia Zaawansowana")) ?
                                (AbilityController.getPoints(player) >= cost && LevelController.getLevel(player) >= level) ?
                                        AbilityType.allow :
                                        AbilityType.block :
                                AbilityType.block;
        }
        return AbilityType.error;
    }
}