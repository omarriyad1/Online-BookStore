package homePage;
import dao.*;
import model.*;
import service.BookService;
import endpoint.AuthEndpoint;
import model.User;
import server.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Scanner;
import model.Book;
import service.BookService;
import service.UserService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static java.sql.DriverManager.getConnection;

public class homePage {
    private static final String url = "jdbc:mysql://127.0.0.1:3306/bookstore2_schema";
    private static final String username = "root";
    private static final String password = "Omar12345";

    // Helper method to establish database connection
    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
    public static void browse(Scanner scanner,String name)  {
        try (Connection connection = getConnection()) {
            List<Book> books = BookService.getAllBooks(connection);
            // Do something with the list of books
            for (Book book : books) {
                System.out.println(book); // Or whatever processing you want to do
            }
            System.out.println("------------------------");
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }
    public static void search(Scanner scanner,String name) {
        try (Connection connection = getConnection()) {
            System.out.println("Select search criteria:");
            System.out.println("1. Title");
            System.out.println("2. Author");
            System.out.println("3. Genre");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            String searchKeyword;
            switch (choice) {
                case 1:
                    System.out.print("Enter the title to search: ");
                    searchKeyword = scanner.nextLine();
                    break;
                case 2:
                    System.out.print("Enter the author to search: ");
                    searchKeyword = scanner.nextLine();
                    break;
                case 3:
                    System.out.print("Enter the genre to search: ");
                    searchKeyword = scanner.nextLine();
                    break;
                default:
                    System.out.println("Invalid choice.");
                    return;
            }

            List<Book> books = BookDao.findBooksByCriteria(choice, searchKeyword, connection);
            if (books.isEmpty()) {
                System.out.println("No books found matching the search criteria.");
                System.out.println("------------------------");
            } else {
                System.out.println("Books found:");
                for (Book book : books) {
                    System.out.println(book); // Print each book
                }
                System.out.println("------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }

    public static void lend(Scanner scanner,String name)  {
        Book book;
        try (Connection connection = getConnection()) {
            System.out.print("Enter the title of the book to lend: ");
            String title = scanner.nextLine();

            // Find the book by title
            book = BookDao.findBookByTitlee(title, connection);
            if (book == null) {
                System.out.println("Book not found with title: " + title);
                System.out.println("------------------------");
                return;
            } else if (book.getLenderId() != 0) { // Check against 0 instead of null
                System.out.println("Book '" + title + "' is already lent.");
                User lenderr = UserDao.getUserById(connection,book.getLenderId());
                System.out.println("U Can Borrow "+title +" Book From "+lenderr.getUsername() );
                System.out.println("1. Help Me To Make a Request");
                System.out.println("2. Thanks");
                System.out.print("Enter your choice: ");
                int choicelen = scanner.nextInt();
                if (choicelen == 1) {
                    User u = UserDao.getUserByUsername(connection, name);
                    if (u != null) {
                        int borrowerId = u.getUserId();
                        int lenderId = book.getLenderId();
                        Request r = new Request(borrowerId, lenderId, book.getBookId());
                        RequestDao.createRequest(connection, r);
                        System.out.println("Request has been sent successfully");
                        System.out.println("------------------------");
                        // Handle the request submission
                    }else {

                    System.out.println("User not found with username: " + name);}
                    System.out.println("------------------------");
                }
                else
                {
                    return;
                }

                return;
            }

            User u = UserDao.getUserByUsername(connection,name);

                book.setLenderId(u.getUserId());
                BookDao.updateBook(connection, book);
        System.out.println("Book '" + title + "' has been lent successfully.");
            System.out.println("------------------------");
    } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }
    public static void notification(Scanner scanner,String name) {
        try (Connection connection = getConnection()) {
            // Get the current user's ID or fetch it from user input
            User y = UserDao.getUserByUsername(connection,name);

            // Retrieve all requests for the current user (lender)
            List<Request> requests = RequestDao.getAllRequestsByLenderId(y.getUserId(), connection);

            // Print the requests
            if (requests.isEmpty()) {
                System.out.println("You have no pending requests.");
                System.out.println("------------------------");
            } else {
                System.out.println("Pending Requests:");
                for (Request request : requests) {
                    // Print details of each request
                    System.out.println("Request ID: " + request.getRequestId());
                    System.out.println("Borrower ID: " + request.getBorrowerId());
                    System.out.println("Book ID: " + request.getBookId());
                    System.out.println("Request Date: " + request.getRequestDate());
                    System.out.println("Status: " + request.getStatus());
                    System.out.println("------------------------");
                }

            System.out.println("Do you want to Accept or reject any request? ");
            System.out.println("1. Yes");
            System.out.println("2. Thanks");
            System.out.print("Enter your choice: ");
            int choicelen = scanner.nextInt();
            if (choicelen == 1) {
                System.out.print("Enter RequestID: ");
                int reqid = scanner.nextInt();
                Request RQ = RequestDao.getRequestById(reqid, connection);
                System.out.println("1. Accept ");
                System.out.println("2. Reject");
                System.out.print("Enter your choice: ");
                int cho = scanner.nextInt();
                if (cho == 1) {
                    String s = "accepted";
                    Book b =BookDao.getBookById(RQ.getBookId(),connection);
                    b.setLenderId(RQ.getBorrowerId());
                    BookDao.updateBook(connection,b);
                    RQ.setStatus(s);
                    Chat newchat1 = new Chat(RQ.getLenderId(), RQ.getBorrowerId());
                    Chat newchat2 = new Chat(RQ.getBorrowerId(), RQ.getLenderId());
                    ChatDao.createChat(connection, newchat1);
                    ChatDao.createChat(connection, newchat2);
                    System.out.println("Accepted Request ");
                    System.out.println("Now You both can chat with each other");
                    RequestHistory RHH = new RequestHistory(RQ.getBorrowerId(), RQ.getLenderId(), RQ.getBookId(), RQ.getStatus());
                    RequestHistoryDao.createRequestHistory(connection, RHH);
                    RequestDao.deleteRequestById(connection, RQ.getRequestId());
                    System.out.println("------------------------");
                } else {

                    RQ.setStatus("rejected");
                    System.out.println("rejected Request ");
                    RequestHistory RHH = new RequestHistory(RQ.getBorrowerId(), RQ.getLenderId(), RQ.getBookId(), RQ.getStatus());
                    RequestHistoryDao.createRequestHistory(connection, RHH);
                    RequestDao.deleteRequestById(connection, RQ.getRequestId());
                    System.out.println("------------------------");
                }

             }}

        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }

    public static void returnBook(Scanner scanner,String name)  {
        try (Connection connection = getConnection()) {
            System.out.print("Enter the title of the book to return: ");
            String title = scanner.nextLine();

            // Find the book by title
            Book book = BookDao.findBookByTitlee(title, connection);
            if (book == null) {
                System.out.println("Book not found with title: " + title);
                System.out.println("------------------------");
                return;
            }

            if (book.getLenderId() == 0) { // Check if the book is not currently lent
                System.out.println("Book '" + title + "' is not currently lent.");
                System.out.println("------------------------");
                return;
            }
            int lenderId = book.getLenderId();
            if (!UserDao.isUserExists(lenderId, connection)) {
                System.out.println("Error: Lender ID " + lenderId + " does not exist in the user table.");
                System.out.println("------------------------");
                return;
            }
            // Reset the lender ID of the book to indicate it's returned to the library
            User ud = UserDao.getUserByUsername(connection,name);
            if(book.getLenderId() == ud.getUserId() )
            {
                book.setLenderId(0); // Assuming 0 represents null for lender ID
                BookDao.updateBook(connection, book);

                System.out.println("Book '" + title + "' has been returned successfully.");
                System.out.println("------------------------");
            }else {
                System.out.println("You haven't lent this book before.");
                System.out.println("------------------------");
            }


        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
}
    public static void History(Scanner scanner, String name) {
        try (Connection connection = getConnection()) {
            User u = UserDao.getUserByUsername(connection, name);
            List<RequestHistory> requestHistoryList = RequestHistoryDao.getRequestHistoryByUserId(connection, u.getUserId());

            // Print the request history information
            for (RequestHistory requestHistory : requestHistoryList) {
                System.out.println("Borrower ID: " + requestHistory.getBorrowerId());
                System.out.println("Lender ID: " + requestHistory.getLenderId());
                System.out.println("Book ID: " + requestHistory.getBookId());
                System.out.println("Status: " + requestHistory.getStatus());
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }
    public static void Chatt(Scanner scanner, String name) {
        try (Connection connection = getConnection()) {
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

            User u = UserDao.getUserByUsername(connection, name);
            List<Chat> chatList = ChatDao.getChatByrecieverId(connection,u.getUserId());
                for (Chat chat : chatList) {
                    System.out.println("Chat ID: " + chat.getChatId());
                    System.out.println("Sender ID: " + chat.getSenderId());
                    System.out.println("Receiver ID: " + chat.getReceiverId());
                    System.out.println("Message: " + chat.getMessage());
                    System.out.println();
                }
            System.out.println("do you want to chant? ");
            System.out.println("1. Yes");
            System.out.println("2. No");
            System.out.print("Enter your choice: ");
            int cho = scanner.nextInt();
            if (cho == 1)
            {
                System.out.print("Chat with (enter your friend’s id): ");
                int Fid = scanner.nextInt();
                if(ChatDao.hasChatBetweenUsers(connection,u.getUserId(),Fid)){
                    System.out.print("Enter your msg ");

                    String MSG = userInput.readLine();
                    ChatDao.updateChatt(connection,u.getUserId(), Fid, MSG);
                }
                else {
                    if (UserDao.isUserExists(Fid,connection)){
                        System.out.print("You cannot chat with him ");
                    }
                    else {
                        System.out.print("there is no user with this userid ");
                    }
                }

            }

        } catch (SQLException | IOException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }
    public static void viewAllHistory(Scanner scanner, String name) {
        try (Connection connection = getConnection()) {
            User u = UserDao.getUserByUsername(connection, name);
            List<RequestHistory> requestHistoryList = RequestHistoryDao.getAllRequestHistory(connection);

            // Print the request history information
            for (RequestHistory requestHistory : requestHistoryList) {
                System.out.println("Borrower ID: " + requestHistory.getBorrowerId());
                System.out.println("Lender ID: " + requestHistory.getLenderId());
                System.out.println("Book ID: " + requestHistory.getBookId());
                System.out.println("Status: " + requestHistory.getStatus());
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }

}








