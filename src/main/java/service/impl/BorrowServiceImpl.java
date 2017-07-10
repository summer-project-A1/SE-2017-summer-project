package service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.constants.BookStatus;
import dao.BookDao;
import dao.BookReleaseDao;
import dao.BorrowDao;
import dao.BorrowHistoryDao;
import dao.ReserveDao;
import dao.UserDao;
import model.Book;
import model.BookInfo;
import model.BookRelease;
import model.Borrow;
import model.BorrowHistory;
import model.BorrowProfile;
import model.User;
import service.BorrowService;

public class BorrowServiceImpl extends BaseServiceImpl implements BorrowService {
    private BookDao bookDao;
    private BookReleaseDao bookReleaseDao;
    private UserDao userDao;
    private BorrowDao borrowDao;
    private BorrowHistoryDao borrowHistoryDao;
    private ReserveDao reserveDao;
    
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
    
    public ReserveDao getReserveDao() {
        return reserveDao;
    }

    public void setReserveDao(ReserveDao reserveDao) {
        this.reserveDao = reserveDao;
    }

    /* ============================================================= */
    
    @Override
    public Map showMyBorrow() {
        // Map中包含两个List<BorrowProfile>，分别对应Borrow和BorrowHistory
        if(!isLogined()) {
            return null;
        }
        User user = getLoginedUserInfo();
        int userID = user.getUserID();
        List<Borrow> borrows = this.borrowDao.getBorrowByUserID(userID);
        List<BorrowHistory> borrowHistories = this.borrowHistoryDao.getBorrowHistoryByUserID(userID);
        List<BorrowProfile> borrowBook = new ArrayList();
        List<BorrowProfile> borrowHistoryBook = new ArrayList();
        for(Borrow borrow : borrows) {
            BorrowProfile borrowProfile = new BorrowProfile();
            int bookID = borrow.getBookID();
            BookInfo bookInfo = this.bookDao.getBookInfoByID(bookID);
            borrowProfile.setBookID(bookID);
            borrowProfile.setBookName(bookInfo.getBookName());
            borrowProfile.setAuthor(bookInfo.getAuthor());
            borrowProfile.setCategory1(bookInfo.getCategory1().toString());
            borrowProfile.setImageID(bookInfo.getImageID());
            borrowProfile.setIsbn(bookInfo.getIsbn());
            borrowProfile.setBorrowPrice(bookInfo.getBorrowCredit());
            borrowProfile.setReturned(false);
            borrowProfile.setDelayed((borrow.getDelayCount()!=0));
            borrowProfile.setYhDate(borrow.getYhDate());
            borrowProfile.setReturnDate(null);
            borrowBook.add(borrowProfile);
        }
        for(BorrowHistory borrowHistory : borrowHistories) {
            BorrowProfile borrowProfileHistory = new BorrowProfile();
            int bookID = borrowHistory.getBookID();
            BookInfo bookInfo = this.bookDao.getBookInfoByID(bookID);
            borrowProfileHistory.setBookID(bookID);
            borrowProfileHistory.setBookName(bookInfo.getBookName());
            borrowProfileHistory.setAuthor(bookInfo.getAuthor());
            borrowProfileHistory.setCategory1(bookInfo.getCategory1().toString());
            borrowProfileHistory.setImageID(bookInfo.getImageID());
            borrowProfileHistory.setIsbn(bookInfo.getIsbn());
            borrowProfileHistory.setBorrowPrice(bookInfo.getBorrowCredit());
            borrowProfileHistory.setReturned(true);
            borrowProfileHistory.setDelayed((borrowHistory.getDelayCount()!=0));
            borrowProfileHistory.setYhDate(borrowHistory.getYhDate());
            borrowProfileHistory.setReturnDate(borrowHistory.getInDate());
            borrowHistoryBook.add(borrowProfileHistory);
        }
        Map result = new HashMap();
        result.put("borrowBook", borrowBook);
        result.put("borrowHistoryBook", borrowHistoryBook);
        return result;
    }
    
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
        this.borrowDao.update(borrow);
        return true;
    }
    
}