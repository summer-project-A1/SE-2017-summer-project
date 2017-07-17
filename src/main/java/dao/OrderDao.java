package dao;

import java.util.List;

import model.Order;

public interface OrderDao extends BaseDao {
    public List<Order> getOrdersByBuyerID(int buyerID);
    public List<Order> getOrdersBySellerID(int sellerID);
    public Order getOrderByID(int orderID);
}