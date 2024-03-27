package dao;

import model.RequestHistory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;

public class RequestHistoryDao {
    // Create a new request history entry
    public static void createRequestHistory(Connection connection, RequestHistory requestHistory) {
        try {
            String query = "INSERT INTO RequestHistory (borrower_id, lender_id, book_id,  status) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, requestHistory.getBorrowerId());
            preparedStatement.setInt(2, requestHistory.getLenderId());
            preparedStatement.setInt(3, requestHistory.getBookId());
            preparedStatement.setString(4, requestHistory.getStatus());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static List<RequestHistory> getAllRequestHistory(Connection connection) {
        List<RequestHistory> requestHistoryList = new ArrayList<>();
        try {
            String query = "SELECT * FROM RequestHistory";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                RequestHistory requestHistory = new RequestHistory();
                requestHistory.setBorrowerId(resultSet.getInt("borrower_id"));
                requestHistory.setLenderId(resultSet.getInt("lender_id"));
                requestHistory.setBookId(resultSet.getInt("book_id"));
                requestHistory.setStatus(resultSet.getString("status"));
                requestHistoryList.add(requestHistory);
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requestHistoryList;
    }
    // Retrieve all request history entries for a given user
    public static List<RequestHistory> getRequestHistoryByUserId(Connection connection, int userId) {
        List<RequestHistory> requestHistoryList = new ArrayList<>();
        try {
            String query = "SELECT * FROM RequestHistory WHERE borrower_id = ? OR lender_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                RequestHistory requestHistory = new RequestHistory();
                requestHistory.setBorrowerId(resultSet.getInt("borrower_id"));
                requestHistory.setLenderId(resultSet.getInt("lender_id"));
                requestHistory.setBookId(resultSet.getInt("book_id"));
                requestHistory.setStatus(resultSet.getString("status"));
                requestHistoryList.add(requestHistory);
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requestHistoryList;
    }
}
