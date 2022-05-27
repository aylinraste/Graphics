package com.example.hw3.View;

import com.example.hw3.HelloApplication;
import com.example.hw3.Model.GameDatabase;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class SettingsView implements Initializable {
    public Slider slider;
    public ImageView mute;
    public ToggleButton devilMode;
    public ChoiceBox choiceBox;
    private Stage stage;

    public static void load(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("settings-view.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void back(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("mainMenu-view.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<String> choices = new ArrayList<>() {
            {
                add("EASY");
                add("DEFAULT");
                add("HARD");
            }
        };
        choiceBox.setItems(FXCollections.observableList(choices));
        choiceBox.setValue(choices.get(1));
        if (HelloApplication.getMediaPlayer().isMute()) {
            mute.setImage(new Image(HelloApplication.class.getResource("frames/icons/mute.png").toExternalForm()));
        } else {
            HelloApplication.getMediaPlayer().setMute(false);
            mute.setImage(new Image(HelloApplication.class.getResource("frames/icons/voice.png").toExternalForm()));
        }
        if (GameDatabase.isDevil()) {
            devilMode.setSelected(true);
        } else {
            devilMode.setSelected(false);
        }
    }

    public void mute(MouseEvent mouseEvent) {
        if (!HelloApplication.getMediaPlayer().isMute()) {
            HelloApplication.getMediaPlayer().setMute(true);
            mute.setImage(new Image(HelloApplication.class.getResource("frames/icons/mute.png").toExternalForm()));
        } else {
            HelloApplication.getMediaPlayer().setMute(false);
            mute.setImage(new Image(HelloApplication.class.getResource("frames/icons/voice.png").toExternalForm()));
        }
    }

    public void chengeDifficulty(MouseEvent contextMenuEvent) {
        if (choiceBox.getValue() == "HARD") {
            GameDatabase.setDifficulty(3);
        }
        if (choiceBox.getValue() == "EASY") {
            GameDatabase.setDifficulty(1);
        }
        if (choiceBox.getValue() == "DEFAULT") {
            GameDatabase.setDifficulty(2);
        }
    }

    public void activeDevil(ActionEvent event) {
        if (GameDatabase.isDevil()) {
            GameDatabase.setIsDevil(false);
        } else if (!GameDatabase.isDevil()) {
            GameDatabase.setIsDevil(true);
        }
        System.out.println(GameDatabase.isDevil());
    }
}
