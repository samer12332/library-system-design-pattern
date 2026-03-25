package chain;

import model.BorrowRequest;

public class DirectorApprovalHandler extends ApprovalHandler {
    @Override
    protected boolean canApprove(BorrowRequest request) {
        return request.getRequestedDays() >= 15;
    }

    @Override
    protected void approve(BorrowRequest request) {
        System.out.println("[Chain] Director approved request for "
            + request.getUser().getName() + " (" + request.getRequestedDays() + " days).");
    }
}
