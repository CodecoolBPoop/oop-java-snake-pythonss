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
        if(snakeHeadX == spawnedEntityX) {
            double newEntityX =  spawnedEntityX+ 150;
            if(newEntityX > Globals.WINDOW_WIDTH) {
                newEntityX = Globals.WINDOW_WIDTH - 170;
                return newEntityX;
            } else {
                return newEntityX;
            }
        } else {
            return spawnedEntityX;
        }
    }


    public static double getSpawnedEntityY (double snakeHeadY, double spawnedEntityY) {
        if(snakeHeadY == spawnedEntityY) {
            double newEntityY = spawnedEntityY + 150;
            if(newEntityY > Globals.WINDOW_HEIGHT) {
                newEntityY = Globals.WINDOW_HEIGHT - 170;
                return newEntityY;
            } else {
                return newEntityY;
            }
        } else {
            return spawnedEntityY;
        }
    }
}
