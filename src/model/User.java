package model;

import observer.BookAvailabilityObserver;

public class User implements BookAvailabilityObserver {
    private final String name;
    private final UserType userType;

    public User(String name, UserType userType) {
        this.name = name;
        this.userType = userType;
    }

    public String getName() {
        return name;
    }

    public UserType getUserType() {
        return userType;
    }

    public boolean isPremium() {
        return userType == UserType.PREMIUM;
    }

    @Override
    public void onBookAvailable(Book book) {
        System.out.println("[Observer] Notification for " + name + ": '" + book.getTitle() + "' is now available.");
    }
}
