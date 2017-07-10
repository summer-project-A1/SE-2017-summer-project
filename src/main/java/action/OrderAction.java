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
        return SUCCESS;
    }
    public String createBuyOrder() {     // 用户创建订单，添加到数据库，跳转到付款页面
        this.orderService.createOrder();
        return "createBuy";
    }
    public String confirmOrder() {       // 用户付款确认订单，修改订单状态
        return SUCCESS;
    }
    public String cancelOrder() {        // 取消订单（已创建但未付款确认）
        return SUCCESS;
    }
}