package model;

import java.util.Date;

public class RequestHistory {
    private int requestId;
    private int borrowerId;
    private int lenderId;
    private int bookId;

    private String status;

    // Constructors
    public RequestHistory() {
    }
    public RequestHistory(int borrowerId, int lenderId, int bookId, String status) {

        this.borrowerId = borrowerId;
        this.lenderId = lenderId;
        this.bookId = bookId;

        this.status = status;
    }

    // Getters

    public int getBorrowerId() {
        return borrowerId;
    }

    public int getLenderId() {
        return lenderId;
    }

    public int getBookId() {
        return bookId;
    }



    public String getStatus() {
        return status;
    }

    // Setters


    public void setBorrowerId(int borrowerId) {
        this.borrowerId = borrowerId;
    }

    public void setLenderId(int lenderId) {
        this.lenderId = lenderId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }



    public void setStatus(String status) {
        this.status = status;
    }

    // Override toString method for easy printing
    @Override
    public String toString() {
        return "RequestHistory{" +
                ", borrowerId=" + borrowerId +
                ", lenderId=" + lenderId +
                ", bookId=" + bookId +
                ", status='" + status + '\'' +
                '}';
    }
}
