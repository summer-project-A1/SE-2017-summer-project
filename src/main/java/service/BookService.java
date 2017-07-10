package service;

import java.util.List;
import java.util.Map;

import model.Book;
import model.BookInfo;
import model.BookProfile;

public interface BookService extends BaseService {
    public Boolean uploadBook(BookProfile bookProfile);
    public List<BookInfo> showAllBookInfoByPage(int part,int pageSize);
    public List<Book> showUserBooks(int userID);
    public List<Book> searchByTextByPage(String searchText,int part,int pageSize);
    public List<Book> searchBook(Map condition);
    public BookInfo showBookInfo(int bookID);
    public BookProfile showBookProfile(int bookID);
}