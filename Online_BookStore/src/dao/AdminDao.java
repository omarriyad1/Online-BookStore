package dao;

import model.Admin;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDao {
    // Create a new admin
    public static void createAdmin(Connection connection, Admin admin) {
        try {
            String query = "INSERT INTO Admin (username, password, name) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, admin.getUsername());
            preparedStatement.setString(2, admin.getPassword());
            preparedStatement.setString(3, admin.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Retrieve all admins
    public static List<Admin> getAllAdmins(Connection connection) {
        List<Admin> admins = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Admin");
            while (resultSet.next()) {
                Admin admin = new Admin();
                admin.setAdminId(resultSet.getInt("admin_id"));
                admin.setUsername(resultSet.getString("username"));
                admin.setPassword(resultSet.getString("password"));
                admin.setName(resultSet.getString("name"));
                admins.add(admin);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admins;
    }

    // Update admin information
    public static void updateAdmin(Connection connection, Admin admin) {
        try {
            String query = "UPDATE Admin SET username=?, password=?, name=? WHERE admin_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, admin.getUsername());
            preparedStatement.setString(2, admin.getPassword());
            preparedStatement.setString(3, admin.getName());
            preparedStatement.setInt(4, admin.getAdminId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete an admin by ID
    public static void deleteAdminById(Connection connection, int adminId) {
        try {
            String query = "DELETE FROM Admin WHERE admin_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, adminId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
