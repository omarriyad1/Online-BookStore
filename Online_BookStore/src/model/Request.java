package model;

import java.util.Date;

public class Request {
    private int requestId;
    private int borrowerId;
    private int lenderId;
    private int bookId;
    private Date requestDate;
    private String status;

    // Constructors
    public Request() {
    }
    public Request( int borrowerId, int lenderId, int bookId, Date requestDate, String status) {

        this.borrowerId = borrowerId;
        this.lenderId = lenderId;
        this.bookId = bookId;
        this.requestDate = requestDate;
        this.status = status;
    }
    public Request( int borrowerId, int lenderId, int bookId) {

        this.borrowerId = borrowerId;
        this.lenderId = lenderId;
        this.bookId = bookId;

    }
    public Request(int requestId, int borrowerId, int lenderId, int bookId, Date requestDate, String status) {
        this.requestId = requestId;
        this.borrowerId = borrowerId;
        this.lenderId = lenderId;
        this.bookId = bookId;
        this.requestDate = requestDate;
        this.status = status;
    }

    // Getters
    public int getRequestId() {
        return requestId;
    }

    public int getBorrowerId() {
        return borrowerId;
    }

    public int getLenderId() {
        return lenderId;
    }

    public int getBookId() {
        return bookId;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public String getStatus() {
        return status;
    }

    // Setters
    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public void setBorrowerId(int borrowerId) {
        this.borrowerId = borrowerId;
    }

    public void setLenderId(int lenderId) {
        this.lenderId = lenderId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Override toString method for easy printing
    @Override
    public String toString() {
        return "Request{" +
                "requestId=" + requestId +
                ", borrowerId=" + borrowerId +
                ", lenderId=" + lenderId +
                ", bookId=" + bookId +
                ", requestDate=" + requestDate +
                ", status='" + status + '\'' +
                '}';
    }
}
