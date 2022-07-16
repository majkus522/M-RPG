package majkus522.mrpg.rank;

public enum Rank
{
    Gracz, Admin, VIP;

    public static Rank getRank(String input)
    {
        switch(input.toLowerCase())
        {
            case "gracz":
                return Gracz;

            case "admin":
                return Admin;

            case "vip":
                return VIP;
        }
        return null;
    }
}