package com.example.teststudentmanagementsystem;

import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentSpawner {
    public static void samplePopulateStudents(ObservableList<Student> studentList){
        studentList.add(new Student("Juan", "Dela Cruz", 20, 1 , "placeholder@email.com"));
        studentList.add(new Student("Juan", "Daliuag The Great", 20, 2 , "placeholder@email.com"));
        studentList.add(new Student("John", "Doe", 19, 2 , "placeholder@email.com"));
    }
    public static void getAllStudentData(ObservableList<Student> studentList){
        try {
            String sql = """
                    SELECT id, first_name, last_name, age, email from STUDENTS
                    """;
            try (Connection conn = Database.getConnection();
                 PreparedStatement statement = conn.prepareStatement(sql);
                 ResultSet rs = statement.executeQuery()){
                while (rs.next()){
                    int id = rs.getInt("id");
                    String firstName = rs.getString("first_name");
                    String lastName = rs.getString("last_name");
                    int age = rs.getInt("age");
                    String email = rs.getString("email");
                    studentList.add(new Student(firstName, lastName, age, id, email));
                }
            }
        } catch (SQLException e){

        }
    }
}
