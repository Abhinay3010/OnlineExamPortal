package service;

import db.DBConnection;

import java.sql.*;
import java.util.ArrayList;

public class ResultService {

    public boolean saveResult(String username, int score, int total) {
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO results (student_username, score, total) VALUES (?, ?, ?)"
            );
            ps.setString(1, username);
            ps.setInt(2, score);
            ps.setInt(3, total);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public ArrayList<String> getResultsByStudent(String username) {
        ArrayList<String> results = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM results WHERE student_username = ?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                results.add("Score: " + rs.getInt("score") + "/" + rs.getInt("total")
                    + " | Date: " + rs.getTimestamp("test_date"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }

    public ArrayList<String> getAllResults() {
        ArrayList<String> results = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM results");
            while (rs.next()) {
                results.add("Student: " + rs.getString("student_username")
                    + " | Score: " + rs.getInt("score") + "/" + rs.getInt("total")
                    + " | Date: " + rs.getTimestamp("test_date"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }
    
}