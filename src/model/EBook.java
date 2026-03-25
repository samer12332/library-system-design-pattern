package model;

public class EBook extends Book {
    private final String fullContent;
    private final String previewContent;

    public EBook(String title, String author, int year, boolean borrowable, int baseBorrowDays, String fullContent) {
        super(title, author, year, borrowable, baseBorrowDays);
        this.fullContent = fullContent;
        this.previewContent = "Preview of '" + title + "'. Upgrade to premium for full access.";
    }

    public String getFullContent() {
        return fullContent;
    }

    public String getPreviewContent() {
        return previewContent;
    }
}
