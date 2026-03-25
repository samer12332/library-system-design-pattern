package chain;

import model.BorrowRequest;

public class LibrarianApprovalHandler extends ApprovalHandler {
    @Override
    protected boolean canApprove(BorrowRequest request) {
        return request.getRequestedDays() <= 7;
    }

    @Override
    protected void approve(BorrowRequest request) {
        System.out.println("[Chain] Librarian approved request for "
            + request.getUser().getName() + " (" + request.getRequestedDays() + " days).");
    }
}
