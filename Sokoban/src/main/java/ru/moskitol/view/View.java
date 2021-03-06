package ru.moskitol.view;

import ru.moskitol.controller.Controller;
import ru.moskitol.controller.EventListener;
import ru.moskitol.model.GameObjects;
import javax.swing.*;

public class View extends JFrame {
    private Controller controller;
    private static Field field;
    public static int level;
    static {
            try
            {
                do {
                    level = Integer.parseInt(JOptionPane.showInputDialog(field, "Выберите уровень (1 - 60) \n" +
                            " Управление стрелочками, R - перезапуск уровня. "));
                }while (level < 1 || level > 60);
            }
            catch (NumberFormatException e)
            {
                JOptionPane.showMessageDialog(field,"Вы не справились, получайте 60");
            }
    }

    public View(Controller controller) {
        this.controller = controller;
    }

    public void init() {
        field = new Field(this);
        add(field);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setTitle("Сокобан");
        setVisible(true);
    }
    public void setEventListener(EventListener eventListener)
    {
      field.setEventListener(eventListener);
    }
    public void update()
    {
        field.repaint();
    }
    public GameObjects getGameObjects()
    {
        return controller.getGameObjects();
    }
    public void completed(int level){
        update();
        JOptionPane.showMessageDialog(field,"Уровень " + level + " пройден.");
        controller.startNextLevel();
    }
}
