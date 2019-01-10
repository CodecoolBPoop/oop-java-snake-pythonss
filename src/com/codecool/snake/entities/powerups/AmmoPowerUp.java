package com.codecool.snake.entities.powerups;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.snakes.Snake;
import com.codecool.snake.entities.snakes.SnakeHead;

public class AmmoPowerUp extends SimplePowerUp {

    public AmmoPowerUp(Snake snake) {
        super(snake);
        setImage(Globals.getInstance().getImage("AmmoPickup"));
    }

    @Override
    public void apply(GameEntity entity) {
        if(entity instanceof SnakeHead){
            Snake snake = ((SnakeHead)entity).getSnake();
            snake.changeAmmo(5);
            Globals.getInstance().ammoCounter();
            System.out.println(snake.getAmmo());
            System.out.println(getMessage());
            destroy();
        }
    }

    @Override
    public String getMessage() {
        return "Ammo picked up :)";
    }
}
