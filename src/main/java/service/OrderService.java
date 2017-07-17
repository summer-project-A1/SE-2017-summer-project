package service;

import java.util.List;
import java.util.Map;

import model.Order;
import model.OrderProfile;

public interface OrderService extends BaseService {
    public Map getOrderDetailByID(int orderID);
    public Map createBuyOrder(String fullAddress);
    public Map confirmBuyOrder(List<Integer> orderIDList);
    public boolean cancelOrder(int orderID);
}