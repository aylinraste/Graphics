package com.example.hw3.Transitions;

import com.example.hw3.HelloApplication;
import com.example.hw3.Model.Cuphead;
import com.example.hw3.Model.Game;
import com.example.hw3.Model.MiniBoss;
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

public class MiniBossAnimation extends Transition {
    private final MiniBoss miniBoss;
    private final Pane pane;
    private final String color;
    private final double speed = 2;
    private final int theta = 180;
    private final Cuphead cuphead;
    private final ArrayList<MiniBoss> miniBosses;
    private final Game game;

    public MiniBossAnimation(Game game, String color) {
        //TODO
        this.miniBoss = game.getMiniBosses().get(game.getMiniBosses().size() - 1);
        this.pane = game.getPane();
        this.color = color;
        setCycleDuration(Duration.millis(700));
        setCycleCount(8);
        this.cuphead = game.getCuphead();
        this.miniBosses = game.getMiniBosses();
        this.game = game;
    }
    @Override
    protected void interpolate(double v) {
        double dx = speed * Math.cos(Math.toRadians(theta));
        double dy = speed * Math.sin(Math.toRadians(theta)) * (-1);
        int frame = (int) Math.floor(v * 3) + 1;
        miniBoss.setImage(new Image(HelloApplication.class.getResource("frames/MiniBossFly/" + color +"/" + frame + ".png").toExternalForm()));
        miniBoss.move(dx, dy);
        if (miniBosses.size() != 0 && miniBoss.hitCuphead()) {
            miniBosses.remove(miniBoss);
            this.stop();
            if (cuphead.getHeart() <= 0) {
                return;
            }
            explode();
        }
    }
    public void explode(){
        cuphead.setHeart(cuphead.getHeart() - 1);
        pane.getChildren().remove(miniBoss);
        pane.getChildren().remove(game.getHearts().get(game.getHearts().size() - 1));
        game.getHearts().remove(game.getHearts().size() - 1);
        ImageView explode = new ImageView();
        ExplodeAnimation animation = new ExplodeAnimation(miniBoss, pane, explode);
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
