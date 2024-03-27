package dao;

import model.Book;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDao {
    // Create a new book
    public static void createBook(Connection connection, Book book) {
        try {
            String query = "INSERT INTO Book (title, author, genre, price, quantity) VALUES (?, ?, ?, ?, ? )";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setString(3, book.getGenre());
            preparedStatement.setDouble(4, book.getPrice());
            preparedStatement.setInt(5, book.getQuantity());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Retrieve all books
    public static List<Book> getAllBooks(Connection connection) {
        List<Book> books = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Book");
            while (resultSet.next()) {
                Book book = new Book();
                book.setBookId(resultSet.getInt("book_id"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setGenre(resultSet.getString("genre"));
                book.setPrice(resultSet.getDouble("price"));
                book.setQuantity(resultSet.getInt("quantity"));
                book.setLenderId(resultSet.getInt("lender_id"));
                books.add(book);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    // Update book information
    public static void updateBook(Connection connection, Book book) {
        try {
            String query = "UPDATE Book SET title=?, author=?, genre=?, price=?, quantity=?, lender_id=? WHERE book_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setString(3, book.getGenre());
            preparedStatement.setDouble(4, book.getPrice());
            preparedStatement.setInt(5, book.getQuantity());
            preparedStatement.setInt(6, book.getLenderId());
            preparedStatement.setInt(7, book.getBookId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete a book by ID
    public static void deleteBookById(Connection connection, int bookId) {
        try {
            String query = "DELETE FROM Book WHERE book_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, bookId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Book> findBookByTitle(String title, Connection connection) throws SQLException {
        List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM Book WHERE title LIKE ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, "%" + title + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Book book = new Book();
                book.setBookId(resultSet.getInt("book_id"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setGenre(resultSet.getString("genre"));
                book.setPrice(resultSet.getDouble("price"));
                book.setQuantity(resultSet.getInt("quantity"));
                book.setLenderId(resultSet.getInt("lender_id"));
                books.add(book);
            }
        }
        return books;
    }
    public static Book findBookByTitlee(String title, Connection connection) throws SQLException {
        Book book = null;
        String query = "SELECT * FROM Book WHERE title LIKE ? LIMIT 1"; // Limit the result to 1
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, "%" + title + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                book = new Book();
                book.setBookId(resultSet.getInt("book_id"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setGenre(resultSet.getString("genre"));
                book.setPrice(resultSet.getDouble("price"));
                book.setQuantity(resultSet.getInt("quantity"));
                book.setLenderId(resultSet.getInt("lender_id"));
            }
        }
        return book;
    }

    public static List<Book> findBooksByCriteria(int searchCriteria, String keyword, Connection connection) throws SQLException {
        List<Book> books = new ArrayList<>();
        String column;
        switch (searchCriteria) {
            case 1:
                column = "title";
                break;
            case 2:
                column = "author";
                break;
            case 3:
                column = "genre";
                break;
            default:
                throw new IllegalArgumentException("Invalid search criteria.");
        }
        String query = "SELECT * FROM Book WHERE " + column + " LIKE ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, "%" + keyword + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Book book = new Book();
                book.setBookId(resultSet.getInt("book_id"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setGenre(resultSet.getString("genre"));
                book.setPrice(resultSet.getDouble("price"));
                book.setQuantity(resultSet.getInt("quantity"));
                book.setLenderId(resultSet.getInt("lender_id"));
                books.add(book);
            }
        }
        return books;
    }
    public static Book getBookById(int bookId, Connection connection) {
        Book book = null;
        try {
            String query = "SELECT * FROM Book WHERE book_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, bookId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                book = new Book();
                book.setBookId(resultSet.getInt("book_id"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setGenre(resultSet.getString("genre"));
                book.setPrice(resultSet.getDouble("price"));
                book.setQuantity(resultSet.getInt("quantity"));
                book.setLenderId(resultSet.getInt("lender_id"));
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

}
