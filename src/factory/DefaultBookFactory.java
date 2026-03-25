package factory;

import decorator.BaseBorrowPolicy;
import decorator.BorrowPolicy;
import decorator.PremiumBookDecorator;
import model.Book;
import model.EBook;
import model.RegularBook;

public class DefaultBookFactory implements BookFactory {
    @Override
    public BorrowPolicy createBook(BookType type, String title, String author, int year, boolean borrowable, int baseBorrowDays) {
        switch (type) {
            case REGULAR:
                return new BaseBorrowPolicy(new RegularBook(title, author, year, borrowable, baseBorrowDays));
            case EBOOK:
                return new BaseBorrowPolicy(new EBook(title, author, year, borrowable, baseBorrowDays,
                    "Full eBook content for '" + title + "'."));
            case PREMIUM:
                // Decorator pattern adds 10 days without changing the core Book model.
                Book base = new RegularBook(title, author, year, borrowable, baseBorrowDays);
                return new PremiumBookDecorator(new BaseBorrowPolicy(base));
            default:
                throw new IllegalArgumentException("Unsupported type: " + type);
        }
    }
}
