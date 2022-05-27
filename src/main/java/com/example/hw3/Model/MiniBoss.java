package com.example.hw3.Model;

import com.example.hw3.HelloApplication;
import com.example.hw3.View.GameView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class MiniBoss extends ImageView {
    private int life = 100;
    private String color;
    private Cuphead cuphead;
    private Game game;

    public MiniBoss(String color, Game game, double y) {
        this.setImage(new Image(HelloApplication.class.getResource("frames/MiniBossFly/" + color +"/1.png").toExternalForm()));
        this.setX(600);
        this.setY(y);
        setFitWidth(50);
        setFitHeight(50);
        this.color = color;
        this.cuphead = game.getCuphead();
        this.game = game;
    }
    public void move (double dx, double dy) {
        this.setX(this.getX() + dx);
        this.setY(this.getY() + dy);
    }
    public boolean hitTopWall() {
        return this.getY() <= 0;
    }
    public boolean hitFloor() {
        return this.getY() + this.getFitHeight() >= game.getPane().getHeight();
    }

    public boolean hitCuphead() {
        return !(this.getX() > cuphead.getX() + cuphead.getFitWidth() || this.getX() + this.getFitWidth() < cuphead.getX())
                && !(this.getY() > cuphead.getY() + cuphead.getFitHeight() || this.getY() + this.getFitHeight() < cuphead.getY());
    }
    public boolean isNextPossible() {
        return this.getX() < 560;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getLife() {
        return life;
    }

    public String getColor() {
        return color;
    }
}
