package decorator;

import model.Book;

public abstract class BorrowPolicyDecorator implements BorrowPolicy {
    protected final BorrowPolicy delegate;

    protected BorrowPolicyDecorator(BorrowPolicy delegate) {
        this.delegate = delegate;
    }

    @Override
    public Book getBook() {
        return delegate.getBook();
    }

    @Override
    public int getAllowedBorrowDays() {
        return delegate.getAllowedBorrowDays();
    }

    @Override
    public String getTypeName() {
        return delegate.getTypeName();
    }
}
