package service;

import java.util.Date;
import java.util.Map;

public interface BorrowService extends BaseService {
    public Map showMyBorrow();
    public boolean borrowBook(int bookID, Date yhDate);
    public Map borrowAllBookInBorrowCart();
    public boolean returnBook(int borrowID);
    public boolean delayBook(int borrowID, Date newYhDate);
}