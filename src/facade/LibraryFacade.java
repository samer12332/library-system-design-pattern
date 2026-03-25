package facade;

import chain.ApprovalHandler;
import model.Book;
import model.BorrowRequest;
import model.User;
import service.LibraryService;

public class LibraryFacade {
    private final LibraryService libraryService;
    private final ApprovalHandler approvalChain;

    public LibraryFacade(LibraryService libraryService, ApprovalHandler approvalChain) {
        this.libraryService = libraryService;
        this.approvalChain = approvalChain;
    }

    public void borrowBook(User user, String title, int requestedDays) {
        System.out.println("[Facade] Borrow request started for '" + title + "' by " + user.getName() + ".");

        Book book = libraryService.findBook(title);
        if (book == null) {
            System.out.println("[Facade] Book lookup failed.");
            return;
        }

        BorrowRequest request = new BorrowRequest(user, book, requestedDays);
        boolean approved = approvalChain.handle(request);
        if (!approved) {
            System.out.println("[Facade] Approval failed.");
            return;
        }

        if (!book.isAvailable()) {
            System.out.println("[Facade] Borrow failed. Book is unavailable.");
            return;
        }

        boolean borrowed = libraryService.borrowBook(title);
        if (borrowed) {
            System.out.println("[Facade] Borrow flow completed successfully.");
        } else {
            System.out.println("[Facade] Borrow flow failed.");
        }
    }
}
