package com.example.hw3.Transitions;

import com.example.hw3.HelloApplication;
import com.example.hw3.Model.*;
import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Random;

public class DevilAnimation extends DevilAndBossAnimation {

    public DevilAnimation(Game game) {
        super(game);
        setCycleDuration(Duration.millis(700));
        setCycleCount(-1);
        boss.setFitWidth(100);
        boss.setFitHeight(100);
    }

    @Override
    protected void interpolate(double v) {
        if (((int) Math.floor(v * 1000)) == 1000) {
            theta = random.nextInt(360);
        }
        double dx = speed * Math.cos(Math.toRadians(theta));
        double dy = speed * Math.sin(Math.toRadians(theta)) * (-1);
        endShoot = System.currentTimeMillis();
        endMini = System.currentTimeMillis();
        String address;
        if (boss.getLife() <= 0) {
            address = death(v);
        } else {
            if (!isShooting) address = notShooting(v);
            else address = shooting(v);
        }
        if (endShoot - startShoot >= 3000) isShooting = true;
        if (endMini - startMini >= 13000 && (miniBosses.size() == 0 || miniBosses.get(miniBosses.size() - 1).isNextPossible()))
            miniBoss(v);
        boss.setImage(new Image(HelloApplication.class.getResource(address).toExternalForm()));
        boss.move(dx, dy);
        if (cuphead.getHeart() <= 0) {
            this.stop();
        }
        checkHit();
        if (isDead) {
            this.stop();
            pane.getChildren().remove(boss);
            boss.setX(-1000);
            boss.setY(-1000);
        }
    }

    protected String death(double v) {
        String address;
        int frame = (int) Math.floor(v * 7) + 1;
        address = "frames/Idle/Death/Transition/" + frame + ".png";

        if (frame == 8) {
            isDead = true;
        }
        return address;
    }

    protected String notShooting(double v) {
        String address;
        int frame = (int) Math.floor(v * 14) + 1;
        address = ("frames/Idle/" + frame + ".png");
        return address;
    }

    protected String shooting(double v) {
        int frame;
        frame = (int) Math.floor(v * 19) + 1;
        String address = "frames/Idle/Shoot/" + frame + ".png";
        if (frame == 13 && !isBallShoot) {
            isBallShoot = true;
            BallAnimation ballAnimation = null;
            Ball ball = new Ball(boss, cuphead, 20, 20, "frames/Idle/Bullet/1.png");
            pane.getChildren().add(ball);
            ballAnimation = new BallAnimation(ball, game);
            ballAnimation.play();
        }
        if (frame == 20) {
            isBallShoot = false;
            isShooting = false;
            startShoot = System.currentTimeMillis();
        }
        return address;
    }
}
