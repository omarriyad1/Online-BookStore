package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LibraryStatisticsDao {
    // Retrieve overall library statistics
    public static int getBorrowedBooksCount(Connection connection) {
        int borrowedBooksCount = 0;
        try {
            String query = "SELECT COUNT(*) FROM Request WHERE status = 'accepted'";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                borrowedBooksCount = resultSet.getInt(1);
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return borrowedBooksCount;
    }

    public static int getAvailableBooksCount(Connection connection) {
        int availableBooksCount = 0;
        try {
            String query = "SELECT SUM(quantity) FROM Book";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                availableBooksCount = resultSet.getInt(1);
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return availableBooksCount;
    }

    public static int getPendingRequestsCount(Connection connection) {
        int pendingRequestsCount = 0;
        try {
            String query = "SELECT COUNT(*) FROM Request WHERE status = 'pending'";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                pendingRequestsCount = resultSet.getInt(1);
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pendingRequestsCount;
    }

    public static int getRejectedRequestsCount(Connection connection) {
        int rejectedRequestsCount = 0;
        try {
            String query = "SELECT COUNT(*) FROM Request WHERE status = 'rejected'";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                rejectedRequestsCount = resultSet.getInt(1);
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rejectedRequestsCount;
    }
}
