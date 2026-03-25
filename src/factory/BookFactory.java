package factory;

import decorator.BorrowPolicy;

public interface BookFactory {
    BorrowPolicy createBook(BookType type, String title, String author, int year, boolean borrowable, int baseBorrowDays);
}
