package majkus522.mrpg.skills;

public class Skill
{
    public int skillPoints;
    public String name;
    public SkillRarity rarity;

    public Skill(String name, SkillRarity rarity)
    {
        this.name = name;
        this.rarity = rarity;
        skillPoints = 0;
    }

    public Skill(String name, SkillRarity rarity, int skillPoints)
    {
        this.name = name;
        this.rarity = rarity;
        this.skillPoints = skillPoints;
    }

    public boolean compare(Skill input)
    {
        return name == input.name && rarity == input.rarity && skillPoints == input.skillPoints;
    }
}