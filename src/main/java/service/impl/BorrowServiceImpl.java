package service.impl;

import java.util.Date;

import common.constants.BookStatus;
import dao.ApplyDao;
import dao.BookDao;
import dao.BookReleaseDao;
import dao.BorrowDao;
import dao.BorrowHistoryDao;
import dao.UserDao;
import model.Book;
import model.BookRelease;
import model.Borrow;
import model.BorrowHistory;
import model.User;
import model.Apply;
import service.BorrowService;

public class BorrowServiceImpl extends BaseServiceImpl implements BorrowService {
    private BookDao bookDao;
    private BookReleaseDao bookReleaseDao;
    private UserDao userDao;
    private BorrowDao borrowDao;
    private BorrowHistoryDao borrowHistoryDao;
    private ApplyDao applyDao;
    
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
    
    public ApplyDao getApplyDao() {
        return applyDao;
    }

    public void setApplyDao(ApplyDao applyDao) {
        this.applyDao = applyDao;
    }

    /* ============================================================= */
    
    @Override
    public boolean borrowBook(int borrowID, Date yhDate) {
        if(!isLogined()) {
            return false;
        }
        Borrow borrow = this.borrowDao.getBorrowByID(borrowID);
        int bookID = borrow.getBookID();
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
        newBorrow.setBorrowPrice(bookRelease.getBorrowCredit());
        newBorrow.setYhDate(yhDate);
        this.borrowDao.save(newBorrow);
        book.setStatus(BookStatus.BORROWED);
        return true;
    }

    @Override
    public boolean returnBook(int borrowID) {
        if(!isLogined()) {
            return false;
        }
        User user = getLoginedUserInfo();
        Borrow borrow = this.borrowDao.getBorrowByID(borrowID);
        if(borrow.getUserID() != user.getUserID()) {
            return false;
        }
        int bookID = borrow.getBookID();
        Book book = this.bookDao.getBookByID(bookID);
        if(book.getStatus() != BookStatus.BORROWED) {
            return false;
        }
        book.setStatus(BookStatus.IDLE);
        BorrowHistory newBorrowHistory = new BorrowHistory();
        newBorrowHistory.setBookID(bookID);
        newBorrowHistory.setBorrowDate(borrow.getBorrowDate());
        newBorrowHistory.setBorrowPrice(borrow.getBorrowPrice());
        newBorrowHistory.setInDate(new Date());
        newBorrowHistory.setUserID(user.getUserID());
        newBorrowHistory.setYhDate(borrow.getYhDate());
        this.bookDao.update(book);
        this.borrowHistoryDao.save(newBorrowHistory);
        this.borrowDao.delete(borrow);
        return true;
    }

    @Override
    public boolean delayBook(int borrowID, Date newYhDate) {
        if(!isLogined()) {
            return false;
        }
        User user = getLoginedUserInfo();
        Borrow borrow = this.borrowDao.getBorrowByID(borrowID);
        if(borrow.getUserID() != user.getUserID()) {
            return false;
        }
        borrow.setYhDate(newYhDate);
        Apply newApply = new Apply();
        newApply.setBookID(borrow.getBookID());
        newApply.setUserID(user.getUserID());
        newApply.setDue(newYhDate);
        this.borrowDao.update(borrow);
        this.applyDao.save(newApply);
        return true;
    }
    
}