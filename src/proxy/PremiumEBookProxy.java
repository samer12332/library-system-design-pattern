package proxy;

import model.EBook;
import model.User;

public class PremiumEBookProxy implements EBookReader {
    private final EBook eBook;
    private final EBookReader realReader;

    public PremiumEBookProxy(EBook eBook) {
        this.eBook = eBook;
        this.realReader = new RealEBookReader(eBook);
    }

    @Override
    public String read(User user) {
        if (user.isPremium()) {
            return realReader.read(user);
        }
        return "[Proxy] Access denied for " + user.getName() + ". " + eBook.getPreviewContent();
    }
}
