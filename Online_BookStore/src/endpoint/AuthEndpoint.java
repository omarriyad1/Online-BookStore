package endpoint;

import model.User;
import service.UserService;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class AuthEndpoint {

    private static final String url = "jdbc:mysql://127.0.0.1:3306/bookstore2_schema";
    private static final String username = "root";
    private static final String password = "Omar12345";

    // Helper method to establish database connection
    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    public static String login(User user) {
        try (Connection connection = getConnection()) {
            // Retrieve user from the database
            User existingUser = UserService.getUserByUsername(user.getUsername(), connection);
            if (existingUser != null && existingUser.getPassword().equals(user.getPassword())) {
                String kk= user.getUsername();
                System.out.println("hi "+kk);
                // Return the token in the response
                //Response res= Response.ok().build();
                return kk ;
            } else {

                System.out.println("Invalid username or password");
                String kk= "Invalid username or password";
               // Response res= Response.status(Response.Status.UNAUTHORIZED).build();
                return kk;
            }
        } catch (SQLException e) {
            //Response res=Response.serverError().entity("Internal server error").build();
        }
        String kk = " there is smth wrong";
        return kk;
    }



    public static String register(User user) {
        try (Connection connection = getConnection()) {
            // Check if the username is available
            if (!UserService.isUsernameAvailable(user.getUsername(), connection)) {
                System.out.println("Username already reserved");
                String re="Username already reserved";
              //  Response res= Response.status(Response.Status.CONFLICT).build();
                return re;
            }
            // Create a new user
            UserService.createUser(user, connection);
            System.out.println("User registered successfully");
            String rr="User registered successfully";
            //Response res= Response.status(Response.Status.CREATED).build();
        } catch (SQLException e) {
          //  Response res= Response.serverError().entity("Internal server error").build();
           String kk= "User registered successfully";
            return kk;
        }
        String kk= "there is smth wrong";
        return kk;
    }


}
