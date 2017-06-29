package dao;

import java.util.List;

import model.OrderItem;

public interface OrderItemDao extends BaseDao {
    public List<OrderItem> getOrderItemByOrderID(int orderID);
}