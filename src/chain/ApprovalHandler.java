package chain;

import model.BorrowRequest;

public abstract class ApprovalHandler {
    private ApprovalHandler next;

    public ApprovalHandler setNext(ApprovalHandler next) {
        this.next = next;
        return next;
    }

    public boolean handle(BorrowRequest request) {
        if (canApprove(request)) {
            approve(request);
            return true;
        }
        if (next != null) {
            return next.handle(request);
        }
        System.out.println("[Chain] No approver found for request days: " + request.getRequestedDays());
        return false;
    }

    protected abstract boolean canApprove(BorrowRequest request);

    protected abstract void approve(BorrowRequest request);
}
