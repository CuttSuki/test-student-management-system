package com.example.teststudentmanagementsystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentQuery {
    public static void insertStudent(String firstName, String lastName, String email, int age) throws SQLException {
        String sql = """
                INSERT INTO students (first_name, last_name, age, email) VALUES(?,?,?,?)
                """;
        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            
        }
    }
}
