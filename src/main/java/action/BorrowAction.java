package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import model.Book;
import service.BorrowService;
import service.CartService;
import service.OrderService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lvjiawei on 2017/7/10.
 */
public class BorrowAction extends ActionSupport {

    private BorrowService borrowService;

    private int bookID;
    private Map params;

    /* ============================================================== */
    public BorrowService getBorrowService() {
        return borrowService;
    }
    public void setBorrowService(BorrowService borrowService) {
        this.borrowService = borrowService;
    }

    public int getBookID() {
        return bookID;
    }
    public void setBookID(int bookID) {
        this.bookID = bookID;
    }
    public Map getParams() {
        return params;
    }
    public void setParams(Map params) {
        this.params = params;
    }

    /* ============================================================== */
    public String showMyBorrow(){
        this.params = this.borrowService.showMyBorrow();
        List borrowBook = (List)params.get("borrowBook");
        List borrowHistoryBook = (List)params.get("borrowHistoryBook");
        ActionContext.getContext().put("borrowBook",borrowBook);
        ActionContext.getContext().put("borrowHistoryBook",borrowHistoryBook);
        return "showMyBorrow";
    }

    public String delayBook(){
        params = new HashMap();
        Date yhdate = new Date();  /*需要一个应还日期，尚未实现*/
        if(this.borrowService.delayBook(getBookID(),yhdate)){
            params.put("success",true);
            params.put("yhdate",yhdate);
        }
        return "ajax";
    }

    public String returnBook(){
        params = new HashMap();
        Date returnDate = new Date(); /*需要一个归还日期，尚未实现*/
        if(this.borrowService.returnBook(getBookID())){
            params.put("success",true);
            params.put("returnDate",returnDate);
        }
        return "ajax";
    }
}
