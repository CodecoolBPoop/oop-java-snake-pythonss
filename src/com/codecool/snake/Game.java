package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.powerups.SimplePowerUp;
import com.codecool.snake.entities.snakes.Snake;
import com.codecool.snake.eventhandler.InputHandler;

import com.sun.javafx.geom.Vec2d;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.util.List;


public class Game extends Pane {
    private Snake snake = null;
    private GameTimer gameTimer = new GameTimer();
    private Button healthButton;

    public Game() {
        Globals.getInstance().game = this;
        Globals.getInstance().display = new Display(this);
        Globals.getInstance().setupResources();

        init();
    }

    public void init() {
        spawnSnake();
        spawnEnemies(4);
        spawnPowerUps(4);

        GameLoop gameLoop = new GameLoop(snake);
        Globals.getInstance().setGameLoop(gameLoop);
        gameTimer.setup(gameLoop::step);
        gameTimer.play();
    }

    private void cleanup() {
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

    private void spawnEnemies(int numberOfEnemies) {
        for(int i = 0; i < numberOfEnemies; ++i) { new SimpleEnemy(snake); }
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



    public void addHealthBar() {
        healthButton = new Button("100");
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
