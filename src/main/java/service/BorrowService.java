package service;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface BorrowService extends BaseService {
    public Map showMyBorrow();
    public boolean borrowBook(int bookID);
    public Map createBorrowOrder(String fullAddress);
    public Boolean confirmBorrowOrder(List<Integer> borrowIDList);
    public Map returnBook(int borrowID,String trackingNo1);
    public Map delayBook(int borrowID);
}