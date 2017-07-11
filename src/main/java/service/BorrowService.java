package service;

import java.util.Date;
import java.util.Map;

public interface BorrowService extends BaseService {
    public Map showMyBorrow();
    public boolean borrowBook(int bookID);
    public Map borrowAllBookInBorrowCart();
    public Map returnBook(int borrowID);
    public Map delayBook(int borrowID);
}