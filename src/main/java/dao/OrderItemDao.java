package dao;

import java.util.List;

import model.OrderItem;

public interface OrderItemDao {
    public List<OrderItem> getOrderItemByOrderID(int orderID);
}