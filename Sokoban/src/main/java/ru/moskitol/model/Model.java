package ru.moskitol.model;

import ru.moskitol.controller.EventListener;
import ru.moskitol.view.View;

public class Model
{
    private int currentLevel = View.level;
    private LevelLoader levelLoader;
    private EventListener eventListener;
    private GameObjects gameObjects;
    public static final int FIELD_SELL_SIZE = 20;

    public Model() {
        this.levelLoader = new LevelLoader();
        gameObjects = levelLoader.getLevel(currentLevel);
    }
    public void setEventListener(EventListener eventListener)
    {
        this.eventListener = eventListener;
       
    }
    public GameObjects getGameObjects()
    {
       return gameObjects;
    }
    public void restartLevel(int level)
    {
        gameObjects =  levelLoader.getLevel(level);
    }
    public void restart()
    {
        restartLevel(currentLevel);
    }
    public void startNextLevel()
    {
        restartLevel(++currentLevel);
    }
    public void move(Direction direction){
        Player player = gameObjects.getPlayer();
        if(checkWallCollision(player,direction))
            return;
        if(checkBoxCollision(direction))
            return;
        switch (direction){
            case UP : player.move(0, -FIELD_SELL_SIZE);
                break;
            case DOWN : player.move(0, +FIELD_SELL_SIZE);
                break;
            case RIGHT: player.move(+FIELD_SELL_SIZE,0);
                break;
            case LEFT: player.move(-FIELD_SELL_SIZE, 0);
        }
        checkCompletion();
    }
    public boolean checkWallCollision(CollisionObject gameObject, Direction direction)
    {
        for(Wall wall : gameObjects.getWalls())
            if(gameObject.isCollision(wall,direction))
                return true;
        return false;
    }
    public boolean checkBoxCollision(Direction direction){
        Player player = gameObjects.getPlayer();
        for(Box box : gameObjects.getBoxes())
        {
            if(player.isCollision(box,direction))
            {
                if(checkWallCollision(box, direction))
                    return true;
                for(Box box1 : gameObjects.getBoxes())
                {
                    if(box != box1){
                        if(box.isCollision(box1,direction))
                            return true;
                    }
                }
                switch (direction){
                    case LEFT: box.move(-FIELD_SELL_SIZE,0);
                        break;
                    case RIGHT: box.move(+FIELD_SELL_SIZE,0);
                        break;
                    case UP: box.move(0, -FIELD_SELL_SIZE);
                        break;
                    case DOWN: box.move(0,+FIELD_SELL_SIZE);
                        break;
                }
            }
        }
        return false;
    }
    public void checkCompletion(){
        int count = 0;
        for(Box box : gameObjects.getBoxes())
        {
            for (Home home : gameObjects.getHomes())
            {
                if(box.getX() == home.getX() && box.getY() == home.getY())
                    count++;
            }
        }
        if(count == gameObjects.getHomes().size())
            eventListener.levelCompleted(currentLevel);
    }
}
