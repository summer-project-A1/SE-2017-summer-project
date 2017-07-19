package service;

import java.util.List;
import java.util.Map;

public interface CartService extends BaseService {
    public Map showBorrowCart();
    public boolean addToBorrowCart(int bookID);
    public boolean removeFromBorrowCart(int bookID);
    public boolean emptyBorrowCart();
    
    public Map showBuyCart();
    public boolean addToBuyCart(int bookID);
    public boolean removeFromBuyCart(int bookID);
    public boolean emptyBuyCart();
}