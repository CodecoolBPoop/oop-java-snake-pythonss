package com.codecool.snake;

import javafx.geometry.Point2D;

public class Utils {

    /*
    Converts a direction in degrees (0...360) to x and y coordinates.
    The length of this vector is the second parameter
    */
    public static Point2D directionToVector(double directionInDegrees, double length) {
        double directionInRadians = directionInDegrees / 180 * Math.PI;
        Point2D heading = new Point2D(length * Math.sin(directionInRadians), - length * Math.cos(directionInRadians));
        return heading;
    }

    public static double getSpawnedEntityX (double snakeHeadX, double spawnedEntityX){
        double newEntityX =  spawnedEntityX+ 150;
        if(snakeHeadX == spawnedEntityX) {
            if(newEntityX > Globals.WINDOW_WIDTH) {
                newEntityX = Globals.WINDOW_WIDTH - 170;
                return newEntityX;
            } else {
                return newEntityX;
            }
        } else {
            newEntityX = spawnedEntityX;
            return newEntityX;
        }
    }

    public static double getSpawnedEntityY (double snakeHeadY, double spawnedEntityY) {
        double newEnemyY = spawnedEntityY + 150;
        if(snakeHeadY == spawnedEntityY) {
            if(newEnemyY > Globals.WINDOW_HEIGHT) {
                newEnemyY = Globals.WINDOW_HEIGHT - 170;
                return newEnemyY;
            } else {
                return newEnemyY;
            }
        } else {
            newEnemyY = spawnedEntityY;
            return newEnemyY;
        }
    }
}
