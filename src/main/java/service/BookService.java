package service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import model.Book;
import model.BookProfile;
import model.Category1;

public interface BookService extends BaseService {
    public Boolean uploadBook(BookProfile bookProfile);
    public Boolean isLastPart(int part, int pageSize);
    public List<Book> showAllBooksByPage(int part,int pageSize);
    public List<Book> showUserBooks();
    public List<Book> searchByTextByPage(String searchText,int part,int pageSize);
    public List<Book> searchBook(Integer part, Integer pageSize, String category1NameString, String category2NameString, String yearString, String statusString);
    public Book showBook(int bookID);
    public List<Book> getRecommendBookList();
    public BookProfile showBookProfile(int bookID);
    public Boolean updateBook(BookProfile bookProfile);
    public Boolean deleteBook(int bookID);
    public List<Book> showBooksByCategory1NameByPage(String category1Name, int part, int pageSize);
    public List<Book> showBooksByCategory2NameByPage(String category2Name, int part, int pageSize);
    public List<Category1> showAllCategory1s();
    public List<Book> showBooksByConditions(Integer part, Integer pageSize, String category1Name, String category2Name);
    public Map getInfoByIsbn(String isbn);
}