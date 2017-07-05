package service;

import java.util.List;
import model.Order;

public interface OrderService extends BaseService {
    public List showCart();
    public boolean addToCart(int bookID);
    public boolean removeFromCart(int bookID);
    public boolean emptyCart();
    public Order createOrder();
    public boolean submitOrder(int orderID);
    public boolean cancelOrder(int orderID);
}