package com.example.hw3.Transitions;

import com.example.hw3.HelloApplication;
import com.example.hw3.Model.*;
import com.example.hw3.View.EndView;
import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class BossAnimation extends DevilAndBossAnimation {

    public BossAnimation(Game game) {
        super(game);
        setCycleDuration(Duration.millis(700));
        setCycleCount(-1);
    }

    @Override
    protected void interpolate(double v) {
        double dx = speed * Math.cos(Math.toRadians(theta));
        double dy = speed * Math.sin(Math.toRadians(theta)) * (-1);
        endShoot = System.currentTimeMillis();
        endMini = System.currentTimeMillis();
        String address;
        if (boss.getLife() <= 75) {
            address = death(v);
        }
        else {
            if (!isShooting) address = notShooting(v);
            else address = shooting(v);
        }
        if (endShoot - startShoot >= 3000) isShooting = true;
        if (endMini - startMini >= 13000 && (miniBosses.size() == 0 || miniBosses.get(miniBosses.size() - 1).isNextPossible()))
            miniBoss(v);
        boss.setImage(new Image(HelloApplication.class.getResource(address).toExternalForm()));
        boss.move(dx, dy);
        checkHit();
        if (isDead) {
            DevilAnimation devilAnimation = new DevilAnimation(game);
            devilAnimation.play();
            boss.setDevil(true);
            this.stop();
        }
    }

    protected String death(double v) {
        int frame = (int) Math.floor(v * 8) + 1;
        String address = "frames/Death/bird_large_death_000" + frame + ".png";
        if (frame == 9) {
            isDead = true;
        }
        return address;
    }

    protected String notShooting(double v) {
        int frame = (int) Math.floor(v * 5) + 1;
        String address = "frames/BossFly/" + frame + ".png";
        return address;
    }

    protected String shooting(double v) {
        int frame = (int) Math.floor(v * 11) + 1;
        String address = "frames/BossShoot/" + frame + ".png";
        if (frame == 5 && !isBallShoot) {
            isBallShoot = true;
            BallAnimation ballAnimation = null;
            Ball ball = new Ball(boss, cuphead, 30, 30, "frames/images/egg.png");
            pane.getChildren().add(ball);
            ballAnimation = new BallAnimation(ball, game);
            ballAnimation.play();
        }
        if (frame == 12) {
            isBallShoot = false;
            isShooting = false;
            startShoot = System.currentTimeMillis();
        }
        return address;
    }
}

