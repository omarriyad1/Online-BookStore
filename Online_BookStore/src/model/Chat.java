package model;

import java.util.Date;

public class Chat {
    private int chatId;
    private int requestId;
    private int senderId;
    private int receiverId;
    private String message;


    // Constructors
    public Chat() {
    }
    public Chat( int requestId, int senderId, int receiverId, String message) {

        this.requestId = requestId;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.message = message;

    }
    public Chat(  int senderId, int receiverId) {


        this.senderId = senderId;
        this.receiverId = receiverId;


    }
    public Chat(  int senderId, int receiverId, String message) {


        this.senderId = senderId;
        this.receiverId = receiverId;
        this.message = message;

    }

    public Chat(int chatId, int requestId, int senderId, int receiverId, String message) {
        this.chatId = chatId;
        this.requestId = requestId;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.message = message;

    }

    // Getters
    public int getChatId() {
        return chatId;
    }

    public int getSenderId() {
        return senderId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public String getMessage() {
        return message;
    }



    // Setters
    public void setChatId(int chatId) {
        this.chatId = chatId;
    }


    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public void setMessage(String message) {
        this.message = message;
    }



    // Override toString method for easy printing
    @Override
    public String toString() {
        return "Chat{" +
                "chatId=" + chatId +
                ", senderId=" + senderId +
                ", receiverId=" + receiverId +
                ", message='" + message +
                '}';
    }
}
