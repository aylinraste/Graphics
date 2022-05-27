package com.example.hw3.Model;

import com.example.hw3.HelloApplication;
import com.example.hw3.View.GameView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class Bullet extends ImageView {
    private final Boss boss;
    private final ArrayList<MiniBoss> miniBosses;
    private Game game;

    public Bullet(Game game) {
        this.setImage(new Image(HelloApplication.class.getResource("frames/images/bullet.png").toExternalForm()));
        this.game = game;
        boss = game.getBoss();
        miniBosses = game.getMiniBosses();
        this.setX(game.getCuphead().getX() + game.getCuphead().getFitWidth());
        this.setY(game.getCuphead().getY() + 20);
        setFitWidth(15);
        setFitHeight(7);
    }
    public void move (double dx, double dy) {
        this.setX(this.getX() + dx);
        this.setY(this.getY() + dy);
    }
    public boolean hitBoss() {
        return !(this.getX() > boss.getX() + boss.getFitWidth() - 50 || this.getX() + this.getFitWidth() < boss.getX() + 50)
                && !(this.getY() > boss.getY() - 50 + boss.getFitHeight() || this.getY() + this.getFitHeight() < boss.getY() + 50);
    }
    public boolean hitDevil() {
        return !(this.getX() > boss.getX() + boss.getFitWidth() - 10 || this.getX() + this.getFitWidth() < boss.getX() + 10)
                && !(this.getY() > boss.getY() + boss.getFitHeight() - 10 || this.getY() + this.getFitHeight() < boss.getY() + 10);
    }
    public boolean hitMiniBoss() {
        return miniBosses.size() != 0 && !(this.getX() > miniBosses.get(0).getX() + miniBosses.get(0).getFitWidth() || this.getX() + this.getFitWidth() < miniBosses.get(0).getX())
                && !(this.getY() > miniBosses.get(0).getY() + miniBosses.get(0).getFitHeight() || this.getY() + this.getFitHeight() < miniBosses.get(0).getY());
    }
    public boolean hitFloor() {
        return this.getY() + this.getFitHeight() >= game.getPane().getHeight();
    }

}
