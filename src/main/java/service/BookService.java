package service;

import java.util.List;
import java.util.Map;

import model.Book;
import model.BookProfile;
import model.Category1;

public interface BookService extends BaseService {
    public Boolean uploadBook(BookProfile bookProfile);
    public List<Book> showAllBooksByPage(int part,int pageSize);
    public List<Book> showUserBooks(int userID);
    public List<Book> searchByTextByPage(String searchText,int part,int pageSize);
    public List<Book> searchBook(Map condition);
    public Book showBook(int bookID);
    public BookProfile showBookProfile(int bookID);
    public List<Book> showBooksByCategory1NameByPage(String category1Name, int part, int pageSize);
    public List<Book> showBooksByCategory2NameByPage(String category2Name, int part, int pageSize);
    public List<Category1> showAllCategory1s();
}