package dao;

import java.util.List;
import java.util.Map;

import model.Book;
import model.BookInfo;

public interface BookDao extends BaseDao {
    public Book getBookByID(int bookID);
    public BookInfo getBookInfoByID(int bookID);
    public List<Book> getAllBooks();
    public int getAllBooksCount();
    public List<Book> getAllBooksByPage(int part,int pageSize);
    public List<BookInfo> getAllBookInfoByPage(int part,int pageSize);
    public List<Book> getBooksByUserID(int userID);
    public List<Book> searchByTextByPage(String searchText,int part,int pageSize);
    public List<Book> searchByCondition(Map condition);
    public Map getBookProfileMap(Book book);
    public String saveBookProfile(Map bookProfile);
    public boolean updateBookProfile(Book book, Map bookProfile);
}