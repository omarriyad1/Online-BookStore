package client;

import endpoint.AuthEndpoint;
import homePage.homePage;
import model.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import endpoint.AuthEndpoint;
import model.User;
import homePage.homePage;
import server.Server;
import java.io.PrintWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.util.Scanner;
public class Client {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 5000;

    public static void main(String[] args) {
        try {
            Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            System.out.println("Connected to server.");

            // Setup input and output streams for communication
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

            // Read username from user
            System.out.print("Enter your username: ");
            String username = userInput.readLine();
            out.println(username);

            Scanner scanner = new Scanner(System.in);

            System.out.println("Welcome to the Online Bookstore!");

            boolean exit = false;
            while (!exit) {
                System.out.println("Select an option:");
                System.out.println("1. Login");
                System.out.println("2. Register");
                System.out.println("3. Exit");
                System.out.println("------------------------");

                int option = scanner.nextInt();
                scanner.nextLine(); // Consume newline character

                switch (option) {
                    case 1:
                        login(scanner);
                        break;
                    case 2:
                        register(scanner);
                        break;
                    case 3:
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }

            // Close scanner to prevent resource leak
            scanner.close();



            /*
        } catch (UnknownHostException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        // Start a separate thread to handle messages from the server
            new Thread(() -> {
                String messageFromServer;
                try {
                    while ((messageFromServer = in.readLine()) != null) {
                        System.out.println(messageFromServer);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
*/




            // Read user input and send messages to server
            String userInputLine;
            while ((userInputLine = userInput.readLine()) != null) {
                out.println(userInputLine);
            }

            // Close resources
            out.close();
            in.close();
            userInput.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void login(Scanner scanner) {
        // Prompt the user for login credentials
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        // Check if any of the input strings are empty
        if (username.isEmpty() || password.isEmpty()) {
            System.out.println("Username and password are required. Please try again.");
            return;
        }

        User user = new User(username, password);
        // Call the login method with the provided credentials
        AuthEndpoint.login(user);
        HomePage(user);
    }
    private static void register(Scanner scanner) {
        // Prompt the user for user details
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        // Check if any of the input strings are empty
        if (username.isEmpty() || name.isEmpty() || password.isEmpty()) {
            System.out.println("All fields are required. Please try again.");
            return;
        }

        // Create a User object
        User user = new User(username, password,name);

        // Call the register method with the User object
        AuthEndpoint.register(user);
    }

    public static void HomePage(User u){


        Scanner scanner = new Scanner(System.in);
        boolean logout = false;

        while (!logout) {
            System.out.println("Select an option:");
            System.out.println("1. browse");
            System.out.println("2. Search");
            System.out.println("3. Lend a Book");
            System.out.println("4. return a Book");
            System.out.println("5. view pending requests");
            System.out.println("6. chat with friend");
            System.out.println("7. view History");
            System.out.println("8. view All History");
            System.out.println(". logout");
            System.out.println("------------------------");

            if (scanner.hasNextInt()) {
                int option = scanner.nextInt();
                scanner.nextLine(); // Consume newline character

                if (option == 1) {
                    homePage.browse(scanner,u.getUsername());
                } else if (option == 2) {
                    homePage.search(scanner,u.getUsername());
//
//                   } else if (option == 3) {
//                        startServer(); // Start the server
//
                }
                else if (option == 3) {
                    homePage.lend(scanner,u.getUsername());

                }
                else if (option == 4) {
                    homePage.returnBook(scanner,u.getUsername());

                }
                else if (option == 5) {
                    homePage.notification(scanner,u.getUsername());

                }
                else if (option == 6) {
                    homePage.Chatt(scanner,u.getUsername());


                }
                else if (option == 7) {
                    homePage.History(scanner, u.getUsername());

                }
                else if (option == 8) {
                    homePage.viewAllHistory(scanner, u.getUsername());

                }
                else if (option == 9) {
                    logout = true;
                    System.out.println("goodbye " + u.getUsername());
                    System.out.println("------------------------");

                } else {
                    System.out.println("Invalid option. Please try again.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Consume invalid input
            }
        }
}}
