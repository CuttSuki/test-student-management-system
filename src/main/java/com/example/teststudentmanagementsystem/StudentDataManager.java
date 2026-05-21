package com.example.teststudentmanagementsystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDataManager {
    public static void insertStudent(String firstName, String lastName, String email, int age) throws SQLException {
        String sql = """
                INSERT INTO students (first_name, last_name, age, email) VALUES(?,?,?,?)
                """;
        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setInt(3, age);
            pstmt.setString(4, email);
            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0){
                System.out.println("Inserted " + rowsInserted + " rows.");
            }
        }
    }
    public static int getStudentCount() {
        String sql = """
                SELECT count(*) as total_count from students
                """;
        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()){
                if (rs.next()){
                    return rs.getInt("total_count");
                }

        } catch (SQLException e) {
            System.out.println("Error counting student data: " + e.getMessage());
        }
        return 0;
    }
}
