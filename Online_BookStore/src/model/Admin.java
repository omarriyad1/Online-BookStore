package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Admin {
    private int adminId;
    private String username;
    private String password;
    private String name;

    // Constructors
    public Admin() {
    }
    public Admin( String username, String password, String name) {

        this.username = username;
        this.password = password;
        this.name = name;
    }
    public Admin(int adminId, String username, String password, String name) {
        this.adminId = adminId;
        this.username = username;
        this.password = password;
        this.name = name;
    }

    // Getters
    public int getAdminId() {
        return adminId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    // Setters
    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    // CRUD operations

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
            String query = "SELECT * FROM Admin";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Admin admin = new Admin();
                admin.setAdminId(resultSet.getInt("admin_id"));
                admin.setUsername(resultSet.getString("username"));
                admin.setPassword(resultSet.getString("password"));
                admin.setName(resultSet.getString("name"));
                admins.add(admin);
            }
            resultSet.close();
            preparedStatement.close();
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

    // Override toString method for easy printing
    @Override
    public String toString() {
        return "Admin{" +
                "adminId=" + adminId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
