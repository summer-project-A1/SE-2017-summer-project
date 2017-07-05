package dao;

import java.util.List;
import java.util.Map;

import model.Book;

public interface BookDao extends BaseDao {
    public Book getBookByID(int bookID);
    public List<Book> getAllBooks();
    public List<Book> getBooksByUserID(int userID);
    public List<Book> searchByCondition(Map condition);
    public Map getBookProfileMap(Book book);
    public String saveBookProfile(Map bookProfile);
    public boolean updateBookProfile(Book book, Map bookProfile);
}