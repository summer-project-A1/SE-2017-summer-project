package dao;

import java.util.List;
import java.util.Map;

import model.Book;

public interface BookDao {
    public List<Book> getAllBooks();
    public List<Book> getBooksByUserID(int userID);
    public List<Book> searchByCondition(Map condition);
    public Map getBookProfileMap(int bookID);
}