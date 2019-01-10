package com.codecool.snake;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Game game = new Game();

        Scene mainScene = new Scene(game, Globals.WINDOW_WIDTH, Globals.WINDOW_HEIGHT, Color.ANTIQUEWHITE);



        primaryStage.setTitle("Snake Game");
        primaryStage.setScene(mainScene);
        primaryStage.show();


        game.addHealthBar();
        game.addAmmoBar();
        game.addRestartButton();
        game.start();
    }

    @Override
    public void stop() throws Exception {
        System.out.println("Exiting..");
    }



}
