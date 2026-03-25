package model;

public class BorrowRequest {
    private final User user;
    private final Book book;
    private final int requestedDays;

    public BorrowRequest(User user, Book book, int requestedDays) {
        this.user = user;
        this.book = book;
        this.requestedDays = requestedDays;
    }

    public User getUser() {
        return user;
    }

    public Book getBook() {
        return book;
    }

    public int getRequestedDays() {
        return requestedDays;
    }
}
