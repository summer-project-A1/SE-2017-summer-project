package service;

import java.util.List;

public interface CartService extends BaseService {
    public List showBorrowCart();
    public boolean addToBorrowCart(int bookID);
    public boolean removeFromBorrowCart(int bookID);
    public boolean emptyBorrowCart();
    
    public List showBuyCart();
    public boolean addToBuyCart(int bookID);
    public boolean removeFromBuyCart(int bookID);
    public boolean emptyBuyCart();
}