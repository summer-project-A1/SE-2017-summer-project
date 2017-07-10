package service;

import java.util.Date;

public interface BorrowService extends BaseService {
    public boolean borrowBook(int bookID, Date yhDate);
    public boolean returnBook(int borrowID);
    public boolean delayBook(int borrowID, Date newYhDate);
}