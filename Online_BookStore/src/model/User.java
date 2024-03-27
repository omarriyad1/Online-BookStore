package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class User {
    private int userId;
    private String username;
    private String password;
    private String name;
    private String role;

    // Constructors
    public User() {
    }
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    // Constructor with required parameters
    public User(String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;

    }
    // Constructor with parameters
    public User(int userId, String username, String password, String name) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.name = name;

    }
    // Getters and setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    // CRUD operations

    // Create a new user
    public static void createUser(Connection connection, User user) {
        try {
            String query = "INSERT INTO User (username, password, name) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getName());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Retrieve all users
    public static List<User> getAllUsers(Connection connection) {
        List<User> users = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM User");
            while (resultSet.next()) {
                User user = new User();
                user.setUserId(resultSet.getInt("user_id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setName(resultSet.getString("name"));

                users.add(user);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    // Update user information
    public static void updateUser(Connection connection, User user) {
        try {
            String query = "UPDATE User SET username=?, password=?, name=?,WHERE user_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getName());

            preparedStatement.setInt(5, user.getUserId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete a user by ID
    public static void deleteUserById(Connection connection, int userId) {
        try {
            String query = "DELETE FROM User WHERE user_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
