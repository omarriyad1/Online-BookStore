package model;

public class Book {
    private int bookId;
    private String title;
    private String author;
    private String genre;
    private double price;
    private int quantity;
    private int lenderId;

    // Constructors
    public Book() {
    }
    public Book( String title, String author, String genre, double price, int quantity) {

        this.title = title;
        this.author = author;
        this.genre = genre;
        this.price = price;
        this.quantity = quantity;

    }

    public Book(int bookId, String title, String author, String genre, double price, int quantity, int lenderId) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.price = price;
        this.quantity = quantity;
        this.lenderId = lenderId;
    }

    // Getters
    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getLenderId() {
        return lenderId;
    }

    // Setters
    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setLenderId(int lenderId) {
        this.lenderId = lenderId;
    }

    // Override toString method for easy printing
    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", lenderId=" + lenderId +
                '}';
    }
}
