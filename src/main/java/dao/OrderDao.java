package dao;

import java.util.List;

import model.Order;

public interface OrderDao extends BaseDao {
    public List<Order> getOrdersByUserID(int userID);
    public Order getOrderByID(int orderID);
}