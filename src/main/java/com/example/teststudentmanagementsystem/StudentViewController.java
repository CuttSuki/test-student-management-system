package com.example.teststudentmanagementsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.sql.SQLException;

public class StudentViewController {

    @FXML private AnchorPane anchorPane;
    @FXML private Pagination pagination;
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
    private final int ROWS_PER_PAGE = 10;

    @FXML
    public void initialize(){
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        StudentSpawner.getAllStudentData(studentList);
        loadPagination();
    }

    @FXML
    private void onAnchorPaneMouseClicked(MouseEvent e){
        if (e.getButton() == MouseButton.SECONDARY){
            System.out.println("RMB was clicked.");
        }
    }

    @FXML
    private void onInsertButtonClicked(ActionEvent e) throws SQLException {
        String firstName = firstNameTextField.getText();
        String lastName = lastNameTextField.getText();
        String email = emailTextField.getText();
        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || ageTextField.getText().isEmpty()){
            Alerter.sendAlert(Alert.AlertType.ERROR, "Missing Input Field", "Please fill out all input fields!");
            return;
        }
        int age = Integer.parseInt(ageTextField.getText());
        System.out.println(firstName + " " + lastName +"'s email: " + email );
        StudentDataManager.insertStudent(firstName, lastName, email, age);
        studentList.clear();
        StudentSpawner.getAllStudentData(studentList);
        loadPagination();
    }
    private void loadPagination(){
        int pageCount = (int) Math.ceil((double) StudentDataManager.getStudentCount()/ROWS_PER_PAGE);
        pagination.setPageCount(pageCount);
        pagination.setPageFactory(this::createPage);
    }

    private Node createPage(int pageIndex) {
        int fromIndex = pageIndex * ROWS_PER_PAGE;
        int toIndex   = Math.min(fromIndex + ROWS_PER_PAGE, StudentDataManager.getStudentCount());
        tableView.refresh();
        tableView.setItems(FXCollections.observableArrayList(
                studentList.subList(fromIndex, toIndex)
        ));
        tableView.refresh();

        // Return the already-injected tableView
        return new javafx.scene.layout.VBox();
    }
}
