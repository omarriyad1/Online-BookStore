package dao;

import model.Request;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RequestDao {
    // Create a new request
    public static void createRequest(Connection connection, Request request) {
        try {
            String query = "INSERT INTO Request (borrower_id, lender_id, book_id) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, request.getBorrowerId());
            preparedStatement.setInt(2, request.getLenderId());
            preparedStatement.setInt(3, request.getBookId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static Request getRequestById(int requestId, Connection connection) {
        Request request = null;
        try {
            String query = "SELECT * FROM Request WHERE request_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, requestId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                request = new Request();
                request.setRequestId(resultSet.getInt("request_id"));
                request.setBorrowerId(resultSet.getInt("borrower_id"));
                request.setLenderId(resultSet.getInt("lender_id"));
                request.setBookId(resultSet.getInt("book_id"));
                request.setStatus(resultSet.getString("status"));
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return request;
    }
    public static List<Request> getAllRequestsByLenderId(int lenderId, Connection connection) {
        List<Request> requests = new ArrayList<>();
        try {
            String query = "SELECT * FROM Request WHERE lender_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, lenderId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Request request = new Request();
                request.setRequestId(resultSet.getInt("request_id"));
                request.setBorrowerId(resultSet.getInt("borrower_id"));
                request.setLenderId(resultSet.getInt("lender_id"));
                request.setBookId(resultSet.getInt("book_id"));
                request.setStatus(resultSet.getString("status"));
                requests.add(request);
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requests;
    }

    // Retrieve all requests
    public static List<Request> getAllRequests(Connection connection) {
        List<Request> requests = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Request");
            while (resultSet.next()) {
                Request request = new Request();
                request.setRequestId(resultSet.getInt("request_id"));
                request.setBorrowerId(resultSet.getInt("borrower_id"));
                request.setLenderId(resultSet.getInt("lender_id"));
                request.setBookId(resultSet.getInt("book_id"));
                request.setStatus(resultSet.getString("status"));
                requests.add(request);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requests;
    }

    // Update request information
    public static void updateRequest(Connection connection, Request request) {
        try {
            String query = "UPDATE Request SET borrower_id=?, lender_id=?, book_id=?, status=? WHERE request_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, request.getBorrowerId());
            preparedStatement.setInt(2, request.getLenderId());
            preparedStatement.setInt(3, request.getBookId());
            preparedStatement.setString(4, request.getStatus());
            preparedStatement.setInt(5, request.getRequestId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete a request by ID
    public static void deleteRequestById(Connection connection, int requestId) {
        try {
            String query = "DELETE FROM Request WHERE request_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, requestId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
