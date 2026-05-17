package com.example.teststudentmanagementsystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserValidator {
    public static boolean validateLogin(String userInput, String passInput) throws SQLException {
        String sql = """
                SELECT username, password FROM userbase
                WHERE username = ?
                """;
        try (Connection conn = Database.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)){
            statement.setString(1, userInput);
            try (ResultSet rs = statement.executeQuery()){
                if (!rs.next()){
                        return false;
                    }
                String username = rs.getString("username");
                String password = rs.getString("password");
                if (!passInput.equals(password)){
                    return false;
                }
            }
        }
        return true;
    }
}
