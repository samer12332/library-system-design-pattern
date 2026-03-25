package observer;

import java.util.ArrayList;
import java.util.List;
import model.Book;

public class BookWaitlist {
    private final Book book;
    private final List<BookAvailabilityObserver> observers = new ArrayList<>();

    public BookWaitlist(Book book) {
        this.book = book;
    }

    public void subscribe(BookAvailabilityObserver observer) {
        observers.add(observer);
    }

    public void unsubscribe(BookAvailabilityObserver observer) {
        observers.remove(observer);
    }

    public void notifyAvailable() {
        for (BookAvailabilityObserver observer : observers) {
            observer.onBookAvailable(book);
        }
    }
}
