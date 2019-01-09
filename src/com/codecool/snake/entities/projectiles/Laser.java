package com.codecool.snake.entities.projectiles;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.enemies.Enemy;
import com.codecool.snake.entities.snakes.Snake;
import com.codecool.snake.entities.snakes.SnakeHead;
import java.util.Random;
import com.codecool.snake.eventhandler.InputHandler;

import com.sun.javafx.geom.Vec2d;
import javafx.scene.input.KeyCode;

import javafx.geometry.Point2D;


public class Laser extends GameEntity implements Animatable, Interactable {

    private double direction;
    private float speed;

    public Laser(Snake snake) {
        System.out.println("new lazor");
        setImage(Globals.getInstance().getImage("Laser"));
        SnakeHead head = snake.getHead();

        setX(head.getX());
        setY(head.getY());

        speed = 10;
        direction = snake.getHead().getRotate();
        setRotate(direction);

    }

    @Override
    public void step() {

        if (isOutOfBounds()) destroy();

        Point2D heading = Utils.directionToVector(direction, speed);
        //System.out.println(getY());
        //System.out.println(getX());
        setY(getY() + heading.getY());
        setX(getX() + heading.getX());

    }

    @Override
    public void apply(GameEntity entity) {
        if (entity instanceof Enemy) {
            entity.destroy();
            destroy();
        }
    }

    @Override
    public String getMessage() {
        return null;
    }
}
