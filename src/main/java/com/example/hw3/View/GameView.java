package com.example.hw3.View;

import com.example.hw3.HelloApplication;
import com.example.hw3.Model.Boss;
import com.example.hw3.Model.Cuphead;
import com.example.hw3.Model.Game;
import com.example.hw3.Model.GameDatabase;
import com.example.hw3.Transitions.BossAnimation;
import com.example.hw3.Transitions.CupheadAnimation;
import com.example.hw3.Transitions.DevilAnimation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class GameView implements Initializable {
    @FXML
    public Pane pane;
    public Button start;
    private Game game;
    private boolean rightKey = false, leftKey = false, upKey = false, downKey = false, spaceKey = false;

    public static void load(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("game-view.fxml")));
        fxmlLoader.setController(new GameView());
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void run() throws FileNotFoundException {
        background();
        game = new Game(pane);
        EndView.setIsCalled(false);
        Boss boss = game.getBoss();
        Cuphead cuphead = game.getCuphead();
        pane.getChildren().add(boss);
        pane.getChildren().add(cuphead);
        pane.getChildren().addAll(game.getHearts());
        pane.getChildren().remove(start);
        cuphead.requestFocus();
        if (GameDatabase.isDevil()) {
            DevilAnimation devilAnimation = new DevilAnimation(game);
            devilAnimation.play();
            boss.setDevil(true);
        }
        else {
            BossAnimation bossAnimation = new BossAnimation(game);
            bossAnimation.play();
        }
        CupheadAnimation cupheadAnimation = new CupheadAnimation(leftKey, rightKey, upKey, downKey, spaceKey, game);
        cupheadAnimation.play();
    }


    public void background() {
        DoubleProperty xPosition = new SimpleDoubleProperty(0);
        xPosition.addListener((observable, oldValue, newValue) -> setBackgroundPositions(pane, xPosition.get()));
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(xPosition, 0)),
                new KeyFrame(Duration.seconds(200), new KeyValue(xPosition, -15000))
        );
        timeline.play();
    }

    void setBackgroundPositions(Region region, double xPosition) {
        String style = "-fx-background-position: " +
                "left " + xPosition / 6 + "px bottom," +
                "left " + xPosition / 5 + "px bottom," +
                "left " + xPosition / 4 + "px bottom," +
                "left " + xPosition / 3 + "px bottom," +
                "left " + xPosition / 2 + "px bottom," +
                "left " + xPosition + "px bottom;";
        region.setStyle(style);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (!HelloApplication.getMediaPlayer().isMute()) {
            HelloApplication.getMediaPlayer().pause();
            MediaPlayer mediaPlayer = new MediaPlayer(new Media(HelloApplication.class.getResource("musics/3.mp3").toExternalForm()));
            MediaView mediaView = new MediaView(mediaPlayer);
            HelloApplication.setMediaPlayer(mediaPlayer);
            mediaPlayer.setAutoPlay(true);
            mediaPlayer.setCycleCount(-1);
            pane.getChildren().add(mediaView);
        }
    }

}
