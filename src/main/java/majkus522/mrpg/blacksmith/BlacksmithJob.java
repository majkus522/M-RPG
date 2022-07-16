package majkus522.mrpg.blacksmith;

public enum BlacksmithJob
{
    Helmet, Chestplate, Leggings, Boots, Weapon, main;

    public static BlacksmithJob getJob(String input)
    {
        switch(input.toLowerCase())
        {
            case "helmet":
            case "hełm":
                return Helmet;

            case "chestplate":
            case "napierśnik":
                return Chestplate;

            case "leggings":
            case "spodnie":
                return Leggings;

            case "boots":
            case "buty":
                return Boots;

            case "weapon":
            case "broń":
                return Weapon;

            default:
                return null;
        }
    }
}