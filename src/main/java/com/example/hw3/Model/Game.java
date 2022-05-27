package com.example.hw3.Model;

import com.example.hw3.HelloApplication;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Game {
    private int score;
    private final long start;
    private long end;
    private Boss boss;
    private Cuphead cuphead;
    private Pane pane;
    private ArrayList<MiniBoss> miniBosses;
    private ArrayList<ImageView> hearts = new ArrayList<>();


    public Game(Pane pane) {
        this.pane = pane;
        cuphead = new Cuphead(this);
        boss = new Boss(this);
        miniBosses = new ArrayList<>();
        start = System.currentTimeMillis();
        score = 0;
        for (int i = 0; i < cuphead.getHeart(); i++) {
            ImageView heart = new ImageView(new Image(HelloApplication.class.getResource("frames/icons/heart.png").toExternalForm()));
            heart.setX(100 - 26 * GameDatabase.getDifficulty() + i * 50);
            heart.setY(300);
            heart.setFitHeight(25);
            heart.setFitWidth(25);
            hearts.add(heart);
        }
    }

    public int getScore() {
        return score;
    }

    public Boss getBoss() {
        return boss;
    }

    public Cuphead getCuphead() {
        return cuphead;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setBoss(Boss boss) {
        this.boss = boss;
    }

    public void setCuphead(Cuphead cuphead) {
        this.cuphead = cuphead;
    }

    public void setPane(Pane pane) {
        this.pane = pane;
    }

    public Pane getPane() {
        return pane;
    }

    public ArrayList<MiniBoss> getMiniBosses() {
        return miniBosses;
    }

    public void setMiniBosses(ArrayList<MiniBoss> miniBosses) {
        this.miniBosses = miniBosses;
    }

    public long getStart() {
        return start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    public ArrayList<ImageView> getHearts() {
        return hearts;
    }
}
