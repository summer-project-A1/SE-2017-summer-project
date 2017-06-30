package service;

import java.util.List;

public interface OrderService {
    public boolean addToCart(int bookID);
    public List showCart();
    public boolean removeFromCart();
    public boolean emptyCart();
    public boolean createOrder();
    public boolean submitOrder(int orderID);
    public boolean cancelOrder(int orderID);
}