package model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import resources.MyBeautifulClass;

public class Wall extends CollisionObject
{
    private BufferedImage image;
    public Wall(int x, int y)
    {
        super(x, y);
        try{
          image = ImageIO.read(MyBeautifulClass.class.getResourceAsStream("wall2.png"));
        }catch(IOException e){}
    }

    @Override
    public void draw(Graphics graphics)
    {
        graphics.drawImage(image,getX(),getY(),getWidth(),getHeight(),null);
    }
}
