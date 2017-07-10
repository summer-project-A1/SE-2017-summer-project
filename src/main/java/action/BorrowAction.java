package action;

import com.opensymphony.xwork2.ActionSupport;

public class BorrowAction extends ActionSupport {

    public String createBorrowOrder() {
        //this.params = this.borrowService.borrowAllBookInBorrowCart();
        return "borrow";
    }
}