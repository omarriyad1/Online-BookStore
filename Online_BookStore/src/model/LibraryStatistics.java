package model;

public class LibraryStatistics {
    private int totalBooks;
    private int borrowedBooks;
    private int availableBooks;
    private int acceptedRequests;
    private int rejectedRequests;
    private int pendingRequests;

    // Constructors
    public LibraryStatistics() {
    }

    public LibraryStatistics(int totalBooks, int borrowedBooks, int availableBooks, int acceptedRequests, int rejectedRequests, int pendingRequests) {
        this.totalBooks = totalBooks;
        this.borrowedBooks = borrowedBooks;
        this.availableBooks = availableBooks;
        this.acceptedRequests = acceptedRequests;
        this.rejectedRequests = rejectedRequests;
        this.pendingRequests = pendingRequests;
    }

    // Getters
    public int getTotalBooks() {
        return totalBooks;
    }

    public int getBorrowedBooks() {
        return borrowedBooks;
    }

    public int getAvailableBooks() {
        return availableBooks;
    }

    public int getAcceptedRequests() {
        return acceptedRequests;
    }

    public int getRejectedRequests() {
        return rejectedRequests;
    }

    public int getPendingRequests() {
        return pendingRequests;
    }

    // Setters
    public void setTotalBooks(int totalBooks) {
        this.totalBooks = totalBooks;
    }

    public void setBorrowedBooks(int borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    public void setAvailableBooks(int availableBooks) {
        this.availableBooks = availableBooks;
    }

    public void setAcceptedRequests(int acceptedRequests) {
        this.acceptedRequests = acceptedRequests;
    }

    public void setRejectedRequests(int rejectedRequests) {
        this.rejectedRequests = rejectedRequests;
    }

    public void setPendingRequests(int pendingRequests) {
        this.pendingRequests = pendingRequests;
    }

    // Override toString method for easy printing
    @Override
    public String toString() {
        return "LibraryStatistics{" +
                "totalBooks=" + totalBooks +
                ", borrowedBooks=" + borrowedBooks +
                ", availableBooks=" + availableBooks +
                ", acceptedRequests=" + acceptedRequests +
                ", rejectedRequests=" + rejectedRequests +
                ", pendingRequests=" + pendingRequests +
                '}';
    }
}
