module com.example.teststudentmanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;

    opens com.example.teststudentmanagementsystem to javafx.fxml;
    exports com.example.teststudentmanagementsystem;
}