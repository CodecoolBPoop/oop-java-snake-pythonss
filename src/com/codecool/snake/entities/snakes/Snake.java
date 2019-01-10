package com.codecool.snake.entities.snakes;

import com.codecool.snake.DelayedModificationList;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.eventhandler.InputHandler;
import com.sun.javafx.geom.Vec2d;
import javafx.scene.control.Alert;
import javafx.scene.control.Dialog;
import javafx.scene.input.KeyCode;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Optional;

public class Snake implements Animatable {
    private static final float speed = 2;
    private int health = 100;
    private int ammo = 10;
    private int weaponCooldown = 30;

    private SnakeHead head;
    private DelayedModificationList<GameEntity> body;

    public Snake(Vec2d position) {
        head = new SnakeHead(this, position);
        body = new DelayedModificationList<>();

        addPart(2);
    }


    public void step() {
        SnakeControl turnDir = getUserInput();
        head.updateRotation(turnDir, speed);

        updateSnakeBodyHistory();
        checkForGameOverConditions();

        body.doPendingModifications();

        if (weaponCooldown > 0) weaponCooldown--;
    }

    private SnakeControl getUserInput() {
        SnakeControl turnDir = SnakeControl.INVALID;
        if (InputHandler.getInstance().isKeyPressed(KeyCode.LEFT)) turnDir = SnakeControl.TURN_LEFT;
        if (InputHandler.getInstance().isKeyPressed(KeyCode.RIGHT)) turnDir = SnakeControl.TURN_RIGHT;
        return turnDir;
    }

    public void addPart(int numParts) {
        GameEntity parent = getLastPart();
        Vec2d position = parent.getPosition();

        for (int i = 0; i < numParts; i++) {
            SnakeBody newBodyPart = new SnakeBody(position);
            body.add(newBodyPart);
        }
        Globals.getInstance().display.updateSnakeHeadDrawPosition(head);
    }

    public void changeHealth(int diff) {
        if (health < 101) {
            health -= diff;
        } else if (health > 100) {
            health = 100;
        }
    }

    public int getHealth() {
        return health;
    }

    private void checkForGameOverConditions() {
        if (head.isOutOfBounds() || health <= 0) {
            System.out.println("Game Over");
            Globals.getInstance().stopGame();
            showGameOverAlert();
        }
    }

    private void updateSnakeBodyHistory() {
        GameEntity prev = head;
        for (GameEntity currentPart : body.getList()) {
            currentPart.setPosition(prev.getPosition());
            prev = currentPart;
        }
    }

    private GameEntity getLastPart() {
        GameEntity result = body.getLast();

        if (result != null) return result;
        return head;
    }

    public SnakeHead getHead() {
        return head;
    }

    public int getWeaponCooldown() {
        return weaponCooldown;
    }

    public void setWeaponCooldown(int weaponCooldown) {
        this.weaponCooldown = weaponCooldown;
    }

    public int getAmmo() {
        return ammo;
    }

    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }

    public void changeAmmo(int amount) {
        this.ammo += amount;
    }

    public void showGameOverAlert() {
        Alert deadAlert = new Alert(Alert.AlertType.INFORMATION);

        int globalScore = Globals.getInstance().getGlobalScore();
        String globalScoreString = Integer.toString(globalScore);
        deadAlert.setTitle("You Died");
        deadAlert.setHeaderText("DEAD");
        deadAlert.setContentText("Your score is: " + globalScoreString);
        deadAlert.initModality(Modality.APPLICATION_MODAL);
        deadAlert.show();
        Stage stage = (Stage) deadAlert.getDialogPane().getScene().getWindow();
        stage.setAlwaysOnTop(true);
    }

}
