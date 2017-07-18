package service;

import java.util.List;
import java.util.Map;

import model.Book;
import model.Order;
import model.OrderProfile;

public interface OrderService extends BaseService {
    public Map showMyOrder();
    public Map getOrderDetailByID(int orderID);
    public Map createBuyOrder(String fullAddress);
    public Map confirmBuyOrder(List<Integer> orderIDList);
    public boolean cancelBuyOrder(int orderID);
    public Map confirmBuyReceipt(int orderID);
    
    public List<OrderProfile> getSellBookList();
    public Map deliverBuyOrder(int orderID,String trackingNo); //卖家发货，返回success和发货时间
}