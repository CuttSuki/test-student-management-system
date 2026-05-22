package com.example.teststudentmanagementsystem;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StudentViewSceneCreator {
    public static void createStudentViewScene(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StudentLoginApplication.class.getResource("student-view-database.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Student Database Management System");
        stage.setScene(scene);
        stage.show();
    }
}
