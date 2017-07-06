package service;

import java.util.List;
import java.util.Map;

import model.Order;

public interface OrderService extends BaseService {
    public Map getOrderDetailByID(int orderID);
    public Order createOrder();
    public boolean submitOrder(int orderID);
    public boolean cancelOrder(int orderID);
}