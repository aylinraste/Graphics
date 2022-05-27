package com.example.hw3.View;

import com.example.hw3.Enum.Commands;
import com.example.hw3.HelloApplication;
import com.example.hw3.Model.GameDatabase;
import com.example.hw3.Model.User;
import com.example.hw3.Model.UserDatabase;
import javafx.event.ActionEvent;
//import java.awt.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import  javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoginView implements Initializable{
    private Stage stage;
    @FXML
    private PasswordField loginPasswordField;
    @FXML
    private TextField loginTextField;
    @FXML
    private Label label1, label2;
    public Pane pane;
    String username, password;
    Parent root;

    public static void load(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("login-view.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void login(ActionEvent event) throws IOException {
        username = loginTextField.getText();
        password = loginPasswordField.getText();
        User user;
        if (username.equals("")) {
            label1.setText("ENTER YOUR USERNAME!");
            label1.setTextFill(Color.rgb(210, 39, 30));
            return;
        }
        else {
            label1.setText("");
        }
        if (password.equals("")) {
            label2.setText("ENTER YOUR PASSWORD!");
            label2.setTextFill(Color.rgb(210, 39, 30));
            return;
        }
        else {
            label2.setText("");
        }
        if (Commands.getMatcher(username, Commands.USERNAMEFORMATREGEX) == null) {
            label1.setText("ENTER VALID USERNAME!");
            label1.setTextFill(Color.rgb(210, 39, 30));
            return;
        }
        else {
            label1.setText("");
        }
        if (Commands.getMatcher(password, Commands.PASSWORDFORMATREGEX) == null) {
            label2.setText("ENTER VALID PASSWORD!");
            label2.setTextFill(Color.rgb(210, 39, 30));
            return;
        }
        else {
            label2.setText("");
        }
        if ((user = UserDatabase.findUserByUsername(username)) == null) {
            label1.setText("SIGN UP...");
            label1.setTextFill(Color.rgb(210, 39, 30));
            return;
        }
        else {
            label1.setText("");
        }
        if (!password.equals(Objects.requireNonNull(user).getPassword())) {
            label1.setText("NO MATCH!");
            label1.setTextFill(Color.rgb(210, 39, 30));
            return;
        }
        else {
            label1.setText("");
        }
        GameDatabase.setUser(user);
        MainMenuView.load((Stage) ((Node) event.getSource()).getScene().getWindow());
    }

    public void register(ActionEvent event) throws IOException {
        RegisterView.load((Stage) ((Node) event.getSource()).getScene().getWindow());
    }

    public void skip(ActionEvent event) throws IOException {
        GameDatabase.setUser(new User("host", ""));
        MainMenuView.load((Stage) ((Node) event.getSource()).getScene().getWindow());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (HelloApplication.getMediaPlayer() != null && !HelloApplication.getMediaPlayer().isMute() ) {
            HelloApplication.getMediaPlayer().pause();
        }
        if (HelloApplication.getMediaPlayer() == null || !HelloApplication.getMediaPlayer().isMute()) {
            MediaPlayer mediaPlayer = new MediaPlayer(new Media(HelloApplication.class.getResource("musics/1.mp3").toExternalForm()));
            MediaView mediaView = new MediaView(mediaPlayer);
            HelloApplication.setMediaPlayer(mediaPlayer);
            mediaPlayer.setAutoPlay(true);
            mediaPlayer.setCycleCount(-1);
            pane.getChildren().add(mediaView);
        }
    }
}
