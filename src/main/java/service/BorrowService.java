package service;

public interface BorrowService {
    public Boolean borrowBook(int userID, int bookID);
    public Boolean returnBook(int userID, int bookID);
    public Boolean delayBook(int userID, int bookID);
}