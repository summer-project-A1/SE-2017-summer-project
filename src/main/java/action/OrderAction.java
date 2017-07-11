package action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import service.BookService;
import service.BorrowService;
import service.CartService;
import service.OrderService;

import model.Book;
import model.BookInfo;
import model.Order;
import model.OrderItem;

public class OrderAction extends ActionSupport {
    private static final long serialVersionUID = 2210578889662002765L;
    
    private OrderService orderService;
    private BorrowService borrowService;
    private BookService bookService;
    private CartService cartService;
    
    private String buyOrBorrow;
    private Integer orderID;
    
    private Map params; 
    
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
    public BookService getBookService() {
        return bookService;
    }
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }
    public CartService getCartService() {
        return cartService;
    }
    public void setCartService(CartService cartService) {
        this.cartService = cartService;
    }
    public String getBuyOrBorrow() {
        return buyOrBorrow;
    }
    public void setBuyOrBorrow(String buyOrBorrow) {
        this.buyOrBorrow = buyOrBorrow;
    }
    public Map getParams() {
        return params;
    }
    public void setParams(Map params) {
        this.params = params;
    }
    
    /* ========================================================= */
    
    public String buyCheckout() {        // 从购物车跳转到地址确认页面，不修改数据库
        List cart = this.cartService.showBuyCart();
        ActionContext.getContext().put("action","buyCheckout");
        ActionContext.getContext().put("booksInOrder",cart.isEmpty()?null:cart);
        return "buyCheckout";
    }
    public String createBuyOrder() {     // 用户创建订单，添加到数据库，跳转到付款页面
        Order newOrder = this.orderService.createOrder();
        List<BookInfo> allBookInfo = new ArrayList<BookInfo>();
        for(OrderItem orderItem : newOrder.getOrderItems()) {
            int bookID = orderItem.getBookID();
            allBookInfo.add(this.bookService.showBookInfo(bookID));
        }
        ActionContext.getContext().put("order", newOrder);
        ActionContext.getContext().put("booksInOrder", allBookInfo);
        return "createBuy";
    }
    public String showOrderById() {
        Map orderInfo = this.orderService.getOrderDetailByID(this.orderID);
        Order order = (Order)orderInfo.get("order");
        List<BookInfo> booksInOrder = (List<BookInfo>)orderInfo.get("booksInOrder");
        ActionContext.getContext().put("order", order);
        ActionContext.getContext().put("booksInOrder", booksInOrder);
        return SUCCESS;
    }
    public String confirmOrder() {       // 用户付款确认订单，修改订单状态
        return SUCCESS;
    }
    public String cancelOrder() {        // 取消订单（已创建但未付款确认）
        return SUCCESS;
    }
}