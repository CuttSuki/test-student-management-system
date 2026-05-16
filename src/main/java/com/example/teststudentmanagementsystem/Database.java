package com.example.teststudentmanagementsystem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
public class Database {
    private static final String URL = "jdbc:postgresql://aws-1-ap-southeast-2.pooler.supabase.com:6543/postgres";
    private static final String USER = "postgres.kcbgfizplkmgppkoyihl";
    private static final String PASS = "@Cutterion123";
    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(URL, USER, PASS);
    }

    public static void testConnection() {
        String sql = "SELECT VERSION() AS current_version";
        try(Connection conn = getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);
        ResultSet rs = statement.executeQuery()){
            if (rs.next()){
                System.out.println(rs.getString("current_version"));
            }
        } catch (SQLException e){
            System.err.println("Connection failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
