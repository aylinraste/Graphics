package com.example.hw3.View;

import com.example.hw3.HelloApplication;
import com.example.hw3.Model.GameDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainMenuView {
    public static void load(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("mainMenu-view.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void newGame(ActionEvent event) throws IOException {
        GameView.load((Stage) ((Node) event.getSource()).getScene().getWindow());
    }

    public void scoreBoard(ActionEvent event) throws IOException {
        ScoreBoardView.load((Stage) ((Node) event.getSource()).getScene().getWindow());
    }

    public void profile(ActionEvent event) throws IOException {
        ProfileMenuView.load((Stage) ((Node) event.getSource()).getScene().getWindow());
    }

    public void setting(ActionEvent event) throws IOException {
        SettingsView.load((Stage) ((Node) event.getSource()).getScene().getWindow());
    }
}
