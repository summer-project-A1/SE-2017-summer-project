package action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;

import service.OrderService;

import model.Book;

public class OrderAction extends ActionSupport {
    private static final long serialVersionUID = 2210578889662002765L;
    
    private OrderService orderService;
    
    private int bookID;
    private int amount;
    
    private List<Book> cart;
    private Map params;
    
    /* ========================================================= */
    

    public OrderService getOrderService() {
        return orderService;
    }
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
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
    public void setCart(List<Book> cart) {
        this.cart = cart;
    }
    public Map getParams() {
        return params;
    }
    public void setParams(Map params) {
        this.params = params;
    }
    
    /* ========================================================= */
    
    public String addToCart() {
        params = new HashMap();
        boolean result = this.orderService.addToCart(this.bookID);
        if(result) {
            params.put("success", true);
        }
        else {
            params.put("success", false);
        }
        return "ajax";
    }
    public String showCart() {
        this.cart = this.orderService.showCart();
        return "cart";
    }
}