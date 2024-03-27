package service;

import dao.UserDao;
import model.User;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserService {
    // Create a new user
    public static void createUser(User user, Connection connection) {
        UserDao.createUser(connection, user);
    }

    // Retrieve all users
    public static List<User> getAllUsers(Connection connection) {
        return UserDao.getAllUsers(connection);
    }

    // Update user information
    public static void updateUser(User user, Connection connection) {
        UserDao.updateUser(connection, user);
    }

    // Delete a user by ID
    public static void deleteUserById(int userId, Connection connection) {
        UserDao.deleteUserById(connection, userId);
    }

    // Retrieve user by username
    public static User getUserByUsername(String username, Connection connection) {
        return UserDao.getUserByUsername(connection, username);
    }

    // Check if username is available
    public static boolean isUsernameAvailable(String username, Connection connection) {
        return UserDao.getUserByUsername(connection, username) == null;
    }
}
