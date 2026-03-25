package adapter;

public class ExternalBookDto {
    private final String bookTitle;
    private final boolean borrowable;
    private final String authorName;
    private final int year;

    public ExternalBookDto(String bookTitle, boolean borrowable, String authorName, int year) {
        this.bookTitle = bookTitle;
        this.borrowable = borrowable;
        this.authorName = authorName;
        this.year = year;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public boolean isBorrowable() {
        return borrowable;
    }

    public String getAuthorName() {
        return authorName;
    }

    public int getYear() {
        return year;
    }
}
