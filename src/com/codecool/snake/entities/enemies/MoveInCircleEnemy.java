package com.codecool.snake.entities.enemies;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.Snake;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;

import java.util.Random;

public class MoveInCircleEnemy extends Enemy implements Animatable, Interactable {
    private double angle;
    private double enemyX;
    private double enemyY;
    private double radius = Globals.WINDOW_HEIGHT / 2 - 100;
    private double origoY = Globals.WINDOW_HEIGHT / 2;
    private double origoX = Globals.WINDOW_WIDTH / 2;


    public MoveInCircleEnemy(double direction) {
        super(10);
        setImage(Globals.getInstance().getImage("InCircleEnemy"));

        angle = Math.toRadians(direction);

        enemyX = origoX + radius * Math.cos(angle);
        enemyY = origoY + radius * Math.sin(angle);

        setX(enemyX);

        setY(enemyY);
    }

    @Override
    public void step() {
        this.angle += 0.007;
        this.enemyX = origoX + radius * Math.cos(angle);
        this.enemyY = origoY + radius * Math.sin(angle);
        setX(enemyX);
        setY(enemyY);
    }

    @Override
    public void apply(GameEntity entity) {
        if(entity instanceof SnakeHead){
            System.out.println(getMessage());
            destroy();
        }
    }

    @Override
    public String getMessage() {
        return (getDamage() + " damage");
    }
}
