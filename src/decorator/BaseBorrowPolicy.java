package decorator;

import model.Book;

public class BaseBorrowPolicy implements BorrowPolicy {
    private final Book book;

    public BaseBorrowPolicy(Book book) {
        this.book = book;
    }

    @Override
    public Book getBook() {
        return book;
    }

    @Override
    public int getAllowedBorrowDays() {
        return book.getBaseBorrowDays();
    }

    @Override
    public String getTypeName() {
        return "BaseBook";
    }
}
