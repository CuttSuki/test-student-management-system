package com.example.teststudentmanagementsystem;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController {

    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private Button loginButton;
    @FXML
    private void onLoginButtonClicked(ActionEvent e) throws SQLException, IOException {
        String usernameText = usernameField.getText();
        String passwordText = passwordField.getText();
        if (UserValidator.validateLogin(usernameText, passwordText)){
            System.out.println("Login sucesss!");
            Stage stage = (Stage) usernameField.getScene().getWindow();
            StudentViewSceneCreator.createStudentViewScene(stage);
        } else {
            System.out.println("Invalid credentials!");
        }
    }
}
