package com.codecool.snake.entities.projectiles;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.Snake;
import com.codecool.snake.entities.snakes.SnakeHead;
import java.util.Random;

import javafx.geometry.Point2D;


public class Laser extends GameEntity implements Animatable, Interactable {

    private Point2D heading;
    private double direction;
    private int speed;

    public Laser(Snake snake) {
        Globals.getInstance().getImage("Laser");
        SnakeHead head = snake.getHead();

        setX(head.getX());
        setY(head.getY());

        this.speed = 5;
        direction = snake.getHead().getRotate();
        setRotate(direction);

    }

    @Override
    public void step() {

    }

    @Override
    public void apply(GameEntity entity) {

    }

    @Override
    public String getMessage() {
        return null;
    }
}
