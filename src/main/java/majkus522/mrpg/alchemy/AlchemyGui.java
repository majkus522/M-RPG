package majkus522.mrpg.alchemy;

import majkus522.mrpg.Functions;
import majkus522.mrpg.skills.SkillController;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class AlchemyGui implements InventoryHolder
{
    Inventory inventory;

    public AlchemyGui(Player player)
    {
        inventory = Bukkit.createInventory(this, SkillController.haveSkill(player, "Zaawansowana alchemia") ? 54 : 45, "Alchemia");
        ItemStack empty = Functions.emptySlot();
        for(int index = 0; index < inventory.getSize(); index++)
            inventory.setItem(index, empty);
        inventory.setItem(13, new ItemStack(Material.AIR));

        ItemStack ingridient = new ItemStack(Material.GREEN_STAINED_GLASS_PANE, 1);
        ItemMeta meta = ingridient.getItemMeta();
        meta.setDisplayName(Functions.colors(ChatColor.GREEN) + "Potrzebna jest baza mikstury");
        ingridient.setItemMeta(meta);

        for(int index = 0; index < 7; index++)
            inventory.setItem(28 + index, ingridient);
        if(SkillController.haveSkill(player, "Zaawansowana alchemia"))
            for(int index = 0; index < 7; index++)
                inventory.setItem(37 + index, ingridient);
    }

    @Override
    public Inventory getInventory()
    {
        return inventory;
    }
}