package service;

import dao.ChatDao;
import model.Chat;
import java.sql.Connection;
import java.util.List;

public class ChatService {
    // Create a new chat message
    public static void createChat(Chat chat, Connection connection) {
        ChatDao.createChat(connection, chat);
    }

    // Retrieve all chat messages for a given request
    public static List<Chat> getChatByRequestId(int requestId, Connection connection) {
        return ChatDao.getChatByrecieverId(connection, requestId);
    }

    // Update chat information (Not implemented as it's typically not needed for chat messages)
    // Delete a chat message by ID (Not typically used for chat messages)
}
