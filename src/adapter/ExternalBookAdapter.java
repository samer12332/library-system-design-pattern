package adapter;

import model.Book;

public interface ExternalBookAdapter {
    Book toBook(String externalJson);
}
