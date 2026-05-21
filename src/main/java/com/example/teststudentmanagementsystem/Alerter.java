package com.example.teststudentmanagementsystem;

import javafx.scene.control.Alert;

public class Alerter {
    public static void sendAlert(Alert.AlertType alertType, String header, String message){
        Alert alert = new Alert(alertType);
        alert.setTitle("Alert!");
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
