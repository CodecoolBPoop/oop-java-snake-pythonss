package com.codecool.snake;

import com.codecool.snake.resources.Resources;
import javafx.scene.image.Image;

// class for holding all static stuff
public class Globals {
    private static Globals instance = null;

    public static final double WINDOW_WIDTH = 1000;
    public static final double WINDOW_HEIGHT = 700;

    public Display display;
    public Game game;

    private GameLoop gameLoop;
    private Resources resources;

    int healthCurrent = 100;
    int ammoCurrent = 0;
    int globalScore = 0;


    public void healthCounter() {
    game.updateHealth();
    }


    public void ammoCounter(){
        game.updateAmmo();
    }

    public static Globals getInstance() {
        if(instance == null) instance = new Globals();
        return instance;
    }

    public void setGameLoop(GameLoop gameLoop) {
        this.gameLoop = gameLoop;
    }

    public void setupResources() {
        resources = new Resources();
        resources.addImage("SnakeHead", new Image("snakehead.png"));
        resources.addImage("SnakeBody", new Image("snakebody.png"));
        resources.addImage("SimpleEnemy", new Image("croc.png"));
        resources.addImage("HealthPickup", new Image("capybara.png"));
        resources.addImage("AmmoPickup", new Image("frog.png"));
        resources.addImage("SimplePickup", new Image("eggs.png"));
        resources.addImage("GoldenSnitch", new Image("mouse.png"));
        resources.addImage("Laser", new Image("lazzor.png"));
        resources.addImage("ChasingEnemy", new Image("eagle.png"));
        resources.addImage("InCircleEnemy", new Image("tiger.png"));
    }

    public Image getImage(String name) { return resources.getImage(name); }

    public int getGlobalScore() {
        return globalScore;
    }

    public void startGame() { gameLoop.start(); }

    public void stopGame() { gameLoop.stop(); }

    private Globals() {
        // singleton needs the class to have private constructor
    }
}
