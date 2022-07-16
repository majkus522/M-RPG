package majkus522.mrpg.clan;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Clan
{
    public String leader;
    public List<String> members;
    public String name;
    public int money;

    public Clan(Player creator, String input)
    {
        leader = creator.getName();
        name = input;
        money = 0;
        members = new ArrayList<String>(0);
    }

    public Clan(String name, String input)
    {
        System.out.println("clan created");
        members = new ArrayList<String>(0);
        this.name = name;
        String[] split = input.split(";");
        leader = split[0];
        for(int index = 1; index < split.length - 1; index++)
        {
            members.add(split[index]);
        }
        money = Integer.parseInt(split[split.length - 1]);
    }

    @Override
    public String toString()
    {
        String result = "";
        result += leader.toString() + ";";
        for(String member : members)
        {
            result += member + ";";
        }
        result += money;
        return result;
    }
}