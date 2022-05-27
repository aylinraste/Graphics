package com.example.hw3.Model;

import com.example.hw3.HelloApplication;
import com.example.hw3.View.GameView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Boss extends ImageView {
    private double life;
    private boolean isDevil = false;
    private Cuphead cuphead;
    private Game game;

    public Boss(Game game) {
        this.setImage(new Image(HelloApplication.class.getResource("frames/BossFly/1.png").toExternalForm()));
        this.setX(400);
        this.setY(60);
        setFitWidth(180);
        setFitHeight(180);
        this.cuphead = game.getCuphead();
        this.game = game;
        life = 150;
    }

    public void move (double dx, double dy) {
        this.setX(this.getX() + dx);
        this.setY(this.getY() + dy);
    }

    public boolean hitTopWall() {
        return this.getY() + 20 <= 0;
    }

    public boolean hitFloor() {
        return this.getY() + this.getFitHeight() - 10 >= game.getPane().getHeight();
    }

    public boolean hitTopWallDevil() {
        return this.getY()<= 0;
    }

    public boolean hitFloorDevil() {
        return this.getY() + this.getFitHeight() >= game.getPane().getHeight();
    }

    public boolean hitLeftWall() {
        return this.getX() + 5 <= 0;
    }

    public boolean hitRightWall() {
        return this.getX() + this.getFitWidth() - 5 >= game.getPane().getWidth();
    }

    public boolean hitCuphead() {
        return !(this.getX() > cuphead.getX() + cuphead.getFitWidth() - 50 || this.getX() + this.getFitWidth() < cuphead.getX() + 50)
                && !(this.getY() > cuphead.getY() - 50 + cuphead.getFitHeight() || this.getY() + this.getFitHeight() < cuphead.getY() + 50);
    }

    public boolean hitCupheadDevil() {
        return !(this.getX() > cuphead.getX() + cuphead.getFitWidth() - 10 || this.getX() + this.getFitWidth() < cuphead.getX() + 10)
                && !(this.getY() > cuphead.getY() - 10 + cuphead.getFitHeight() || this.getY() + this.getFitHeight() < cuphead.getY() + 10);
    }

    public void setLife(double life) {
        this.life = life;
    }

    public double getLife() {
        return life;
    }

    public boolean isDevil() {
        return isDevil;
    }

    public void setDevil(boolean devil) {
        isDevil = devil;
    }
}
