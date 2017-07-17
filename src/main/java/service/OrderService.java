package service;

import java.util.List;
import java.util.Map;

import model.Order;
import model.OrderProfile;

public interface OrderService extends BaseService {
    public Map getOrderDetailByID(int orderID);
    public List<OrderProfile> createBuyOrder(String address);
    public boolean submitBuyOrder(List<Integer> orders);
    public boolean cancelOrder(int orderID);
}