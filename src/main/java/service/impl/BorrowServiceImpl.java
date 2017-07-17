package service.impl;

import java.util.ArrayList;
import java.util.Calendar;
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
import model.BookRelease;
import model.Borrow;
import model.BorrowHistory;
import model.BorrowProfile;
import model.User;
import service.BorrowService;

public class BorrowServiceImpl extends BaseServiceImpl implements BorrowService {
    private int borrowDay;     // 单次借阅时间，由spring注入
    private int delayDay;      // 单次续借时间，由spring注入
    
    private BookDao bookDao;
    private BookReleaseDao bookReleaseDao;
    private UserDao userDao;
    private BorrowDao borrowDao;
    private BorrowHistoryDao borrowHistoryDao;
    private ReserveDao reserveDao;
    
    /* ========================================================== */

    public int getBorrowDay() {
        return borrowDay;
    }

    public void setBorrowDay(int borrowDay) {
        this.borrowDay = borrowDay;
    }

    public int getDelayDay() {
        return delayDay;
    }

    public void setDelayDay(int delayDay) {
        this.delayDay = delayDay;
    }

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
        User user1 = getLoginedUserInfo();
        int userID = user1.getUserID();
        //List<BorrowProfile> borrowBook = (List<BorrowProfile>)borrowDao.getBorrowByUserID(userID);
        List<Borrow> borrows = this.borrowDao.getBorrowByUserID(userID);
        List<BorrowHistory> borrowHistories = this.borrowHistoryDao.getBorrowHistoryByUserID(userID);
        List<BorrowProfile> borrowBook = new ArrayList();
        List<BorrowProfile> borrowHistoryBook = new ArrayList();
        for(Borrow borrow : borrows) {
            //BorrowProfile borrowProfile = new BorrowProfile();
        	BorrowProfile borrowProfile = (BorrowProfile)borrow;
            int bookID = borrow.getBookID();
            Book book = this.bookDao.getBookByID(bookID);
            BookRelease bookRelease=bookReleaseDao.getReleaseBookByBookID(bookID);
            User user2=userDao.getUserById(bookRelease.getUserID());
            borrowProfile.setBookName(book.getBookName());
            borrowProfile.setIsbn(book.getIsbn());
            borrowProfile.setPress(book.getPress());
            borrowProfile.setAuthor(book.getAuthor());
            borrowProfile.setCategory1(book.getCategory1());
            borrowProfile.setCategory2(book.getCategory2());
            borrowProfile.setImageID(book.getImageID());
            borrowProfile.setEmail(user2.getEmail());
            borrowBook.add(borrowProfile);
        }
        for(BorrowHistory borrowHistory : borrowHistories) {
        	BorrowProfile borrowProfile = new BorrowProfile();
            int bookID = borrowHistory.getBookID();
            Book book = this.bookDao.getBookByID(bookID);
            BookRelease bookRelease=bookReleaseDao.getReleaseBookByBookID(bookID);
            User user2=userDao.getUserById(bookRelease.getUserID());
            borrowProfile.setBookID(bookID);
            borrowProfile.setBookName(book.getBookName());
            borrowProfile.setIsbn(book.getIsbn());
            borrowProfile.setPress(book.getPress());
            borrowProfile.setAuthor(book.getAuthor());
            borrowProfile.setCategory1(book.getCategory1());
            borrowProfile.setCategory2(book.getCategory2());
            borrowProfile.setImageID(book.getImageID());
            borrowProfile.setBorrowID(borrowHistory.getBhID());
            borrowProfile.setYhDate(borrowHistory.getYhDate());
            borrowProfile.setBorrowPrice(borrowHistory.getBorrowPrice());
            borrowProfile.setDelayCount(borrowHistory.getDelayCount());
            borrowProfile.setBorrowAddress(borrowHistory.getBorrowAddress());
            borrowProfile.setReturnAddress(borrowHistory.getReturnAddress());
            borrowProfile.setTrackingNo1(borrowHistory.getTrackingNo1());
            borrowProfile.setTrackingNo2(borrowHistory.getTrackingNo2());
            borrowProfile.setOrderDate(borrowHistory.getOrderDate());
            borrowProfile.setPayDate(borrowHistory.getPayDate());
            borrowProfile.setFhDate(borrowHistory.getFhDate());
            borrowProfile.setBorrowDate(borrowHistory.getBorrowDate());
            borrowProfile.setReturnAddress(borrowHistory.getReturnAddress());
            borrowProfile.setShDate(borrowHistory.getShDate());
            borrowProfile.setComment1(borrowHistory.getComment1());
            borrowProfile.setComment2(borrowHistory.getComment2());
            borrowProfile.setEmail(user2.getEmail());
            borrowBook.add(borrowProfile);
        }
        Map result = new HashMap();
        result.put("borrowBook", borrowBook);
        result.put("borrowHistoryBook", borrowHistoryBook);
        return result;
    }
    
    @Override
    public boolean borrowBook(int bookID) {
        Book book = this.bookDao.getBookByID(bookID);
        if(book==null || book.getStatus() != BookStatus.IDLE) {
            return false;
        }
        BookRelease bookRelease = this.bookReleaseDao.getReleaseBookByBookID(bookID);
        User user = getLoginedUserInfo();
        if(user.getCredit() < book.getBorrowCredit()) {
            return false;
        }
        user.setCredit(user.getCredit() - book.getBorrowCredit());
        this.userDao.update(user);
        Borrow newBorrow = new Borrow();
        newBorrow.setBookID(bookID);
        newBorrow.setUserID1(user.getUserID());
        newBorrow.setUserID2(bookRelease.getUserID());
        newBorrow.setBorrowDate(new Date());
        newBorrow.setBorrowPrice(book.getBorrowCredit());
        Calendar now = Calendar.getInstance();
        now.add(Calendar.DATE, this.borrowDay);
        Date yhDate = now.getTime();
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
        
        int totalNeededCredit = 0;        // 购物车中所有书总计需要的积分
        List<Book> allIdleBookInCart = new ArrayList();    // 临时保存空闲的书
        List<BookRelease> allIdleBookReleaseInCart = new ArrayList();    // 临时保存空闲的书
        List<Book> allNotIdleBookInCart = new ArrayList();    // 临时保存非空闲的书
        List<BookRelease> allNotIdleBookReleaseInCart = new ArrayList();    // 临时保存非空闲的书
        for(Map<String, Object> cartListItem : cartList) {
            int bookID = (int)cartListItem.get("bookID");
            Book book = this.bookDao.getBookByID(bookID);
            BookRelease bookRelease = this.bookReleaseDao.getReleaseBookByBookID(bookID);
            totalNeededCredit += book.getBorrowCredit();
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
                newBorrow.setUserID1(user.getUserID());
                newBorrow.setUserID2(bookRelease.getUserID());
                newBorrow.setBorrowDate(new Date());
                newBorrow.setBorrowPrice(book.getBorrowCredit());
                newBorrow.setYhDate(yhDate);
                newBorrow.setBorrowAddress(user.getAddress());
                newBorrow.setDelayCount(0);
                this.borrowDao.save(newBorrow);
                book.setStatus(BookStatus.BORROWED);
                this.bookDao.update(book);
            }
            getHttpSession().remove("borrowCart");
        }

        returnMap.put("result", result);
        returnMap.put("credit", creditNotEnough);
        returnMap.put("book", bookNotIdle);
        return returnMap;
    }

    @Override
    public Map returnBook(int borrowID) {
        Map returnMap = new HashMap();
        if(!isLogined()) {
            returnMap.put("success", false);
            return returnMap;
        }
        User user = getLoginedUserInfo();
        Borrow borrow = this.borrowDao.getBorrowByID(borrowID);
        if(borrow.getUserID1() != user.getUserID()) {
            returnMap.put("success", false);
            return returnMap;
        }
        int bookID = borrow.getBookID();
        Book book = this.bookDao.getBookByID(bookID);
        if(book.getStatus() != BookStatus.BORROWED) {
            returnMap.put("success", false);
            return returnMap;
        }
        book.setStatus(BookStatus.IDLE);
        Date returnDate = new Date();
        BorrowHistory newBorrowHistory = new BorrowHistory();
        newBorrowHistory.setBookID(bookID);
        newBorrowHistory.setBorrowDate(borrow.getBorrowDate());
        newBorrowHistory.setBorrowPrice(borrow.getBorrowPrice());
        newBorrowHistory.setReturnDate(returnDate);
        newBorrowHistory.setUserID1(user.getUserID());
        newBorrowHistory.setYhDate(borrow.getYhDate());
        newBorrowHistory.setBorrowAddress(borrow.getBorrowAddress());
        this.bookDao.update(book);
        this.borrowHistoryDao.save(newBorrowHistory);
        this.borrowDao.delete(borrow);
        returnMap.put("success", true);
        returnMap.put("returnDate", returnDate);
        return returnMap;
    }

    @Override
    public Map delayBook(int borrowID) {
        Map returnMap = new HashMap();
        if(!isLogined()) {
            returnMap.put("success", false);
            return returnMap;
        }
        User user = getLoginedUserInfo();
        Borrow borrow = this.borrowDao.getBorrowByID(borrowID);
        if(borrow.getUserID1() != user.getUserID()) {
            returnMap.put("success", false);
            return returnMap;
        }
        Date oldYhDate = borrow.getYhDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(oldYhDate);
        calendar.add(Calendar.DATE, this.delayDay);
        Date newYhDate = calendar.getTime();
        borrow.setYhDate(newYhDate);
        borrow.setDelayCount(borrow.getDelayCount()+1);
        this.borrowDao.update(borrow);
        returnMap.put("success", true);
        returnMap.put("yhdate", newYhDate);
        return returnMap;
    }
    
}