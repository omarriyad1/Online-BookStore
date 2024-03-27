package server;
import dao.ChatDao;
import dao.UserDao;
import model.Chat;
import model.User;

import java.io.*;
import java.net.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

public class Server {
    private static final String url = "jdbc:mysql://127.0.0.1:3306/bookstore2_schema";
    private static final String username = "root";
    private static final String password = "Omar12345";

    // Helper method to establish database connection
    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    private static final int PORT = 5000;
    private static final Map<String, PrintWriter> clients = new HashMap<>();
    private static Connection databaseConnection;

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Server started. Listening on port " + PORT + "...");

            // Establish database connection
            databaseConnection = getConnection();

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket);

                // Create a new thread for each client
                new ClientHandler(clientSocket).start();
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    static class ClientHandler extends Thread {
        private Socket clientSocket;
        private PrintWriter out;
        private BufferedReader in;
        private String username;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        public void run() {
            try {
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                // Request username from the client
                out.println("Enter your msg:");
                username = in.readLine();
                System.out.println("User '" + username + "' connected.");

                // Add client to the clients map
                synchronized (clients) {
                    clients.put(username, out);
                }

                // Continuously read messages from the client
                // Continuously read messages from the client
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    if (inputLine.startsWith("@")) {
                        // Direct message
                        String recipient = inputLine.substring(1, inputLine.indexOf(" "));
                        String message = inputLine.substring(inputLine.indexOf(" ") + 1);
                        sendMessage(username, recipient, message);
                    } else {
                        // Broadcast message to all clients
                        sendMessageToAll(username + ": " + inputLine);
                        // Store the message in the database
                        storeChatMessage(username, null, inputLine);
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                // Remove client from the clients map and close resources
                if (username != null) {
                    synchronized (clients) {
                        clients.remove(username);
                    }
                }
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        // Send a message to a specific client
        public void sendMessage(String sender, String recipient, String message) {
            PrintWriter recipientWriter = clients.get(recipient);
            if (recipientWriter != null) {
                recipientWriter.println(sender + " (private): " + message);
                out.println("Message sent to " + recipient);
            } else {
                out.println("User '" + recipient + "' not found.");
            }
            // Store the message in the database
            storeChatMessage(sender, recipient, message);
        }

        // Broadcast a message to all clients
        private void sendMessageToAll(String message) {
            synchronized (clients) {
                for (PrintWriter writer : clients.values()) {
                    writer.println(message);
                }
            }
        }

        // Store a chat message in the database
        private void storeChatMessage(String sender, String recipient, String message) {
            User s1 = UserDao.getUserByUsername(databaseConnection,sender);
            User s2 = UserDao.getUserByUsername(databaseConnection,recipient);
            Chat chat = new Chat(s1.getUserId(), s2.getUserId(), message);
            ChatDao.createChat(databaseConnection, chat);
        }
    }
}
