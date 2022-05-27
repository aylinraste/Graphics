package com.example.hw3.View;

import com.example.hw3.Enum.Commands;
import com.example.hw3.HelloApplication;
import com.example.hw3.Model.GameDatabase;
import com.example.hw3.Model.User;
import com.example.hw3.Model.UserDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ProfileMenuView {
    private Stage stage;
    @FXML
    public TextField usernameField = new TextField();
    @FXML
    public PasswordField passwordField = new PasswordField();
    @FXML
    public AnchorPane myPane;
    @FXML
    private Label label;

    String username, password;

    public static void load(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("profileMenu-view.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void changeUsername(ActionEvent event) throws IOException {
        usernameField = new TextField();
        usernameField.setPromptText("username");
        usernameField.setLayoutX(40);
        usernameField.setLayoutY(180);
        myPane.getChildren().add(usernameField);
    }

    public void back(MouseEvent event) throws IOException{
        MainMenuView.load((Stage) ((Node) event.getSource()).getScene().getWindow());
    }

    public void logout(ActionEvent event) throws IOException {
        GameDatabase.setUser(null);
        LoginView.load((Stage) ((Node) event.getSource()).getScene().getWindow());
    }

    public void changePassword(ActionEvent event) {
        passwordField = new PasswordField();
        passwordField.setPromptText("password");
        passwordField.setLayoutX(40);
        passwordField.setLayoutY(80);
        myPane.getChildren().add(passwordField);
    }

    public void deleteAccount(ActionEvent event) throws IOException {
        UserDatabase.getAllUsers().remove(GameDatabase.getUser());
        logout(event);
    }

    public void ok(ActionEvent event) {
        username = usernameField.getText();
        password = passwordField.getText();
        User user;
        if (!username.equals("") && Commands.getMatcher(username, Commands.USERNAMEFORMATREGEX) == null) {
            label.setText("ENTER VALID USERNAME!");
            label.setTextFill(Color.rgb(210, 39, 30));
            return;
        }
        else {
            label.setText("");
        }
        if (!password.equals("") && Commands.getMatcher(password, Commands.PASSWORDFORMATREGEX) == null) {
            label.setText("ENTER VALID PASSWORD!");
            label.setTextFill(Color.rgb(210, 39, 30));
            return;
        }
        else {
            label.setText("");
        }
        if ((user = UserDatabase.findUserByUsername(username)) != null) {
            label.setText("THIS USERNAME IS USED!");
            label.setTextFill(Color.rgb(210, 39, 30));
            return;
        }
        else {
            label.setText("");
        }
        if (!username.equals("")){
            GameDatabase.getUser().setName(username);
        }
        if (!password.equals("")){
            GameDatabase.getUser().setPassword(password);
        }
        label.setText("SAVED!");
        label.setTextFill(Color.rgb(210, 39, 30));
    }
}
