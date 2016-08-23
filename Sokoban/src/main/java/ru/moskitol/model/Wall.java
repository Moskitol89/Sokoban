package ru.moskitol.model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Wall extends CollisionObject
{
    private BufferedImage image;
    public Wall(int x, int y)
    {
        super(x, y);
        try{
          image = ImageIO.read(Wall.class.getResource("/wall2.png").openStream());
        }catch(IOException e){}
    }

    @Override
    public void draw(Graphics graphics)
    {
        graphics.drawImage(image,getX(),getY(),getWidth(),getHeight(),null);
    }
}
