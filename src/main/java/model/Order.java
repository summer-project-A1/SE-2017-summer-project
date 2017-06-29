package model;

import java.util.Calendar;

import common.constants.OrderStatus;

public class Order {               // 使用积分下单购买书
    private int orderID;
    private int userID;
    private Calendar orderDate;
    private int totalPrice;
    private OrderStatus status;
    
    /* ================================================= */
    
    public int getOrderID() {
        return orderID;
    }
    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }
    public int getUserID() {
        return userID;
    }
    public void setUserID(int userID) {
        this.userID = userID;
    }
    public Calendar getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(Calendar orderDate) {
        this.orderDate = orderDate;
    }
    public int getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
    public OrderStatus getStatus() {
        return status;
    }
    public void setStatus(OrderStatus status) {
        this.status = status;
    }
    
}