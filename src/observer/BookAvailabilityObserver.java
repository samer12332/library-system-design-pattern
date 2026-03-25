package observer;

import model.Book;

public interface BookAvailabilityObserver {
    void onBookAvailable(Book book);
}
