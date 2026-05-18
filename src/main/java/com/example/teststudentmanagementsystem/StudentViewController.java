package com.example.teststudentmanagementsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class StudentViewController {
    @FXML private AnchorPane anchorPane;
    @FXML private TableView<Student> tableView;
    @FXML private TextField firstNameTextField;
    @FXML private TextField lastNameTextField;
    @FXML private TextField emailTextField;
    @FXML private TextField ageTextField;
    @FXML private TableColumn<Student, Integer> idColumn;
    @FXML private TableColumn<Student, String> firstNameColumn;
    @FXML private TableColumn<Student, String> lastNameColumn;
    @FXML private TableColumn<Student, String> emailColumn;
    @FXML private TableColumn<Student, Integer> ageColumn;
    private ObservableList<Student> studentList = FXCollections.observableArrayList();
    @FXML
    public void initialize(){
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        StudentSpawner.getAllStudentData(studentList);
        tableView.setItems(studentList);
        tableView.refresh();
    }

    @FXML
    private void onAnchorPaneMouseClicked(MouseEvent e){
        if (e.getButton() == MouseButton.SECONDARY){
            System.out.println("RMB was clicked.");
        }
    }

    @FXML
    private void onInsertButtonClicked(ActionEvent e){
        String firstName = firstNameTextField.getText();
        String lastName = lastNameTextField.getText();
        String email = emailTextField.getText();
        int age = Integer.parseInt(ageTextField.getText());
        System.out.println(firstName + " " + lastName +"'s email: " + email );
        StudentQuery.insertStudent(firstName, lastName, email, age);
        studentList.clear();
        StudentSpawner.getAllStudentData(studentList);
        tableView.setItems(studentList);
        tableView.refresh();
    }
}
