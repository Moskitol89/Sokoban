package ru.moskitol.model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class Box extends CollisionObject implements Movable
{
    private BufferedImage image;
    public Box(int x, int y)
    {
        super(x,y);
        try
        {
            image = ImageIO.read(Box.class.getResource("/box.png").openStream());
        }catch (IOException e){}
    }

    public void draw(Graphics graphics)
    {

        graphics.drawImage(image,getX(),getY(),getWidth(),getHeight(),null);
    }

    public void move(int x, int y)
    {
        setX(getX() + x);
        setY(getY() + y);
    }
}
