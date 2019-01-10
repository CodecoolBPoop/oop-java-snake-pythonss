package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.enemies.ChasingEnemy;
import com.codecool.snake.entities.enemies.Enemy;
import com.codecool.snake.entities.enemies.MoveInCircleEnemy;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.powerups.AmmoPowerUp;
import com.codecool.snake.entities.powerups.GoldenSnitch;
import com.codecool.snake.entities.powerups.HealingPowerUp;
import com.codecool.snake.entities.powerups.SimplePowerUp;
import com.codecool.snake.entities.projectiles.Laser;
import com.codecool.snake.entities.snakes.Snake;
import com.codecool.snake.entities.snakes.SnakeHead;
import com.codecool.snake.eventhandler.InputHandler;
import javafx.scene.input.KeyCode;


import java.util.List;
import java.util.Random;

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
            if (InputHandler.getInstance().isKeyPressed(KeyCode.UP) && snake.getAmmo() > 0 && snake.getWeaponCooldown() == 0) {
                new Laser(snake);
                snake.setWeaponCooldown(30);
                snake.setAmmo(snake.getAmmo() - 1);
                Globals.getInstance().ammoCounter();
                System.out.println("ammo: " + snake.getAmmo());
            }
            snake.step();
            Globals.getInstance().healthCounter();
            Globals.getInstance().healthCurrent = snake.getHealth();
            Globals.getInstance().ammoCurrent = snake.getAmmo();



            for (GameEntity gameObject : Globals.getInstance().display.getObjectList()) {
                if (gameObject instanceof Animatable) {
                    ((Animatable) gameObject).step();
                } else if (gameObject instanceof ChasingEnemy) {
                    ((ChasingEnemy) gameObject).step(snake);
                } else if (gameObject instanceof GoldenSnitch) {
                    ((GoldenSnitch) gameObject).step(snake);
                }
            }
            checkCollisions();






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
                            if((objToCheck instanceof SnakeHead && otherObj instanceof Enemy) | (objToCheck instanceof Enemy && otherObj instanceof SnakeHead) |
                            (objToCheck instanceof Laser && otherObj instanceof Enemy) | (objToCheck instanceof  Enemy && otherObj instanceof Laser)) {
                                if(objToCheck instanceof MoveInCircleEnemy | otherObj instanceof MoveInCircleEnemy) {
                                    Random rnd = new Random();
                                    double direction = rnd.nextDouble() * 360;
                                    new MoveInCircleEnemy(direction);
                                } else {
                                    Random rnd = new Random();
                                    int randNum = rnd.nextInt((100 - 1) + 1) + 1;
                                    if(randNum <= 50) {
                                        new ChasingEnemy();
                                    } else {
                                        new SimpleEnemy(snake);
                                    }
                                }
                            } else if (objToCheck instanceof SimplePowerUp && otherObj instanceof SnakeHead || objToCheck instanceof SnakeHead && otherObj instanceof SimplePowerUp) {
                                Globals.getInstance().globalScore += 1;
                                System.out.println(Globals.getInstance().globalScore);
                                int rand = (int)(Math.random()* 9 + 1);
                                if (rand > 4) {
                                    new SimplePowerUp(snake);
                                } else if (rand == 3 || rand == 4) {
                                    new GoldenSnitch(snake);
                                } else if (rand == 2) {
                                    new HealingPowerUp(snake);
                                } else if (rand == 1) {
                                    new AmmoPowerUp(snake);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
