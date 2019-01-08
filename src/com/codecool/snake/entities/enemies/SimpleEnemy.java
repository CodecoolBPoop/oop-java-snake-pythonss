package com.codecool.snake.entities.enemies;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.Snake;
import com.codecool.snake.entities.snakes.SnakeHead;
import java.util.Random;

import javafx.geometry.Point2D;



public class SimpleEnemy extends Enemy implements Animatable, Interactable {

    private Point2D heading;
    private static Random rnd = new Random();
    int speed = 1;
    double direction = rnd.nextDouble() * 360;


    public SimpleEnemy(Snake snake) {
        super(10);
        SnakeHead head= snake.getHead();
        double headX = head.getX();
        double headY = head.getY();
        setImage(Globals.getInstance().getImage("SimpleEnemy"));

        double randX = rnd.nextDouble() * Globals.WINDOW_WIDTH;
        double randY = rnd.nextDouble() * Globals.WINDOW_HEIGHT;

        double enemyX = Utils.getSpawnedEntityX(headX, randX);
        setX(enemyX);

        double enemyY = Utils.getSpawnedEntityY(headY, randY);
        setY(enemyY);

        setRotate(direction);

        heading = Utils.directionToVector(direction, speed);
    }

    @Override
    public void step() {
        if (isOutOfBounds()) {
            heading = Utils.directionToVector(direction, -speed);
        }
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
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
