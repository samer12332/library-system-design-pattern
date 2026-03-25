package decorator;

public class PremiumBookDecorator extends BorrowPolicyDecorator {
    private static final int EXTRA_DAYS = 10;

    public PremiumBookDecorator(BorrowPolicy delegate) {
        super(delegate);
    }

    @Override
    public int getAllowedBorrowDays() {
        return super.getAllowedBorrowDays() + EXTRA_DAYS;
    }

    @Override
    public String getTypeName() {
        return "PremiumBook";
    }
}
