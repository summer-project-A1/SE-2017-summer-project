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
import service.UserService;

import model.Book;
import model.Order;

public class OrderAction extends ActionSupport {
    private static final long serialVersionUID = 2210578889662002765L;
    
    private OrderService orderService;
    private BorrowService borrowService;
    private BookService bookService;
    private CartService cartService;
    private UserService userService;
    
    private String buyOrBorrow;
    private Integer orderID;
    
    private Map params; 
    
    private String address;     // 这里的address是四部分拼在一起后的！
    private List<Integer> orderIDList;
    
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
    public Integer getOrderID() {
        return orderID;
    }
    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }
    public UserService getUserService() {
        return userService;
    }
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public List<Integer> getOrderIDList() {
        return orderIDList;
    }
    public void setOrderIDList(List<Integer> orderIDList) {
        this.orderIDList = orderIDList;
    }

    /* ========================================================= */
    
    public String showMyOrder(){      // order对应buy（购买）
        /*
        this.params = this.orderService.showMyOrder();
        List orderBook = (List)params.get("orderBook");
        List orderHistoryBook = (List)params.get("orderHistoryBook");
        ActionContext.getContext().put("orderBook",orderBook);
        ActionContext.getContext().put("orderHistoryBook",orderHistoryBook);
        */
        return "showMyOrder";
    }
    public String buyCheckout() {        // 从购物车跳转到地址确认页面，不修改数据库
        Map buyInfo = this.cartService.showBuyCart();
        List<Book> booksInBuyCart = (List<Book>)buyInfo.get("booksInBuyCart");
        Integer totalCredit = (Integer)buyInfo.get("totalCredit");
        ActionContext.getContext().put("action","buyCheckout");
        ActionContext.getContext().put("booksInOrder",booksInBuyCart.isEmpty()?null:booksInBuyCart);
        ActionContext.getContext().put("totalCredit",totalCredit);
        Map result = this.userService.getAllDeliveryAddress();
        ActionContext.getContext().put("defaultAddrList", result.get("defaultAddrList"));
        ActionContext.getContext().put("addrList", result.get("addrList"));
        return "buyCheckout";
    }
    public String createBuyOrder() {     // 用户创建订单，添加到数据库，跳转到付款页面
        /*
         * 从前台接收拼好的address
         * service层返回一个List<OrderProfile>和totalCredit
         */
        /*Order newOrder = this.orderService.createOrder();
        List<Book> allBook = new ArrayList<Book>();
        for(OrderItem orderItem : newOrder.getOrderItems()) {
            int bookID = orderItem.getBookID();
            allBook.add(this.bookService.showBook(bookID));
        }
        ActionContext.getContext().put("order", newOrder);
        ActionContext.getContext().put("booksInOrder", allBook);
        */
        //List<OrderProfile> orderProfileList = this.orderService.createOrders(this.address);
        //ActionContext.getContext().put("buyOrBorrow","buy");
        //ActionContext.getContext().put("totalCredit",<从service层传来>）;
        //ActionContext.getContext().put("orderProfileList",orderProfileList);
        return "showOrder";
    }
    public String showOrderById() {
        Map orderInfo = this.orderService.getOrderDetailByID(this.orderID);
        Order order = (Order)orderInfo.get("order");
        List<Book> booksInOrder = (List<Book>)orderInfo.get("booksInOrder");
        ActionContext.getContext().put("order", order);
        ActionContext.getContext().put("orderStatus", order.getStatus().toString());
        ActionContext.getContext().put("booksInOrder", booksInOrder);
        return "showOrder";
    }
    public String confirmBuyOrder() {       // 用户付款确认订单（允许多个订单），修改订单状态
        /*
         * 从前台接收List<Integer> orderIDList传入service
         * 不需要service层返回特殊的内容
         */
        //boolean result = this.orderService.confirmOrder(this.orderIDList);
        return SUCCESS;
    }
    public String cancelOrder() {        // 取消订单（已创建但未付款确认）
        return SUCCESS;
    }
}