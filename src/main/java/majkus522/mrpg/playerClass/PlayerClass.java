package majkus522.mrpg.playerClass;

public enum PlayerClass
{
    Wojownik, Mag, Strzelec, Zabójca, Paladyn, Poszukiwacz, Alchemik, Dummy;

    public static PlayerClass getClass(String input)
    {
        switch(input.toLowerCase())
        {
            case "wojownik":
                return Wojownik;

            case "mag":
                return Mag;

            case "strzelec":
                return Strzelec;

            case "zabójca":
                return Zabójca;

            case "paladyn":
                return Paladyn;

            case "poszukiwacz":
                return Poszukiwacz;

            case "alchemik":
                return Alchemik;

            case "dummy":
                return Dummy;
        }
        return null;
    }
}
