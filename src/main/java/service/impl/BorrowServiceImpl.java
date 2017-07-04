package service.impl;

import java.util.Date;

import common.constants.BookStatus;
import dao.BookDao;
import dao.BookReleaseDao;
import dao.BorrowDao;
import dao.BorrowHistoryDao;
import dao.UserDao;
import model.Book;
import model.BookRelease;
import model.Borrow;
import model.User;
import service.BorrowService;

public class BorrowServiceImpl extends BaseServiceImpl implements BorrowService {
    private BookDao bookDao;
    private BookReleaseDao bookReleaseDao;
    private UserDao userDao;
    private BorrowDao borrowDao;
    private BorrowHistoryDao borrowHistoryDao;
    
    /* ========================================================== */
    
    public BookDao getBookDao() {
        return bookDao;
    }

    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public BookReleaseDao getBookReleaseDao() {
        return bookReleaseDao;
    }

    public void setBookReleaseDao(BookReleaseDao bookReleaseDao) {
        this.bookReleaseDao = bookReleaseDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public BorrowDao getBorrowDao() {
        return borrowDao;
    }

    public void setBorrowDao(BorrowDao borrowDao) {
        this.borrowDao = borrowDao;
    }

    public BorrowHistoryDao getBorrowHistoryDao() {
        return borrowHistoryDao;
    }

    public void setBorrowHistoryDao(BorrowHistoryDao borrowHistoryDao) {
        this.borrowHistoryDao = borrowHistoryDao;
    }

    /* ============================================================= */
    
    @Override
    public boolean borrowBook(int bookID, Date yhDate) {
        if(!isLogined()) {
            return false;
        }
        Book book = this.bookDao.getBookByID(bookID);
        if(book.getStatus() != BookStatus.IDLE) {
            return false;
        }
        BookRelease bookRelease = this.bookReleaseDao.getReleaseBookByBookID(bookID);
        User user = getLoginedUserInfo();
        Borrow newBorrow = new Borrow();
        newBorrow.setBookID(bookID);
        newBorrow.setUserID(user.getUserID());
        newBorrow.setBorrowDate(new Date());
        newBorrow.setBorrowPrice(bookRelease.getBorrowPrice());
        newBorrow.setYhDate(yhDate);
        this.borrowDao.save(newBorrow);
        book.setStatus(BookStatus.BORROWED);
        return true;
    }

    @Override
    public boolean returnBook(int bookID) {
        if(!isLogined()) {
            return false;
        }
        Book book = this.bookDao.getBookByID(bookID);
        if(book.getStatus() != BookStatus.BORROWED) {
            return false;
        }
        ///////////////////???????????????????????
        return false;
    }

    @Override
    public boolean delayBook(int bookID) {
        // TODO 自动生成的方法存根
        return false;
    }
    
}