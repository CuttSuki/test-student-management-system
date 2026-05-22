package com.example.teststudentmanagementsystem;

import io.github.cdimascio.dotenv.Dotenv;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StudentLoginApplication extends Application {
    public static Dotenv dotenv;
    @Override
    public void start(Stage stage) throws IOException {
        dotenv = Dotenv.load();
        FXMLLoader fxmlLoader = new FXMLLoader(StudentLoginApplication.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Student Database Management System");
        stage.setScene(scene);
        stage.show();
    }
}
