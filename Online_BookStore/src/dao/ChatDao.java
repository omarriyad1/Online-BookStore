package dao;

import model.Chat;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChatDao {
    // Create a new chat message
    public static void createChat(Connection connection, Chat chat) {
        try {
            String query = "INSERT INTO Chat ( sender_id, reciever_id, message) VALUES ( ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, chat.getSenderId());
            preparedStatement.setInt(2, chat.getReceiverId());
            preparedStatement.setString(3, chat.getMessage());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Retrieve all chat messages for a given request ID
    public static List<Chat> getChatByrecieverId(Connection connection, int  recieverid) {
        List<Chat> chatList = new ArrayList<>();
        try {
            String query = "SELECT * FROM Chat WHERE reciever_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, recieverid);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Chat chat = new Chat();
                chat.setChatId(resultSet.getInt("chat_id"));
                chat.setSenderId(resultSet.getInt("sender_id"));
                chat.setReceiverId(resultSet.getInt("reciever_id"));
                chat.setMessage(resultSet.getString("message"));
                chatList.add(chat);
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return chatList;
    }
    // Check if there is a chat between two users
    public static boolean hasChatBetweenUsers(Connection connection, int user1Id, int user2Id) {
        try {
            String query = "SELECT COUNT(*) FROM Chat WHERE (sender_id = ? AND reciever_id = ?) OR (sender_id = ? AND reciever_id = ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, user1Id);
            preparedStatement.setInt(2, user2Id);
            preparedStatement.setInt(3, user2Id);
            preparedStatement.setInt(4, user1Id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

        // Update chat message where sender_id and receiver_id match certain criteria
        public static void updateChatt(Connection connection, int senderId, int receiverId, String newMessage) {
            try {
                String query = "UPDATE Chat SET message = ? WHERE sender_id = ? AND reciever_id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, newMessage);
                preparedStatement.setInt(2, senderId);
                preparedStatement.setInt(3, receiverId);
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Chat message sent successfully.");
                } else {
                    System.out.println("No chat message found matching the criteria.");
                }
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


}
