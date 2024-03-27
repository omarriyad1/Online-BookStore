package service;

import dao.RequestHistoryDao;
import model.RequestHistory;
import java.sql.Connection;
import java.util.List;

public class RequestHistoryService {
    // Create a new request history entry
    public static void createRequestHistory(RequestHistory requestHistory, Connection connection) {
        RequestHistoryDao.createRequestHistory(connection, requestHistory);
    }

    // Retrieve all request history entries for a given user
    public static List<RequestHistory> getRequestHistoryByUserId(int userId, Connection connection) {
        return RequestHistoryDao.getRequestHistoryByUserId(connection, userId);
    }
}
