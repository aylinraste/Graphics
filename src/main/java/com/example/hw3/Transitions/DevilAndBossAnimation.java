package com.example.hw3.Transitions;

import com.example.hw3.Model.Boss;
import com.example.hw3.Model.Cuphead;
import com.example.hw3.Model.Game;
import com.example.hw3.Model.MiniBoss;
import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Random;

public abstract class DevilAndBossAnimation extends Transition {
    protected final Boss boss;
    protected final double speed = 2;
    protected int theta = 90;
    protected long startShoot, endShoot;
    protected long startMini, endMini;
    protected boolean isShooting = false, isBallShoot = false, isDead = false;
    protected final Pane pane;
    protected final ArrayList<MiniBoss> miniBosses;
    protected final Cuphead cuphead;
    protected final Random random = new Random();
    protected boolean hitCuphead = false;
    protected final Game game;

    public DevilAndBossAnimation(Game game) {
        this.boss = game.getBoss();
        startShoot = System.currentTimeMillis();
        startMini = System.currentTimeMillis();
        this.pane = game.getPane();
        this.cuphead = game.getCuphead();
        this.miniBosses = game.getMiniBosses();
        this.game = game;
    }

    public void miniBoss(double v) {
        String color;
        if (miniBosses.size() == 0 || miniBosses.size() == 5)
            color = "purple";
        else
            color = "yellow";
        MiniBossAnimation miniBossAnimation = null;
        MiniBoss miniBoss = null;
        miniBoss = new MiniBoss(color, game, (miniBosses.size() == 0 ? cuphead.getY() : miniBosses.get(0).getY()));
        miniBosses.add(miniBoss);
        pane.getChildren().add(miniBoss);
        miniBossAnimation = new MiniBossAnimation(game, color);
        miniBossAnimation.play();
        MiniBoss finalMiniBoss = miniBoss;
        miniBossAnimation.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                miniBosses.remove(finalMiniBoss);
                pane.getChildren().remove(finalMiniBoss);
            }
        });
        if (miniBosses.size() == 6) {
            startMini = System.currentTimeMillis();
        }
    }

    public void explode(){
        cuphead.setHeart(cuphead.getHeart() - 1);
        pane.getChildren().remove(game.getHearts().get(game.getHearts().size() - 1));
        game.getHearts().remove(game.getHearts().size() - 1);
        ImageView explode = new ImageView();
        ExplodeAnimation animation = new ExplodeAnimation(cuphead, pane, explode);
        animation.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                pane.getChildren().remove(explode);
            }
        });
        animation.play();
    }

    protected void checkHit(){
        if (boss.hitTopWallDevil() || boss.hitFloorDevil())
            theta = -theta;
        if (boss.hitRightWall() || boss.hitLeftWall())
            theta = 180 - theta;
        if (boss.hitCupheadDevil() && !hitCuphead) {
            explode();
            if (cuphead.getHeart() <= 0) {
                this.stop();
            }
            hitCuphead = true;
        }
        if (!boss.hitCupheadDevil()) {
            hitCuphead = false;
        }
    }

    protected abstract String shooting(double v);
    protected abstract String notShooting(double v);
    protected abstract String death(double v);
}
