package service;

import java.util.HashMap;
import java.util.Map;
import model.Book;
import observer.BookAvailabilityObserver;
import observer.BookWaitlist;

public class LibraryService {
    // Singleton pattern: only one shared service instance.
    private static final LibraryService INSTANCE = new LibraryService();

    private final Map<String, Book> booksByTitle = new HashMap<>();
    private final Map<String, BookWaitlist> waitlistsByTitle = new HashMap<>();

    private LibraryService() {
    }

    public static LibraryService getInstance() {
        return INSTANCE;
    }

    public void addBook(Book book) {
        booksByTitle.put(normalize(book.getTitle()), book);
        waitlistsByTitle.put(normalize(book.getTitle()), new BookWaitlist(book));
    }

    public Book findBook(String title) {
        return booksByTitle.get(normalize(title));
    }

    public boolean borrowBook(String title) {
        Book book = findBook(title);
        if (book == null) {
            System.out.println("[Service] Book not found: " + title);
            return false;
        }
        if (!book.isBorrowable()) {
            System.out.println("[Service] Book is not borrowable: " + title);
            return false;
        }
        if (!book.isAvailable()) {
            System.out.println("[Service] Book is currently unavailable: " + title);
            return false;
        }
        book.setAvailable(false);
        System.out.println("[Service] Book borrowed: " + book.getTitle());
        return true;
    }

    public void returnBook(String title) {
        Book book = findBook(title);
        if (book == null) {
            System.out.println("[Service] Book not found: " + title);
            return;
        }
        book.setAvailable(true);
        System.out.println("[Service] Book returned: " + book.getTitle());
        BookWaitlist waitlist = waitlistsByTitle.get(normalize(title));
        if (waitlist != null) {
            waitlist.notifyAvailable();
        }
    }

    public void subscribeToBook(String title, BookAvailabilityObserver observer) {
        BookWaitlist waitlist = waitlistsByTitle.get(normalize(title));
        if (waitlist == null) {
            System.out.println("[Observer] Cannot subscribe. Unknown book: " + title);
            return;
        }
        waitlist.subscribe(observer);
        System.out.println("[Observer] Subscription added for '" + title + "'.");
    }

    private String normalize(String title) {
        return title.toLowerCase().trim();
    }
}
