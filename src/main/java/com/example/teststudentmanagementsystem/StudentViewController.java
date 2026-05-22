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
import javafx.scene.layout.Pane;

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
    @FXML private TableColumn<Student, String> createdAtColumn;
    @FXML private TableColumn<Student, String> updatedAtColumn;
    @FXML private Button updateButton;
    @FXML private Button deleteButton;
    @FXML private Button confirmUpdateButton;
    @FXML private Button cancelUpdateButton;
    @FXML private Button confirmDeleteButton;
    @FXML private Button cancelDeleteButton;
    @FXML private TextField firstNameUpdatedTextField;
    @FXML private TextField lastNameUpdatedTextField;
    @FXML private TextField emailUpdatedTextField;
    @FXML private TextField ageUpdatedTextField;
    @FXML private Pane deletePane;
    @FXML private Pane updatePane;
    private ObservableList<Student> studentList = FXCollections.observableArrayList();
    private final int ROWS_PER_PAGE = 10;

    @FXML
    public void initialize(){
        updatePane.setVisible(false);
        anchorPane.setFocusTraversable(true);
        anchorPane.setOnMouseClicked(event -> {
            anchorPane.requestFocus();
        });
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        createdAtColumn.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
        updatedAtColumn.setCellValueFactory(new PropertyValueFactory<>("updatedAt"));
        StudentSpawner.getAllStudentData(studentList);
        tableView.setOnMouseClicked(event -> {
            System.out.println(tableView.getSelectionModel().getSelectedItem().getClass());
        });
        loadPagination();
    }

    @FXML
    private void onUpdateButtonClicked(ActionEvent e){
        Student studentEntry = tableView.getSelectionModel().getSelectedItem();
        if (studentEntry == null){
            Alerter.sendAlert(Alert.AlertType.ERROR, "No student entry found", "Please click a student row.");
        } else {
            updatePane.setVisible(true);
            updatePane.requestFocus();
            tableView.setDisable(true);
        }
    }
    @FXML
    private void onDeleteButtonClicked(ActionEvent e){
        if(tableView.getSelectionModel().getSelectedItem() == null){
            Alerter.sendAlert(Alert.AlertType.ERROR, "No student entry found", "Please click a student row.");
            return;
        }
        tableView.setDisable(true);
        deletePane.setVisible(true);
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
        if (!ageTextField.getText().matches("\\d++")){
            Alerter.sendAlert(Alert.AlertType.ERROR, "Invalid Input Type", "The age input field must be an integer!");
            return;
        }
        int age = Integer.parseInt(ageTextField.getText());
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

    @FXML
    private void onConfirmUpdateButtonClicked(ActionEvent e) throws SQLException{
        Student selectedStudent  = tableView.getSelectionModel().getSelectedItem();
        String firstNameUpdated = (!firstNameUpdatedTextField.getText().isEmpty()) ? firstNameUpdatedTextField.getText() : selectedStudent.getFirstName();
        String lastNameUpdated = (!lastNameUpdatedTextField.getText().isEmpty()) ? lastNameUpdatedTextField.getText() : selectedStudent.getLastName();
        String emailUpdated = (!emailUpdatedTextField.getText().isEmpty()) ? emailUpdatedTextField.getText() : selectedStudent.getEmail();
        String ageUpdated = (!ageUpdatedTextField.getText().isEmpty()) ? ageUpdatedTextField.getText() : Integer.toString(selectedStudent.getAge());

        if (!ageUpdated.matches("\\d++")){
            System.out.println("Conversion failed.");
            return;
        }
        int ageUpdatedInt = Integer.parseInt(ageUpdated);
        int id = selectedStudent.getId();
        StudentDataManager.updateStudent(firstNameUpdated, lastNameUpdated, emailUpdated, ageUpdatedInt, id);
        studentList.clear();
        StudentSpawner.getAllStudentData(studentList);
        loadPagination();
        updatePane.setVisible(false);
        tableView.setDisable(false);
    }

    @FXML
    private void onCancelUpdateButtonClicked(ActionEvent e){
        firstNameUpdatedTextField.clear();
        lastNameUpdatedTextField.clear();
        emailUpdatedTextField.clear();
        ageUpdatedTextField.clear();
        updatePane.setVisible(false);
        tableView.setDisable(false);
    }

    @FXML
    private void onConfirmDeleteButtonClicked() throws SQLException {
        Student selectedStudent = tableView.getSelectionModel().getSelectedItem();
        int id = selectedStudent.getId();
        StudentDataManager.deleteStudent(id);
        studentList.clear();
        StudentSpawner.getAllStudentData(studentList);
        loadPagination();
        deletePane.setVisible(false);
        tableView.setDisable(false);
    }
    @FXML private void onCancelDeleteButtonClicked(){
        deletePane.setVisible(false);
        tableView.setDisable(false);
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
