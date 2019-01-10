package com.codecool.snake.entities.powerups;

import com.codecool.snake.Game;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.snakes.Snake;
import com.codecool.snake.entities.snakes.SnakeHead;

public class HealingPowerUp extends SimplePowerUp {

    public HealingPowerUp(Snake snake) {
        super(snake);
        setImage(Globals.getInstance().getImage("HealthPickup"));
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
