package com.example.hw3;

import com.example.hw3.Model.UserDatabase;
import com.example.hw3.View.LoginView;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {
    private static Stage stage;
    private static MediaPlayer mediaPlayer = null;
    @Override
    public void start(Stage stage) throws IOException {
        HelloApplication.stage = stage;
        stage.setTitle("CupHead!");
        stage.setMinHeight(365.0);
        stage.setMinWidth(600.0);
        stage.setMaxHeight(365.0);
        stage.setMaxWidth(600.0);
        LoginView.load(stage);
//        MediaPlayer mediaPlayer = new MediaPlayer(new Media(new File("/Users/aylinrasteh/projects/HW3/src/main/resources/com/example/hw3/musics/1.wav").toURI().toString()));
//        mediaPlayer.setAutoPlay(true);
    }

    public static void main(String[] args) {
        UserDatabase.loadUsers();
        launch();
        UserDatabase.saveUsers();
    }

    public static Stage getStage() {
        return stage;
    }

    public static MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public static void setMediaPlayer(MediaPlayer mediaPlayer) {
        HelloApplication.mediaPlayer = mediaPlayer;
    }
}