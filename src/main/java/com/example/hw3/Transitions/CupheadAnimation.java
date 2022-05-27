package com.example.hw3.Transitions;

import com.example.hw3.Model.Bullet;
import com.example.hw3.Model.Cuphead;
import com.example.hw3.Model.Game;
import com.example.hw3.View.EndView;
import javafx.animation.Transition;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.FileNotFoundException;
import java.io.IOException;

public class CupheadAnimation extends Transition {
    private boolean leftKey, rightKey, upKey, downKey, spaceKey;
    private Cuphead cuphead;
    private Game game;
    private Pane pane;

    public CupheadAnimation(boolean leftKey, boolean rightKey, boolean upKey, boolean downKey, boolean spaceKey, Game game) {
        this.leftKey = leftKey;
        this.rightKey = rightKey;
        this.upKey = upKey;
        this.downKey = downKey;
        this.spaceKey = spaceKey;
        cuphead = game.getCuphead();
        this.game = game;
        this.pane = game.getPane();
        setCycleCount(-1);
        setCycleDuration(new Duration(250));
        cuphead.setOnKeyPressed(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent keyEvent) {
                String keyName = keyEvent.getCode().getName(); //Get Pressed Key Name!
                switch (keyName) {
                    case "Left":
                        CupheadAnimation.this.leftKey = true;
                        break;
                    case "Right":
                        CupheadAnimation.this.rightKey = true;
                        break;
                    case "Up":
                        CupheadAnimation.this.upKey = true;
                        break;
                    case "Down":
                        CupheadAnimation.this.downKey = true;
                        break;
                    case "Space":
                        CupheadAnimation.this.spaceKey = true;
                        break;
                }
            }
        });
        cuphead.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                String keyName = keyEvent.getCode().getName(); //Get Pressed Key Name!
                switch (keyName) {
                    case "Left":
                        CupheadAnimation.this.leftKey = false;
                        break;
                    case "Right":
                        CupheadAnimation.this.rightKey = false;
                        break;
                    case "Up":
                        CupheadAnimation.this.upKey = false;
                        break;
                    case "Down":
                        CupheadAnimation.this.downKey = false;
                        break;
                    case "Space":
                        CupheadAnimation.this.spaceKey = false;
                        break;
                }
            }
        });
    }

    @Override
    protected void interpolate(double v) {
        if (leftKey) {
            cuphead.moveLeft();
        }
        if (rightKey) {
            cuphead.moveRight();
        }
        if (upKey) {
            cuphead.moveUp();
        }
        if (downKey) {
            cuphead.moveDown();
        }
        if (spaceKey && v == 1) {
            shoot();
        }
        if (cuphead.getHeart() <= 0 || game.getBoss().getLife() <= 0) {
            this.stop();
            try {
                EndView endView = new EndView();
                endView.load(game);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void shoot () {
        BulletAnimation bulletAnimation = null;
        Bullet bullet = null;
        bullet = new Bullet(game);
        pane.getChildren().add(bullet);
        bulletAnimation = new BulletAnimation(game, bullet);
        bulletAnimation.play();
    }
}
