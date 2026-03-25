package decorator;

import model.Book;

public interface BorrowPolicy {
    Book getBook();
    int getAllowedBorrowDays();
    String getTypeName();
}
