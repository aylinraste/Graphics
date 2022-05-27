package com.example.hw3.Model;

import com.example.hw3.HelloApplication;
import com.example.hw3.View.GameView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Cuphead extends ImageView {
    private int heart = 10;
    private Game game;

    public Cuphead(Game game){
        this.setImage(new Image(HelloApplication.class.getResource("frames/images/red.png").toExternalForm()));
        this.setX(50);
        this.setY(60);
        setFitWidth(70);
        setFitHeight(70);
        this.game = game;
        if (GameDatabase.getDifficulty() == 1) {
            heart = 10;
        }
        if (GameDatabase.getDifficulty() == 2) {
            heart = 5;
        }
        if (GameDatabase.getDifficulty() == 3) {
            heart = 2;
        }
    }

    public void moveRight() {
        if (!hitRightWall())
            this.setX(this.getX() + 5);
    }

    public void moveLeft() {
        if (!hitLeftWall())
            this.setX(this.getX() - 5);
    }

    public void moveUp() {
        if (!hitTopWall())
            this.setY(this.getY() - 5);
    }

    public void moveDown() {
        if (!hitFloor())
            this.setY(this.getY() + 5);
    }

    public boolean hitTopWall() {
        return this.getY() - 5 <= 0;
    }

    public boolean hitLeftWall() {
        return this.getX() - 5 <= 0;
    }

    public boolean hitRightWall() {
        return this.getX() + this.getFitWidth() + 5 >= game.getPane().getWidth();
    }

    public boolean hitFloor() {
        return this.getY() + this.getFitHeight() + 5 >= game.getPane().getHeight();
    }

    public void setHeart(int heart) {
        this.heart = heart;
    }

    public int getHeart() {
        return heart;
    }
}
