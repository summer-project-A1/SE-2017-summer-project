package dao;

import java.util.List;
import java.util.Map;

import model.Book;

public interface BookDao extends BaseDao {
    public Book getBookByID(int bookID);
    public List<Book> getAllBooks();
    public List<Book> getBooksByUserID(int userID);
    public List<Book> searchByCondition(Map condition);
    public Map getBookProfileMap(int bookID);
    public String saveBookProfile(Map bookProfile);
    public boolean updateBookProfile(int bookID, Map bookProfile);
}