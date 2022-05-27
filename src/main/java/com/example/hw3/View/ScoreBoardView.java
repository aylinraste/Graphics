package com.example.hw3.View;

import com.example.hw3.HelloApplication;
import com.example.hw3.Model.Game;
import com.example.hw3.Model.GameDatabase;
import com.example.hw3.Model.User;
import com.example.hw3.Model.UserDatabase;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.control.Label;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class ScoreBoardView implements Initializable {
    private Stage stage;
    private ArrayList<Label> topUsers = new ArrayList<>();
    @FXML
    public Pane pane;

    public static void load(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("scoreBoard-view.fxml")));
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
        UserDatabase.sortUsers();
        User user = null;
        for (int i = 0; i <= UserDatabase.getAllUsers().size() && i <= 10; i++) {
            user = UserDatabase.getAllUsers().get(Math.max(0, i - 1));
            for (int j = 0; j < 4; j++) {
                Label label = new Label();
                if (i != 0) {
                    if (j == 0)
                        label = new Label(String.valueOf(i));
                    if (j == 1)
                        label = new Label(user.getName());
                    if (j == 2)
                        label = new Label(String.valueOf(user.getHighScore()));
                    if (j == 3)
                        label = new Label(String.valueOf(user.getTime()));
                }
                else {
                    if (j == 1)
                        label = new Label("name");
                    if (j == 2)
                        label = new Label("score");
                    if (j == 3)
                        label = new Label("time");
                }
                label.setLayoutX(20 + j * 150);
                label.setLayoutY(20 + i * 30);
                label.setPrefWidth(100);
                label.setPrefHeight(20);
                topUsers.add(label);
            }
            if (i > 0 && i < 4) {
                ImageView imageView = null;
                    imageView = new ImageView(new Image(HelloApplication.class.getResource("frames/icons/" + i + ".png").toExternalForm()));
                imageView.setFitWidth(20);
                imageView.setFitHeight(20);
                imageView.setX(550);
                imageView.setY(20 + i * 30);
                pane.getChildren().add(imageView);
            }
//            i++;
        }
        pane.getChildren().addAll(topUsers);
    }
}
