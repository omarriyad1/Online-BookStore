package service;

import dao.RequestDao;
import model.Request;
import java.sql.Connection;
import java.util.List;

public class RequestService {
    // Create a new request
    public static void createRequest(Request request, Connection connection) {
        RequestDao.createRequest(connection, request);
    }

    // Retrieve all requests
    public static List<Request> getAllRequests(Connection connection) {
        return RequestDao.getAllRequests(connection);
    }

    // Update request information
    public static void updateRequest(Request request, Connection connection) {
        RequestDao.updateRequest(connection, request);
    }

    // Delete a request by ID
    public static void deleteRequestById(int requestId, Connection connection) {
        RequestDao.deleteRequestById(connection, requestId);
    }
}
