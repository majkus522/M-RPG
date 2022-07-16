package majkus522.mrpg.items;

import majkus522.mrpg.items.expos.Expo0Items;
import org.bukkit.inventory.ItemStack;

public class ItemManager
{
    //Expo0
    public static ItemStack wool;
    public static ItemStack leatherCow;
    public static ItemStack leatherWolf;
    public static ItemStack meat;

    //Tools
    public static ItemStack lumberAxe;

    //Herbs
    public static ItemStack mniszek;
    public static ItemStack rumianek;

    public static void init()
    {
        Herbs.init();
        Tools.init();
        Expo0Items.init();
    }
}