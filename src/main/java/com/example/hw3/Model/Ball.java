package com.example.hw3.Model;

import com.example.hw3.HelloApplication;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Ball extends ImageView {
    private Cuphead cuphead;

    public Ball(Boss boss, Cuphead cuphead, int w, int h, String url) {
        this.setImage(new Image(HelloApplication.class.getResource(url).toExternalForm()));
        this.setX(boss.getX());
        if (boss.isDevil()) this.setY(boss.getY() + 20);
        else this.setY(boss.getY() + 70);
        setFitWidth(w);
        setFitHeight(h);
        this.cuphead = cuphead;
    }
    public void move (double dx, double dy) {
        this.setX(this.getX() + dx);
        this.setY(this.getY() + dy);
    }
    public boolean hitCuphead() {
        return !(this.getX() > cuphead.getX() - 10 + cuphead.getFitWidth() || this.getX() + this.getFitWidth() < cuphead.getX() + 10)
                && !(this.getY() > cuphead.getY() - 10 + cuphead.getFitHeight() || this.getY() + this.getFitHeight() < cuphead.getY() + 10);
    }

}
