package ru.moskitol.controller;

import ru.moskitol.model.Direction;
import ru.moskitol.model.GameObjects;
import ru.moskitol.model.Model;
import ru.moskitol.view.View;

public class Controller implements EventListener {
    private View view;
    private Model model;

    public Controller() {
        this.view = new View(this);
        this.model = new Model();
        view.init();
        model.restart();
        view.setEventListener(this);
        model.setEventListener(this);
    }

    public static void main(String[] args) {
        Controller controller = new Controller();
    }

    public void move(Direction direction) {
        model.move(direction);
        view.update();
    }
	

    public void restart() {
        model.restart();
        view.update();
    }
	

    public void startNextLevel() {
        model.startNextLevel();
        view.update();
    }


    public void levelCompleted(int level) {
        view.completed(level);
    }

    public GameObjects getGameObjects() {
        return model.getGameObjects();
    }
}