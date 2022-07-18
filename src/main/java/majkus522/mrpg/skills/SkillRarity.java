package majkus522.mrpg.skills;

public enum SkillRarity
{
    common, extra, unique, ultimate;

    public String toString()
    {
        switch (this)
        {
            case common:
                return "Common";

            case extra:
                return "Extra";

            case unique:
                return "Unique";

            case ultimate:
                return "Ultimate";
        }
        return "";
    }
}