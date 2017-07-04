package service;

import java.util.List;
import java.util.Map;

import model.Book;

public interface BookService extends BaseService {
    public Boolean uploadBook(Map bookInfo);
    public List<Book> showAllBooks();
    public List<Book> showUserBooks(int userID);
    public List<Book> searchBook(Map condition);
    public Map showBookProfile(int bookID);
}