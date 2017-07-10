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
    public boolean borrowBook(int bookID, Date yhDate) {
        if(!isLogined()) {
            return false;
        }
        Book book = this.bookDao.getBookByID(bookID);
        if(book==null || book.getStatus() != BookStatus.IDLE) {
            return false;
        }
        BookRelease bookRelease = this.bookReleaseDao.getReleaseBookByBookID(bookID);
        User user = getLoginedUserInfo();
        if(user.getCredit() < bookRelease.getBorrowCredit()) {
            return false;
        }
        user.setCredit(user.getCredit() - bookRelease.getBorrowCredit());
        this.userDao.update(user);
        Borrow newBorrow = new Borrow();
        newBorrow.setBookID(bookID);
        newBorrow.setUserID(user.getUserID());
        newBorrow.setBorrowDate(new Date());
        newBorrow.setBorrowPrice(bookRelease.getBorrowCredit());
        newBorrow.setYhDate(yhDate);
        this.borrowDao.save(newBorrow);
        book.setStatus(BookStatus.BORROWED);
        this.bookDao.update(book);
        return true;
    }
    
    @Override
    public Map borrowAllBookInBorrowCart() {
        Map returnMap = new HashMap();    // 返回值
        if(!isLogined()) {
            returnMap.put("result", false);
        }
        User user = this.getLoginedUserInfo();
        List<Map<String, Object>> cartList;
        List resultList = new ArrayList();
        if(getHttpSession().containsKey("borrowCart")) {
            cartList = (List<Map<String, Object>>)getHttpSession().get("borrowCart");
        }
        else {
            cartList = new ArrayList<Map<String, Object>>();
        }
        
        int totalNeededCredit = 0;
        List<Book> allIdleBookInCart = new ArrayList();    // 临时保存空闲的书
        List<BookRelease> allIdleBookReleaseInCart = new ArrayList();    // 临时保存空闲的书
        List<Book> allNotIdleBookInCart = new ArrayList();    // 临时保存非空闲的书
        List<BookRelease> allNotIdleBookReleaseInCart = new ArrayList();    // 临时保存非空闲的书
        for(Map<String, Object> cartListItem : cartList) {
            int bookID = (int)cartListItem.get("bookID");
            Book book = this.bookDao.getBookByID(bookID);
            BookRelease bookRelease = this.bookReleaseDao.getReleaseBookByBookID(bookID);
            totalNeededCredit += bookRelease.getBorrowCredit();
            if(book.getStatus().equals(BookStatus.IDLE)) {
                allIdleBookInCart.add(book);
                allIdleBookReleaseInCart.add(bookRelease);
            }
            else {
                allNotIdleBookInCart.add(book);
                allNotIdleBookReleaseInCart.add(bookRelease);
            }
        }
        
        boolean result = true;
        boolean creditNotEnough = false;
        boolean bookNotIdle = false;
        
        // 只有用户积分能够完成购物车的整体支付才能继续
        if(user.getCredit() < totalNeededCredit) {
            result = false;
            creditNotEnough = true;
        }
        // 如果某本书被买走，则失败
        if(!allNotIdleBookInCart.isEmpty()) {
            result = false;
            bookNotIdle = true;
        }
        
        if(result) {
            Date yhDate = new Date();
            user.setCredit(user.getCredit() - totalNeededCredit);
            this.userDao.update(user);
            for(int i=0; i<allIdleBookInCart.size(); i++) {
                Book book = allIdleBookInCart.get(i);
                BookRelease bookRelease = allIdleBookReleaseInCart.get(i);
                Borrow newBorrow = new Borrow();
                newBorrow.setBookID(book.getBookID());
                newBorrow.setUserID(user.getUserID());
                newBorrow.setBorrowDate(new Date());
                newBorrow.setBorrowPrice(bookRelease.getBorrowCredit());
                newBorrow.setYhDate(yhDate);
                this.borrowDao.save(newBorrow);
                book.setStatus(BookStatus.BORROWED);
                this.bookDao.update(book);
            }
            getHttpSession().remove("borrowCart");
            returnMap.put("result", true);
        }
        else {
            returnMap.put("result", false);
        }
        returnMap.put("result", result);
        returnMap.put("credit", creditNotEnough);
        returnMap.put("book", bookNotIdle);
        return returnMap;
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