package ru.moskitol.model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends CollisionObject implements Movable
{
    private BufferedImage image;

    public Player(int x, int y)
    {
        super(x,y);
        try
        {
            image = ImageIO.read(Player.class.getResource("/player.png").openStream());
        }
        catch (IOException e)
        {
        }
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
