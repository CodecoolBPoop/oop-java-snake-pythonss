package com.codecool.snake.entities.powerups;

import com.codecool.snake.Utils;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.Snake;
import com.codecool.snake.entities.snakes.SnakeHead;
import java.util.Random;


public class SimplePowerUp extends GameEntity implements Interactable {
    private static Random rnd = new Random();

    public SimplePowerUp(Snake snake) {
        SnakeHead head= snake.getHead();
        double headX = head.getX();
        double headY = head.getY();

        setImage(Globals.getInstance().getImage("SimplePickup"));

        double randX = rnd.nextDouble() * (Globals.WINDOW_WIDTH-50);
        double randY = rnd.nextDouble() * (Globals.WINDOW_HEIGHT-50);

        double powerUpX = Utils.getSpawnedEntityX(headX, randX);
        setX(powerUpX);

        double powerUpY = Utils.getSpawnedEntityY(headY, randY);
        setY(powerUpY);
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
        return "Got power-up :)";
    }
}
