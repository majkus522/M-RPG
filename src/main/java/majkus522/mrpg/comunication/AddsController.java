package majkus522.mrpg.comunication;

import majkus522.mrpg.Functions;
import org.bukkit.Bukkit;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddsController
{
    static List<Add> adds = new ArrayList<Add>();
    static int addIndex = 0;

    public static void load()
    {
        addIndex = 0;
        for(int index = 0; index < new File("data/").list().length; index++)
        {
            try
            {
                File file = new File("data/add" + index + ".data");
                if(file.exists())
                {
                    FileReader fileReader = new FileReader(file);
                    List<String> code = new ArrayList<String>();
                    String line;
                    BufferedReader br = new BufferedReader(fileReader);
                    while((line = br.readLine()) != null)
                    {
                        code.add(line);
                    }
                    adds.add(new Add(code));
                    fileReader.close();
                }
                else
                    break;
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public static void display()
    {
        for(String line : adds.get(addIndex).getAdd())
            Bukkit.getServer().broadcastMessage(Functions.colors(line));
        addIndex++;
        if(addIndex == adds.size())
        {
            addIndex = 0;
        }
    }
}