package com.codecool.snake.entities.powerups;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.snakes.Snake;
import com.codecool.snake.entities.snakes.SnakeHead;

public class HealingPowerUp extends SimplePowerUp {

    public HealingPowerUp(Snake snake) {
        super(snake);
    }

    @Override
    public void apply(GameEntity entity) {
        if(entity instanceof SnakeHead){
            Snake snake = ((SnakeHead)entity).getSnake();
            snake.changeHealth(-5);
            System.out.println(getMessage());
            destroy();
        }
    }

    @Override
    public String getMessage() {
        return "Healed-up :)";
    }
}
