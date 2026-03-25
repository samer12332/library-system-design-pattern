package chain;

import model.BorrowRequest;

public class ManagerApprovalHandler extends ApprovalHandler {
    @Override
    protected boolean canApprove(BorrowRequest request) {
        return request.getRequestedDays() <= 14;
    }

    @Override
    protected void approve(BorrowRequest request) {
        System.out.println("[Chain] Manager approved request for "
            + request.getUser().getName() + " (" + request.getRequestedDays() + " days).");
    }
}
