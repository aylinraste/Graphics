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
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class RegisterView {
    private Stage stage;
    @FXML
    public TextField registerUsername;
    @FXML
    public PasswordField registerPassword;
    @FXML
    private Label label1, label2;

    String username, password;

    public static void load(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("register-view.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void signUp(ActionEvent event) throws IOException {
        username = registerUsername.getText();
        password = registerPassword.getText();
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
        user = new User(username, password);
        UserDatabase.addUser(user);
//        UserDatabase.saveUsers();
        GameDatabase.setUser(user);
        Parent root = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("mainMenu-view.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void back(MouseEvent event) throws IOException{
        Parent root = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("login-view.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void skip(ActionEvent event) throws IOException {
        GameDatabase.setUser(new User("host", ""));
        Parent root = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("mainMenu-view.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
