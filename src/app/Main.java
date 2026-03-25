package app;

import adapter.ExternalBookAdapter;
import adapter.JsonBookAdapter;
import chain.ApprovalHandler;
import chain.DirectorApprovalHandler;
import chain.LibrarianApprovalHandler;
import chain.ManagerApprovalHandler;
import decorator.BorrowPolicy;
import facade.LibraryFacade;
import factory.BookFactory;
import factory.BookType;
import factory.DefaultBookFactory;
import model.Book;
import model.BorrowRequest;
import model.EBook;
import model.User;
import model.UserType;
import proxy.EBookReader;
import proxy.PremiumEBookProxy;
import service.LibraryService;

public class Main {
    public static void main(String[] args) {
        System.out.println("===== Library Management System (Design Patterns Demo) =====");

        // 1) Singleton
        System.out.println("\n[1] Singleton");
        LibraryService libraryServiceA = LibraryService.getInstance();
        LibraryService libraryServiceB = LibraryService.getInstance();
        System.out.println("Same LibraryService instance? " + (libraryServiceA == libraryServiceB));

        // 2) Factory
        System.out.println("\n[2] Factory");
        BookFactory bookFactory = new DefaultBookFactory();
        BorrowPolicy regularPolicy = bookFactory.createBook(
            BookType.REGULAR, "Clean Code", "Robert C. Martin", 2008, true, 14);
        BorrowPolicy eBookPolicy = bookFactory.createBook(
            BookType.EBOOK, "Refactoring Ebook", "Martin Fowler", 2018, true, 10);
        BorrowPolicy premiumPolicy = bookFactory.createBook(
            BookType.PREMIUM, "Domain-Driven Design", "Eric Evans", 2003, true, 14);
        System.out.println("Created: " + regularPolicy.getBook().getTitle() + " (RegularBook)");
        System.out.println("Created: " + eBookPolicy.getBook().getTitle() + " (EBook)");
        System.out.println("Created: " + premiumPolicy.getBook().getTitle() + " (" + premiumPolicy.getTypeName() + ")");

        libraryServiceA.addBook(regularPolicy.getBook());
        libraryServiceA.addBook(eBookPolicy.getBook());
        libraryServiceA.addBook(premiumPolicy.getBook());

        // 3) Decorator
        System.out.println("\n[3] Decorator");
        System.out.println("Base days: " + regularPolicy.getAllowedBorrowDays());
        System.out.println("Premium days (+10): " + premiumPolicy.getAllowedBorrowDays());

        // 4) Proxy
        System.out.println("\n[4] Proxy");
        User regularUser = new User("John", UserType.REGULAR);
        User premiumUser = new User("Alice", UserType.PREMIUM);
        EBook eBook = (EBook) eBookPolicy.getBook();
        EBookReader eBookProxy = new PremiumEBookProxy(eBook);
        System.out.println(eBookProxy.read(regularUser));
        System.out.println(eBookProxy.read(premiumUser));

        // 5) Chain of Responsibility
        System.out.println("\n[5] Chain of Responsibility");
        ApprovalHandler chain = buildApprovalChain();
        Book chainBook = regularPolicy.getBook();
        chain.handle(new BorrowRequest(regularUser, chainBook, 7));   // Librarian
        chain.handle(new BorrowRequest(regularUser, chainBook, 14));  // Manager
        chain.handle(new BorrowRequest(regularUser, chainBook, 21));  // Director

        // 6) Facade
        System.out.println("\n[6] Facade");
        LibraryFacade libraryFacade = new LibraryFacade(libraryServiceA, chain);
        libraryFacade.borrowBook(regularUser, "Clean Code", 6);

        // 7) Adapter
        System.out.println("\n[7] Adapter");
        String externalJson = "{\n"
            + "  \"bookTitle\": \"Design Patterns\",\n"
            + "  \"isBorrowable\": true,\n"
            + "  \"authorName\": \"Erich Gamma\",\n"
            + "  \"year\": 1994\n"
            + "}";
        ExternalBookAdapter adapter = new JsonBookAdapter();
        Book adaptedBook = adapter.toBook(externalJson);
        libraryServiceA.addBook(adaptedBook);
        System.out.println("Adapted external JSON into internal Book: " + adaptedBook);

        // 8) Observer
        System.out.println("\n[8] Observer");
        libraryServiceA.subscribeToBook("Domain-Driven Design", regularUser);
        libraryServiceA.subscribeToBook("Domain-Driven Design", premiumUser);
        libraryServiceA.borrowBook("Domain-Driven Design");
        libraryServiceA.returnBook("Domain-Driven Design");

        // 9) Extended chain thresholds
        System.out.println("\n[9] Extended Chain Rule");
        chain.handle(new BorrowRequest(premiumUser, adaptedBook, 5));
        chain.handle(new BorrowRequest(premiumUser, adaptedBook, 12));
        chain.handle(new BorrowRequest(premiumUser, adaptedBook, 18));

        System.out.println("\n===== Demo Complete =====");
    }

    private static ApprovalHandler buildApprovalChain() {
        ApprovalHandler librarian = new LibrarianApprovalHandler();
        ApprovalHandler manager = new ManagerApprovalHandler();
        ApprovalHandler director = new DirectorApprovalHandler();
        librarian.setNext(manager).setNext(director);
        return librarian;
    }
}
