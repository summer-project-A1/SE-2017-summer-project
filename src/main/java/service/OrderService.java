package service;

public interface OrderService {
    public Boolean addToCart(int bookID);
    public Boolean showCart();
    public Boolean removeFromCart();
    public Boolean emptyCart();
    public Boolean createOrder();
    public Boolean submitOrder(int orderID);
    public Boolean cancelOrder(int orderID);
}