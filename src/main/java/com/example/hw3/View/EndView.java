package com.example.hw3.View;

import com.example.hw3.HelloApplication;
import com.example.hw3.Model.Game;
import com.example.hw3.Model.GameDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Objects;
import java.util.ResourceBundle;

public class EndView implements Initializable {
    @FXML
    public Label scoreLabel, endLabel;
    @FXML
    public Label timeLabel;
    @FXML
    public Button retry;
    @FXML
    public Button back;
    @FXML
    public ProgressBar progress;
    public Pane pane;
    private static Game game;
    private static boolean isCalled = false;

    private void endWorks() {
        game.setEnd(System.currentTimeMillis());
        game.setScore(game.getScore() + game.getCuphead().getHeart() * 10);
        if (GameDatabase.getUser().getHighScore() < game.getScore()) {
            GameDatabase.getUser().setHighScore(game.getScore());
            DecimalFormat df = new DecimalFormat("0.00");
            GameDatabase.getUser().setTime(Double.parseDouble(df.format((game.getEnd() - game.getStart()) / 1000f)));
        }
    }

    public void load(Game game) throws IOException {
        if (!isCalled) {
            EndView.game = game;
            isCalled = true;
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root = fxmlLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("end-View.fxml")));
            fxmlLoader.getController();
            Stage stage = HelloApplication.getStage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        endWorks();
        this.scoreLabel.setText("SCORE: " + game.getScore());
        this.timeLabel.setText("TIME: " + String.format("%.2f", (game.getEnd() - game.getStart()) / 1000f) + "s");
        MediaPlayer mediaPlayer;
        if (game.getCuphead().getHeart() <= 0) {
            this.endLabel.setText("YOU LOST");
            mediaPlayer = new MediaPlayer(new Media(HelloApplication.class.getResource("musics/2.mp3").toExternalForm()));
        }
        else {
            this.endLabel.setText("YOU WON");
            mediaPlayer = new MediaPlayer(new Media(HelloApplication.class.getResource("musics/4.mp3").toExternalForm()));
        }
        endLabel.setTextFill(Color.rgb(200, 120, 50));
        progress.setProgress((200 - game.getBoss().getLife()) / 200f);
        if (!HelloApplication.getMediaPlayer().isMute()) {
            HelloApplication.getMediaPlayer().pause();
            MediaView mediaView = new MediaView(mediaPlayer);
            HelloApplication.setMediaPlayer(mediaPlayer);
            mediaPlayer.setAutoPlay(true);
            mediaPlayer.setCycleCount(-1);
            pane.getChildren().add(mediaView);
        }
    }

    public void retry(ActionEvent event) throws IOException {
        GameView.load((Stage) ((Node) event.getSource()).getScene().getWindow());
    }

    public void back(ActionEvent event) throws IOException {
        MainMenuView.load((Stage) ((Node) event.getSource()).getScene().getWindow());
    }

    public static void setIsCalled(boolean isCalled) {
        EndView.isCalled = isCalled;
    }
}
