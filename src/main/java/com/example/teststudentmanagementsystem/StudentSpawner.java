package com.example.teststudentmanagementsystem;

import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class StudentSpawner {
    public static void samplePopulateStudents(ObservableList<Student> studentList){
        studentList.add(new Student("Juan", "Dela Cruz", 20, 1 , "placeholder@email.com", "", ""));
        studentList.add(new Student("Juan", "Daliuag The Great", 20, 2 , "placeholder@email.com", "", ""));
        studentList.add(new Student("John", "Doe", 19, 2 , "placeholder@email.com", "", ""));
    }
    public static void getAllStudentData(ObservableList<Student> studentList){
        try {
            String sql = """
                    SELECT id, first_name, last_name, age, email, created_at, updated_at from STUDENTS ORDER BY id
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
                    OffsetDateTime createdAt = rs.getObject("created_at", OffsetDateTime.class);
                    OffsetDateTime updatedAt = rs.getObject("updated_at", OffsetDateTime.class);
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    String formattedTime = createdAt.format(formatter);
                    String formattedUpdatedTime = updatedAt.format(formatter);
                    studentList.add(new Student(firstName, lastName, age, id, email, formattedTime, formattedUpdatedTime));
                }
            }
        } catch (SQLException e){
            System.out.println("Error getting student data: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void getSearchedStudents(ObservableList<Student>studentList, String searchBy, String searcher) throws SQLException{
        String sql = String.format("""
                SELECT id, first_name, last_name, email, age, created_at, updated_at from STUDENTS WHERE LOWER(%s) LIKE ? ORDER BY id
                """, searchBy);
        try(Connection conn = Database.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, searcher + "%");
            try (ResultSet rs = pstmt.executeQuery()){
                while (rs.next()){
                    System.out.println("Found");
                    int id = rs.getInt("id");
                    String firstName = rs.getString("first_name");
                    String lastName = rs.getString("last_name");
                    int age = rs.getInt("age");
                    String email = rs.getString("email");
                    OffsetDateTime createdAt = rs.getObject("created_at", OffsetDateTime.class);
                    OffsetDateTime updatedAt = rs.getObject("updated_at", OffsetDateTime.class);
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    String formattedTime = createdAt.format(formatter);
                    String formattedUpdatedTime = updatedAt.format(formatter);
                    studentList.add(new Student(firstName, lastName, age, id, email, formattedTime, formattedUpdatedTime));
                }
            }
        }
    }
}
