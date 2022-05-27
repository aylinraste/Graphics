package com.example.hw3.Transitions;

import com.example.hw3.Model.Ball;
import com.example.hw3.Model.Cuphead;
import com.example.hw3.Model.Game;
import com.example.hw3.View.EndView;
import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.IOException;

public class BallAnimation extends Transition {
    private Ball ball;
    private double speed = 2;
    private int theta = 180;
    private Cuphead cuphead;
    private Pane pane;
    private Game game;

    public BallAnimation(Ball ball, Game game) {
        this.ball = ball;
        this.setCycleDuration(Duration.millis(50));
        this.setCycleCount(80);
        this.cuphead = game.getCuphead();
        this.pane = game.getPane();
        this.game = game;
    }

    @Override
    protected void interpolate(double v) {
        double dx = speed * Math.cos(Math.toRadians(theta));
        double dy = speed * Math.sin(Math.toRadians(theta)) * (-1);
        ball.move(dx, dy);
        if (ball.hitCuphead()) {
            explode();
            this.stop();
        }

    }

    public void explode(){
        cuphead.setHeart(cuphead.getHeart() - 1);
        pane.getChildren().remove(game.getHearts().get(game.getHearts().size() - 1));
        game.getHearts().remove(game.getHearts().size() - 1);
        pane.getChildren().remove(ball);
        ImageView explode = new ImageView();
        ExplodeAnimation animation = new ExplodeAnimation(ball, pane, explode);
        this.stop();
        animation.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                pane.getChildren().remove(explode);
            }
        });
        animation.play();
    }
}
