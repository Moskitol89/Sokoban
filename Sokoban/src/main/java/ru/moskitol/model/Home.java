package ru.moskitol.model;

import java.awt.*;

public class Home extends GameObject
{
    public Home(int x, int y)
    {
        super(x, y, 2 ,2); 
    }

    @Override
    public void draw(Graphics graphics)
    {
        graphics.setColor(Color.red);
        graphics.drawOval(getX() + 9, getY() + 9, getWidth(), getHeight());
    }
}
