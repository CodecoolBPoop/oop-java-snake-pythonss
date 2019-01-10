package com.codecool.snake.entities.powerups;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.snakes.Snake;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;

public class GoldenSnitch extends SimplePowerUp {

    private double speed = 2;

    public GoldenSnitch(Snake snake) {
        super(snake);

    }

    public void step(Snake snake) {
        SnakeHead snakeHead = snake.getHead();
        double snakeHeadX = snakeHead.getX();
        double snakeHeadY = snakeHead.getY();
        double distance = Math.sqrt(Math.pow(snakeHeadX-getX(), 2) + Math.pow(snakeHeadY-getY(), 2));
        if (distance <= 200) {
            double headRotation = snakeHead.getRotate();
            setRotate(headRotation);
            Point2D heading = Utils.directionToVector(headRotation, speed);
            if ((getX() > 5 && getX() < 960) && (getY() > 5 && getY() < 660)) {
                setX(getX() + heading.getX());
                setY(getY() + heading.getY());
            } else if (!(getX() > 5 && getX() < 960) && (getY() > 5 && getY() < 660)) {
                setY(getY() + heading.getY());
            } else if ((getX() > 5 && getX() < 960) && !(getY() > 5 && getY() < 660)) {
                setX(getX() + heading.getX());
            }
        }
    }

    @Override
    public void apply(GameEntity entity) {
        if(entity instanceof SnakeHead){
            Snake snake = ((SnakeHead)entity).getSnake();
            snake.addPart(2);
            System.out.println(getMessage());
            destroy();
        }
    }

    @Override
    public String getMessage() {
        return "Golden Snitch picked up :)";
    }
}
