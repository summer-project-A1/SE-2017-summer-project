package action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import service.BorrowService;
import service.CartService;
import service.OrderService;

import model.Book;

public class OrderAction extends ActionSupport {
    private static final long serialVersionUID = 2210578889662002765L;
    
    private OrderService orderService;
    private BorrowService borrowService;
    
    private String buyOrBorrow;
        
    /* ========================================================= */

    public OrderService getOrderService() {
        return orderService;
    }
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }
    public BorrowService getBorrowService() {
        return borrowService;
    }
    public void setBorrowService(BorrowService borrowService) {
        this.borrowService = borrowService;
    }
    public String getBuyOrBorrow() {
        return buyOrBorrow;
    }
    public void setBuyOrBorrow(String buyOrBorrow) {
        this.buyOrBorrow = buyOrBorrow;
    }
    
    /* ========================================================= */
    
    public String createOrder() {     // 买书和借书的购物车
        if(this.buyOrBorrow.equals("buy")) {
            this.orderService.createOrder();
            return "buy";
        }
        else if(this.buyOrBorrow.equals("borrow")) {
            this.borrowService.borrowAllBookInBorrowCart();
            return "borrow";
        }
        else {
            return ERROR;
        }
    }
 
}