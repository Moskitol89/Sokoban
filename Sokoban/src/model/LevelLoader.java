package model;

import java.io.*;
import java.util.HashSet;
import java.util.Set;
import resources.MyBeautifulClass;

public class LevelLoader
{	
    public LevelLoader()
    {  
    }        
 
    public GameObjects getLevel(int level)
    {
        int currentLvl = (level % 60 == 0) ? 60 : level % 60;
        int x0 = Model.FIELD_SELL_SIZE / 2 ;
        int y0 = Model.FIELD_SELL_SIZE / 2 ;

        Set<Home> homes = new HashSet<>();
        Set<Box> boxes = new HashSet<>();
        Set<Wall> walls = new HashSet<>();
        Player player = null;
        GameObjects gameObjects;

        String allFromFileWithLevels = "";

        String splittedAllFromFile[];

        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(MyBeautifulClass.class.getResourceAsStream("levels.txt"))))
        {
            String line;
            while ((line = bufferedReader.readLine()) != null)
            {
                allFromFileWithLevels += line + "\n";
            }
        }  catch (IOException e){}
            splittedAllFromFile = allFromFileWithLevels.split("\\*{37}");
            for(int i = 0 ; i < splittedAllFromFile.length; i++)
            {
                if(splittedAllFromFile[i].contains("Length: "))
                    splittedAllFromFile[i] = splittedAllFromFile[i].substring(splittedAllFromFile[i].indexOf("Length: ") + 12);
            }
            if(splittedAllFromFile.length == 1)System.out.println(splittedAllFromFile[0]);
            char[] charsFromCurrentLvl = splittedAllFromFile[currentLvl].toCharArray();
            
           
           label : for(char c : charsFromCurrentLvl)
            {
                switch (c)
                {
                    case 'X' : walls.add(new Wall(x0, y0));
                        break;
                    case '*' : boxes.add(new Box(x0, y0));
                        break;
                    case '.' : homes.add( new Home(x0 , y0 ));
                        break;
                    case '&' : boxes.add(new Box(x0 , y0 ));
                        homes.add(new Home(x0 , y0 ));
                        break;
                    case '@' : player = new Player(x0, y0);
                        break;
                    case '\n' : y0 += Model.FIELD_SELL_SIZE ;
                        x0 = Model.FIELD_SELL_SIZE / 2;
                        continue label;
                    default:break;
                }
                x0 += Model.FIELD_SELL_SIZE;
            }
         gameObjects = new GameObjects(walls,boxes,homes,player);
        return gameObjects;
    }
}
