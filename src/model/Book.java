package model;

public class Book {
    private final String title;
    private final String author;
    private final int year;
    private final boolean borrowable;
    private final int baseBorrowDays;
    private boolean available = true;

    public Book(String title, String author, int year, boolean borrowable, int baseBorrowDays) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.borrowable = borrowable;
        this.baseBorrowDays = baseBorrowDays;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    public boolean isBorrowable() {
        return borrowable;
    }

    public int getBaseBorrowDays() {
        return baseBorrowDays;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Book{"
            + "title='" + title + '\''
            + ", author='" + author + '\''
            + ", year=" + year
            + ", borrowable=" + borrowable
            + ", baseBorrowDays=" + baseBorrowDays
            + ", available=" + available
            + '}';
    }
}
