package model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import resources.MyBeautifulClass;

public class Player extends CollisionObject implements Movable
{
    private BufferedImage image;

    public Player(int x, int y)
    {
        super(x,y);
        try
        {
            image = ImageIO.read(MyBeautifulClass.class.getResourceAsStream("player.png"));
        }
        catch (IOException e)
        {
        }
    }

    @Override
    public void draw(Graphics graphics)
    {
        graphics.drawImage(image,getX(),getY(),getWidth(),getHeight(),null);
    }

    @Override
    public void move(int x, int y)
    {
        setX(getX() + x);
        setY(getY() + y);
    }
}
