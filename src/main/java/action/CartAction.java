package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import model.Book;
import service.BorrowService;
import service.CartService;
import service.OrderService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartAction extends ActionSupport{
    private static final long serialVersionUID = -7295028045750112708L;

    private CartService cartService;
    private OrderService orderService;
    private BorrowService borrowService;

    private int bookID;
    private int amount;

    private List cart;
    private Map params;

    /* ========================================================= */

    public CartService getCartService() {
        return cartService;
    }
    public void setCartService(CartService cartService) {
        this.cartService = cartService;
    }
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
    public int getBookID() {
        return bookID;
    }
    public void setBookID(int bookID) {
        this.bookID = bookID;
    }
    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
    public List<Book> getCart() {
        return cart;
    }
    public void setCart(List cart) {
        this.cart = cart;
    }
    public Map getParams() {
        return params;
    }
    public void setParams(Map params) {
        this.params = params;
    }

    /* ========================================================= */

    public String addToBuyCart() {
        params = new HashMap();
        boolean result = this.cartService.addToBuyCart(this.bookID);
        if(result) {
            params.put("success", true);
        }
        else {
            params.put("success", false);
        }
        return "ajax";
    }
    public String showBuyCart() {
        this.cart = this.cartService.showBuyCart();
        ActionContext.getContext().put("buyOrBorrow","buy");
        ActionContext.getContext().put("booksInCart",cart);
        return "cart";
    }
    public String removeFromBuyCart() {
        params = new HashMap();
        boolean result = this.cartService.removeFromBuyCart(this.bookID);
        if(result) {
            params.put("success", true);
        }
        else {
            params.put("success", false);
        }
        return "ajax";
    }
    public String emptyBuyCart() {
        this.cartService.emptyBuyCart();
        return "emptyCart";
    }
    
    /* =========================== */
    
    public String addToBorrowCart() {
        params = new HashMap();
        boolean result = this.cartService.addToBorrowCart(this.bookID);
        if(result) {
            params.put("success", true);
        }
        else {
            params.put("success", false);
        }
        return "ajax";
    }
    public String showBorrowCart() {
        this.cart = this.cartService.showBorrowCart();
        ActionContext.getContext().put("buyOrBorrow","borrow");
        ActionContext.getContext().put("booksInCart",cart);
        return "cart";
    }
    public String removeFromBorrowCart() {
        params = new HashMap();
        boolean result = this.cartService.removeFromBorrowCart(this.bookID);
        if(result) {
            params.put("success", true);
        }
        else {
            params.put("success", false);
        }
        return "ajax";
    }
    public String emptyBorrowCart() {
        this.cartService.emptyBorrowCart();
        return "emptyCart";
    }
    
    /* ======================= */
       
}
