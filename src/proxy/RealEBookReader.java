package proxy;

import model.EBook;
import model.User;

public class RealEBookReader implements EBookReader {
    private final EBook eBook;

    public RealEBookReader(EBook eBook) {
        this.eBook = eBook;
    }

    @Override
    public String read(User user) {
        return "[Proxy] Full access granted to " + user.getName() + ": " + eBook.getFullContent();
    }
}
