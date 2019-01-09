package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.enemies.Enemy;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.powerups.SimplePowerUp;
import com.codecool.snake.entities.snakes.Snake;

import com.codecool.snake.entities.snakes.SnakeHead;
import java.util.List;

public class GameLoop {
    private Snake snake;
    private boolean running = false;

    public GameLoop(Snake snake) { this.snake = snake; }

    public void start() {
        running = true;
    }

    public void stop() {
        running = false;
    }

    public void step() {
        if(running) {
            snake.step();
            Globals.getInstance().healthCounter();



            for (GameEntity gameObject : Globals.getInstance().display.getObjectList()) {
                if (gameObject instanceof Animatable) {
                    ((Animatable) gameObject).step();
                }
            }
            checkCollisions();

            Globals.getInstance().healthCurrent = snake.getHealth();




        }

        Globals.getInstance().display.frameFinished();
    }

    private void checkCollisions() {
        List<GameEntity> gameObjs = Globals.getInstance().display.getObjectList();
        for (int idxToCheck = 0; idxToCheck < gameObjs.size(); ++idxToCheck) {
            GameEntity objToCheck = gameObjs.get(idxToCheck);
            if (objToCheck instanceof Interactable) {
                for (int otherObjIdx = idxToCheck + 1; otherObjIdx < gameObjs.size(); ++otherObjIdx) {
                    GameEntity otherObj = gameObjs.get(otherObjIdx);
                    if (otherObj instanceof Interactable){
                        if(objToCheck.getBoundsInParent().intersects(otherObj.getBoundsInParent())){
                            ((Interactable) objToCheck).apply(otherObj);
                            ((Interactable) otherObj).apply(objToCheck);
                            if((objToCheck instanceof SnakeHead && otherObj instanceof SimpleEnemy) | (objToCheck instanceof SimpleEnemy && otherObj instanceof SnakeHead)) {
                                new SimpleEnemy(snake);
                            } else if (objToCheck instanceof SimplePowerUp && otherObj instanceof SnakeHead || objToCheck instanceof SnakeHead && otherObj instanceof SimplePowerUp) {
                                new SimplePowerUp(snake);
                            }
                        }
                    }
                }
            }
        }
    }
}
