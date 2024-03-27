package service;

import dao.LibraryStatisticsDao;
import java.sql.Connection;

public class LibraryStatisticsService {
    // Get count of borrowed books
    public static int getBorrowedBooksCount(Connection connection) {
        return LibraryStatisticsDao.getBorrowedBooksCount(connection);
    }

    // Get count of available books
    public static int getAvailableBooksCount(Connection connection) {
        return LibraryStatisticsDao.getAvailableBooksCount(connection);
    }

    // Get count of pending requests
    public static int getPendingRequestsCount(Connection connection) {
        return LibraryStatisticsDao.getPendingRequestsCount(connection);
    }

    // Get count of rejected requests
    public static int getRejectedRequestsCount(Connection connection) {
        return LibraryStatisticsDao.getRejectedRequestsCount(connection);
    }
}
