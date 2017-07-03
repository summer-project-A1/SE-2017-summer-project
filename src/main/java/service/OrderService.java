package service;

import java.util.List;

public interface OrderService {
    public List showCart();
    public boolean addToCart(int bookID);
    public boolean removeFromCart(int bookID);
    public boolean emptyCart();
    public boolean createOrder();
    public boolean submitOrder(int orderID);
    public boolean cancelOrder(int orderID);
}