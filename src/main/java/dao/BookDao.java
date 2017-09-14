package dao;

import java.util.List;
import java.util.Map;

import model.Book;

public interface BookDao extends BaseDao {
    public Book getBookByID(int bookID);
    public List<Book> getAllBooks();
    public int getAllBooksCount();
    public List<Book> getAllBooksByPage(int part,int pageSize);
    public List<Book> getBooksByUserID(int userID);
    public List<Book> getBooksByCategory1NameByPage(String category1Name, int part, int pageSize);
    public List<Book> getBooksByCategory2NameByPage(String category2Name, int part, int pageSize);
    public List<Book> searchByTextByPage(String searchText,int part,int pageSize);
    public List<Book> searchByCondition(Map condition, Integer part, Integer pageSize);
    public List<Book> getRecommendBook();
    public Map getBookProfileMapInMongo(String profileID);
    public String saveBookProfileInMongo(Map bookProfileInMongo);
    public boolean deleteBookProfileInMongo(String profileID);
    public boolean updateBookProfileInMongo(Book book, Map bookProfileInMong);
}