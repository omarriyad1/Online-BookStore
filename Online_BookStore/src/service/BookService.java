package service;

import dao.BookDao;
import model.Book;
import java.sql.Connection;
import java.util.List;

public class BookService {
    // Create a new book
    public static void createBook(Book book, Connection connection) {
        BookDao.createBook(connection, book);
    }

    // Retrieve all books
    public static List<Book> getAllBooks(Connection connection) {
        return BookDao.getAllBooks(connection);
    }

    // Update book information
    public static void updateBook(Book book, Connection connection) {
        BookDao.updateBook(connection, book);
    }

    // Delete a book by ID
    public static void deleteBookById(int bookId, Connection connection) {
        BookDao.deleteBookById(connection, bookId);
    }
}
