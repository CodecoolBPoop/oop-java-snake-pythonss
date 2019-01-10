package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.enemies.ChasingEnemy;
import com.codecool.snake.entities.enemies.MoveInCircleEnemy;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.powerups.HealingPowerUp;
import com.codecool.snake.entities.powerups.SimplePowerUp;
import com.codecool.snake.entities.snakes.Snake;
import com.codecool.snake.eventhandler.InputHandler;

import com.sun.javafx.geom.Vec2d;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.util.List;
import java.util.Optional;


public class Game extends Pane {
    private Snake snake = null;
    private GameTimer gameTimer = new GameTimer();
    private Text healthButton;
    private Text ammoBar;

    public Game() {
        Globals.getInstance().game = this;
        Globals.getInstance().display = new Display(this);
        Globals.getInstance().setupResources();

        init();
    }

    public void init() {
        spawnSnake();
        spawnEnemies(3, 1, 6);
        spawnPowerUps(4);

        GameLoop gameLoop = new GameLoop(snake);
        Globals.getInstance().setGameLoop(gameLoop);
        gameTimer.setup(gameLoop::step);
        gameTimer.play();
    }

    private void cleanup() {
        Globals.getInstance().globalScore = 0;
        Globals.getInstance().ammoCurrent = 0;
        Globals.getInstance().healthCurrent = 100;
        List<GameEntity> gameObjs = Globals.getInstance().display.getObjectList();
        for (GameEntity entity: gameObjs) {
            entity.destroy();
        }
        snake = null;
    }

    public void start() {
        setupInputHandling();
        Globals.getInstance().startGame();
    }

    private void spawnSnake() {
        snake = new Snake(new Vec2d(500, 500));
    }

    private void spawnEnemies(int numberOfSimpleEnemies, int numberOfChasingEnemy, int numberOfMoveInCircleEnemy) {
        double direction = 0;
        for(int i = 0; i < numberOfSimpleEnemies; ++i) { new SimpleEnemy(snake); }
        for(int i = 0; i < numberOfChasingEnemy; ++i) { new ChasingEnemy(); }
        for(int i = 0; i < numberOfMoveInCircleEnemy; ++i) {
            new MoveInCircleEnemy(direction);
            direction += 60;
        }
    }

    private void spawnPowerUps(int numberOfPowerUps) {
        for(int i = 0; i < numberOfPowerUps; ++i) new SimplePowerUp(snake);
    }

    private void setupInputHandling() {
        Scene scene = getScene();
        scene.setOnKeyPressed(event -> InputHandler.getInstance().setKeyPressed(event.getCode()));
        scene.setOnKeyReleased(event -> InputHandler.getInstance().setKeyReleased(event.getCode()));
    }

    public void addRestartButton() {
        Button restartButton = new Button("Restart");
        HBox buttonBar = new HBox();
        restartButton.setOnAction(actionEvent -> restartGame());
        buttonBar.getChildren().add(restartButton);
        getChildren().add(buttonBar);
    }
    public void updateHealth() {
        int currentHealth = Globals.getInstance().healthCurrent;
        String currentHealthString = Integer.toString(currentHealth);
        healthButton.setText(currentHealthString);

    }

    public void updateAmmo(){
        int currentAmmo = snake.getAmmo();
        String currentAmmoString = Integer.toString(currentAmmo);
        ammoBar.setText(currentAmmoString);

    }

    public void addAmmoBar(){
        ammoBar = new Text("0");
        HBox ammo = new HBox();
        ammo.getChildren().add(ammoBar);
        getChildren().add(ammo);
        ammoBar.setTranslateX(70);
        ammoBar.setTranslateY(15);
    }



    public void addHealthBar() {
        healthButton = new Text("100");
        HBox healthBar = new HBox();
        healthBar.getChildren().add(healthButton);
        getChildren().add(healthBar);
        healthButton.setTranslateX(70);
    }

    private void restartGame() {
        Globals.getInstance().stopGame();
        cleanup();
        init();
        Globals.getInstance().startGame();
    }

}
