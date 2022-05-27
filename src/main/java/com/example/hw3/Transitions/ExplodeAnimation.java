package com.example.hw3.Transitions;

import com.example.hw3.HelloApplication;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import javafx.scene.image.ImageView;

public class ExplodeAnimation extends Transition {
    private ImageView explode;

    public ExplodeAnimation(ImageView object, Pane pane, ImageView explode) {
        setCycleDuration(Duration.millis(500));
        this.explode = explode;
        explode.setX(object.getX());
        explode.setY(object.getY() + 15);
        explode.setFitWidth(25);
        explode.setFitHeight(25);
        pane.getChildren().add(explode);
        setCycleCount(1);
    }

    @Override
    protected void interpolate(double v) {
        int frame = (int) Math.floor(v * 6);
        explode.setImage(new Image(HelloApplication.class.getResource("frames/explosion/" + frame + ".png").toExternalForm()));
    }
}
