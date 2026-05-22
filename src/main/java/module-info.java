module com.example.teststudentmanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires io.github.cdimascio.dotenv.java;

    opens com.example.teststudentmanagementsystem to javafx.fxml;
    exports com.example.teststudentmanagementsystem;
}