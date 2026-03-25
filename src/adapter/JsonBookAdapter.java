package adapter;

import model.Book;
import model.RegularBook;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonBookAdapter implements ExternalBookAdapter {
    @Override
    public Book toBook(String externalJson) {
        ExternalBookDto dto = parseDto(externalJson);
        // Adapter converts external schema into internal Book entity.
        return new RegularBook(dto.getBookTitle(), dto.getAuthorName(), dto.getYear(), dto.isBorrowable(), 14);
    }

    private ExternalBookDto parseDto(String json) {
        String title = extractString(json, "bookTitle");
        boolean borrowable = extractBoolean(json, "isBorrowable");
        String author = extractString(json, "authorName");
        int year = extractInt(json, "year");
        return new ExternalBookDto(title, borrowable, author, year);
    }

    private String extractString(String json, String key) {
        Matcher matcher = Pattern.compile("\"" + Pattern.quote(key) + "\"\\s*:\\s*\"([^\"]*)\"").matcher(json);
        if (!matcher.find()) {
            throw new IllegalArgumentException("Missing key: " + key);
        }
        return matcher.group(1);
    }

    private boolean extractBoolean(String json, String key) {
        Matcher matcher = Pattern.compile("\"" + Pattern.quote(key) + "\"\\s*:\\s*(true|false)").matcher(json);
        if (!matcher.find()) {
            throw new IllegalArgumentException("Missing key: " + key);
        }
        return Boolean.parseBoolean(matcher.group(1));
    }

    private int extractInt(String json, String key) {
        Matcher matcher = Pattern.compile("\"" + Pattern.quote(key) + "\"\\s*:\\s*(\\d+)").matcher(json);
        if (!matcher.find()) {
            throw new IllegalArgumentException("Missing key: " + key);
        }
        return Integer.parseInt(matcher.group(1));
    }
}
