package majkus522.mrpg.comunication;

import java.util.List;

public class Add
{
    String[] lines;

    public Add(String[] input)
    {
        lines = input;
    }

    public Add(List<String> input)
    {
        lines = input.toArray(new String[0]);
    }

    public String[] getAdd()
    {
        return lines;
    }
}