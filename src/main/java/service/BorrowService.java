package service;

import java.util.Date;
import java.util.Map;

public interface BorrowService extends BaseService {
    public Map showMyBorrow();
    public boolean borrowBook(int bookID);
    public Map borrowAllBookInBorrowCart(String fullAddress);
    public Map returnBook(int borrowID,String trackingNo1);
    public Map delayBook(int borrowID);
}