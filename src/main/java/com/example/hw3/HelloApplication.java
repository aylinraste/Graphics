package com.example.hw3;

import com.example.hw3.Model.UserDatabase;
import com.example.hw3.View.LoginView;
import javafx.application.Application;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import java.io.IOException;

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