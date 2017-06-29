package service.impl;

import java.util.List;
import java.util.Map;

import dao.BookDao;
import model.Book;
import service.BookService;

public class BookServiceImpl implements BookService {
    private BookDao bookDao;
    
    /* ===================================================== */
    
    public BookDao getBookDao() {
        return bookDao;
    }

    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }   
    
    /* ===================================================== */
    
    @Override
    public Boolean uploadBook(Map bookInfo) {
        // TODO 自动生成的方法存根
        return null;
    }

    @Override
    public List<Book> showAllBooks() {
        return this.bookDao.getAllBooks();
    }

    @Override
    public List<Book> showUserBooks(int userID) {
        return this.bookDao.getBooksByUserID(userID);
    }

    @Override
    public List<Book> searchBook(Map condition) {
        // TODO 自动生成的方法存根
        return null;
    }

    @Override
    public Map showBookProfile(int bookID) {
        // TODO 自动生成的方法存根
        return null;
    }
    
}