package com.codecool.snake.entities.enemies;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.Snake;
import com.codecool.snake.entities.snakes.SnakeHead;
import java.util.Random;


public class ChasingEnemy extends Enemy implements Interactable {
    private static Random rnd = new Random();
    private double speed;


    public ChasingEnemy() {
        super(10);
        setImage(Globals.getInstance().getImage("ChasingEnemy"));

        double enemyX = rnd.nextDouble() * Globals.WINDOW_WIDTH;
        setX(enemyX);

        double enemyY = rnd.nextDouble() * Globals.WINDOW_HEIGHT;
        setY(enemyY);

        speed = 1;

    }

    public void step(Snake snake) {
        SnakeHead head = snake.getHead();
        double targetSnakeHeadX = head.getX();
        double targetSnakeHeadY = head.getY();
        double sourceEnemyX = this.getX();
        double sourceEnemyY = this.getY();

        double angle = Math.atan2(targetSnakeHeadY - sourceEnemyY, targetSnakeHeadX - sourceEnemyX);

        setX(getX() + speed * Math.cos(angle));
        setY(getY() + speed * Math.sin(angle));
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
