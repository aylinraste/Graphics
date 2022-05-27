package com.example.hw3.Transitions;

import com.example.hw3.Model.*;
import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.ArrayList;

public class BulletAnimation extends Transition {
    private Bullet bullet;
    private double speed = 5;
    private int theta = 0;
    private Boss boss;
    private Pane pane;
    private ArrayList<MiniBoss> miniBosses;
    private Game game;

    public BulletAnimation(Game game, Bullet bullet) {
        this.boss = game.getBoss();
        this.setCycleDuration(Duration.millis(100));
        this.setCycleCount(40);
        this.bullet = bullet;
        this.pane = game.getPane();
        this.miniBosses = game.getMiniBosses();
        this.game = game;
    }

    @Override
    protected void interpolate(double v) {
        double dx = speed * Math.cos(Math.toRadians(theta));
        double dy = speed * Math.sin(Math.toRadians(theta)) * (-1);
        bullet.move(dx, dy);
        if (bullet.hitBoss()) {
            boss.setLife(boss.getLife() - 1);
            game.setScore(game.getScore() + 1);
            explode();
        }
        if (boss.isDevil() && bullet.hitDevil()) {
            game.setScore(game.getScore() + 1);
            if (GameDatabase.getDifficulty() == 1) {
                boss.setLife(boss.getLife() - 1.5);
            }
            if (GameDatabase.getDifficulty() == 2) {
                boss.setLife(boss.getLife() - 1);
            }
            if (GameDatabase.getDifficulty() == 3) {
                boss.setLife(boss.getLife() - 0.5);
            }
            explode();
        }
        if (miniBosses.size() != 0 && bullet.hitMiniBoss()) {
            miniBosses.get(0).setLife(miniBosses.get(0).getLife() - 50);
            if (miniBosses.get(0).getLife() <= 0) {
                pane.getChildren().remove(miniBosses.get(0));
//                miniBosses.get(0).setX(1000);
//                miniBosses.get(0).setY(1000);
                game.setScore(game.getScore() + 1);
                miniBosses.remove(0);
            }
            explode();
            this.stop();
        }
    }

    public void explode() {
        pane.getChildren().remove(bullet);
        ImageView explode = new ImageView();
        ExplodeAnimation animation = new ExplodeAnimation(bullet, pane, explode);
        BulletAnimation.this.stop();
        animation.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                pane.getChildren().remove(explode);
            }
        });
        animation.play();
    }
}
