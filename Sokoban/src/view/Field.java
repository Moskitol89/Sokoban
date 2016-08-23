package view;

import controller.EventListener;
import model.Direction;
import model.GameObject;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import resources.MyBeautifulClass;

public class Field extends JPanel
{
    private BufferedImage image;
    private View view;
    private EventListener eventListener;

    public Field(View view)
    {
        this.view = view;
        KeyHandler keyHandler = new KeyHandler();
        view.addKeyListener(keyHandler);
        view.setFocusable(true);
        try
        {
            image = ImageIO.read(MyBeautifulClass.class.getResourceAsStream("fon.jpg"));
        }
        catch (IOException e)
        {
        }
    }

    public void paint(Graphics g)
    {
//            g.setColor(Color.gray);
        g.drawImage(image,getX() + 1, getY() + 1, getWidth() - 1, getHeight() - 1, null);
//            g.fillRect(getX(),getY(),getWidth(),getHeight());
        for (GameObject gameObject : view.getGameObjects().getAll())
        {
            gameObject.draw(g);
        }

    }
    public void setEventListener(EventListener eventListener)
    {
        this.eventListener = eventListener;
    }
    public class KeyHandler extends KeyAdapter
    {
        @Override
        public void keyPressed(KeyEvent e)
        {
            switch (e.getKeyCode())
            {
                case KeyEvent.VK_LEFT : eventListener.move(Direction.LEFT);
                    break;
                case KeyEvent.VK_RIGHT : eventListener.move(Direction.RIGHT);
                    break;
                case KeyEvent.VK_UP : eventListener.move(Direction.UP);
                    break;
                case KeyEvent.VK_DOWN : eventListener.move(Direction.DOWN);
                    break;
                case KeyEvent.VK_R : eventListener.restart();
                    break;
            }
        }
    }
}
