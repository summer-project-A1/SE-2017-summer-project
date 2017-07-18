package service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.constants.BookStatus;
import common.constants.BorrowStatus;
import dao.*;
import model.Book;
import model.BookRelease;
import model.Borrow;
import model.BorrowHistory;
import model.BorrowProfile;
import model.User;
import model.Comment;
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
    private CommentDao commentDao;
    
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

    public CommentDao getCommentDao() {
        return commentDao;
    }

    public void setCommentDao(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    /* ============================================================= */
    
    @Override
    public Map showMyBorrow() {
        // Map中包含两个List<BorrowProfile>，分别对应Borrow和BorrowHistory
        User user1 = getLoginedUserInfo();
        int userID = user1.getUserID();
        //List<BorrowProfile> borrowBook = (List<BorrowProfile>)borrowDao.getBorrowByUserID(userID);
        List<Borrow> borrows = this.borrowDao.getBorrowByBorrowUserID(userID);  // 用户借来书的记录
        List<BorrowHistory> borrowHistories = this.borrowHistoryDao.getBorrowHistoryByBorrowUserID(userID);
        List<BorrowProfile> borrowBook = new ArrayList();
        List<BorrowProfile> borrowHistoryBook = new ArrayList();
        if(borrows != null) {
            for (Borrow borrow : borrows) {
                BorrowProfile borrowProfile = new BorrowProfile();
                //BorrowProfile borrowProfile = (BorrowProfile)borrow;
                int bookID = borrow.getBookID();
                Book book = this.bookDao.getBookByID(bookID);
                BookRelease bookRelease = bookReleaseDao.getReleaseBookByBookID(bookID);
                User user2 = userDao.getUserById(bookRelease.getUserID());
                borrowProfile.setBorrowID(borrow.getBorrowID());
                borrowProfile.setBookID(borrow.getBookID());
                borrowProfile.setUserID1(borrow.getUserID1());
                borrowProfile.setUserID2(borrow.getUserID2());
                borrowProfile.setYhDate(borrow.getYhDate());
                borrowProfile.setBorrowCredit(borrow.getBorrowCredit());
                borrowProfile.setDelayCount(borrow.getDelayCount());
                borrowProfile.setStatus(borrow.getStatus());
                borrowProfile.setBorrowStatus(borrow.getStatus().toString());
                borrowProfile.setReturnAddress(borrow.getReturnAddress());
                borrowProfile.setTrackingNo1(borrow.getTrackingNo1());
                borrowProfile.setOrderDate(borrow.getOrderDate());
                borrowProfile.setPayDate(borrow.getPayDate());
                borrowProfile.setFhDate(borrow.getFhDate());
                borrowProfile.setBorrowDate(borrow.getBorrowDate());
                borrowProfile.setReturnDate(borrow.getReturnDate());

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
        }
        if(borrowHistories != null) {
            for (BorrowHistory borrowHistory : borrowHistories) {
                BorrowProfile borrowProfile = new BorrowProfile();
                int bookID = borrowHistory.getBookID();
                Book book = this.bookDao.getBookByID(bookID);
                BookRelease bookRelease = bookReleaseDao.getReleaseBookByBookID(bookID);
                User user2 = userDao.getUserById(bookRelease.getUserID());
                Comment comment = this.commentDao.getCommentsByCommentID(borrowHistory.getBhID());
                boolean bookComment=false;
                if(comment != null){ bookComment=true;}
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
                borrowProfile.setBorrowCredit(borrowHistory.getBorrowPrice());
                borrowProfile.setDelayCount(borrowHistory.getDelayCount());
                borrowProfile.setBorrowAddress(borrowHistory.getBorrowAddress());
                borrowProfile.setReturnAddress(borrowHistory.getReturnAddress());
                borrowProfile.setTrackingNo1(borrowHistory.getTrackingNo1());
                borrowProfile.setTrackingNo2(borrowHistory.getTrackingNo2());
                borrowProfile.setOrderDate(borrowHistory.getOrderDate());
                borrowProfile.setPayDate(borrowHistory.getPayDate());
                borrowProfile.setFhDate(borrowHistory.getFhDate());
                borrowProfile.setBorrowDate(borrowHistory.getBorrowDate());
                borrowProfile.setReturnDate(borrowHistory.getReturnDate());
                borrowProfile.setShDate(borrowHistory.getShDate());
                borrowProfile.setComment1(borrowHistory.getComment1());
                borrowProfile.setComment2(borrowHistory.getComment2());
                borrowProfile.setEmail(user2.getEmail());
                borrowProfile.setBookComment(bookComment);
                borrowHistoryBook.add(borrowProfile);
            }
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
        newBorrow.setBorrowCredit(book.getBorrowCredit());
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
    public Map createBorrowOrder(String fullAddress) {
        /*
         * 用户创建订单，添加borrow到数据库，不验证/修改书的状态，不验证用户积分
         */
        Map returnMap = new HashMap();    // 返回值
        User user = this.getLoginedUserInfo();
        List<Map<String, Object>> cartList;
        List<BorrowProfile> borrowProfileList = new ArrayList();
        int totalNeededCredit = 0;  // 所需总积分
        if(getHttpSession().containsKey("borrowCart")) {
            cartList = (List<Map<String, Object>>)getHttpSession().get("borrowCart");
        }
        else {
            cartList = new ArrayList<Map<String, Object>>();
        }
        
        for(Map<String, Object> cartListItem : cartList) {
            int bookID = (int)cartListItem.get("bookID");
            Book book = this.bookDao.getBookByID(bookID);
            BookRelease bookRelease = this.bookReleaseDao.getReleaseBookByBookID(bookID);
            totalNeededCredit += book.getBorrowCredit();     
            
            Borrow newBorrow = new Borrow();
            newBorrow.setBookID(book.getBookID());
            newBorrow.setUserID1(user.getUserID());  // 借书人，买家
            newBorrow.setUserID2(bookRelease.getUserID());  // 被借人，卖家
            newBorrow.setOrderDate(new Date());
            newBorrow.setBorrowCredit(book.getBorrowCredit());
            newBorrow.setBorrowAddress(fullAddress);  // 买家收货地址
            newBorrow.setDelayCount(0);   // 延期次数：0
            newBorrow.setStatus(BorrowStatus.BUYER_NOTPAID);
            this.borrowDao.save(newBorrow);
            
            BorrowProfile newBorrowProfile = new BorrowProfile();
            newBorrowProfile.setBorrowID(newBorrow.getBorrowID());
            newBorrowProfile.setBookID(newBorrow.getBookID());
            newBorrowProfile.setImageID(book.getImageID());
            newBorrowProfile.setIsbn(book.getIsbn());
            newBorrowProfile.setBorrowStatus(newBorrow.getStatus().toString());
            newBorrowProfile.setAuthor(book.getAuthor());
            newBorrowProfile.setCategory1(book.getCategory1());
            newBorrowProfile.setCategory2(book.getCategory2());
            newBorrowProfile.setBorrowCredit(book.getBorrowCredit());
            borrowProfileList.add(newBorrowProfile);
        }
        getHttpSession().remove("borrowCart");
        
        returnMap.put("borrowProfileList", borrowProfileList);
        returnMap.put("totalCredit", totalNeededCredit);
        return returnMap;
    }

    @Override
    public Map confirmBorrowOrder(List<Integer> borrowIDList) {
        // 用户付款确认订单（允许多个订单），修改订单状态，验证并修改书的状态，验证并修改用户积分
        Map returnMap = new HashMap();
        User user = this.getLoginedUserInfo();
        int totalCredit = 0;        // 所有书总计需要的积分
        List<Book> allIdleBook = new ArrayList();    // 临时保存空闲的书
        List<BookRelease> allIdleBookRelease = new ArrayList();    // 临时保存空闲的书
        List<Borrow> allSuccessBorrow = new ArrayList();   // 保存所有能够成功的borrow
        List<Book> allNotIdleBook = new ArrayList();    // 临时保存非空闲的书
        List<BookRelease> allNotIdleBookRelease = new ArrayList();    // 临时保存非空闲的书
        List<Borrow> allFailBorrow = new ArrayList();   // 保存所有能够成功的borrow
        
        for(Integer borrowID : borrowIDList) {
            Borrow borrow = this.borrowDao.getBorrowByID(borrowID);
            Integer bookID = borrow.getBookID();
            Book book = this.bookDao.getBookByID(bookID);
            BookRelease bookRelease = this.bookReleaseDao.getReleaseBookByBookID(bookID);
            totalCredit += borrow.getBorrowCredit();
            if(book.getStatus().equals(BookStatus.IDLE) && book.getReserved()==0) {  // 书是空闲的并且没有被预约
                allIdleBook.add(book);
                allIdleBookRelease.add(bookRelease);
                allSuccessBorrow.add(borrow);
            }
            else {
                allNotIdleBook.add(book);
                allNotIdleBookRelease.add(bookRelease);
                allFailBorrow.add(borrow);
            }
        }
        
        boolean result = true;
        boolean creditNotEnough = false;
        boolean bookNotIdle = false;
        
        // 只有用户积分能够完成本批订单的整体支付才能继续
        if(user.getCredit() < totalCredit) {
            result = false;
            creditNotEnough = true;
        }
        
        // 如果某本书已被买走/借走/交换等，则失败
        if(!allNotIdleBook.isEmpty()) {
            result = false;
            bookNotIdle = true;
        }
        
        Date payDate = new Date();
        if(result) {
            user.setCredit(user.getCredit() - totalCredit);
            this.userDao.update(user);
            for(int i=0; i<allSuccessBorrow.size(); i++) {
                Book book = allIdleBook.get(i);
                BookRelease bookRelease = allIdleBookRelease.get(i);
                Borrow borrow = allSuccessBorrow.get(i);
                User lender = this.userDao.getUserById(borrow.getUserID2()); // 借出书的人（卖家）
                lender.setCredit(lender.getCredit() + borrow.getBorrowCredit());
                this.userDao.update(lender);
                borrow.setStatus(BorrowStatus.SELLER_NOT_SHIPPED);
                borrow.setPayDate(payDate);
                this.borrowDao.update(borrow);
                book.setStatus(BookStatus.BORROWED);
                this.bookDao.update(book);
            }
        }
        returnMap.put("success", result);
        returnMap.put("payDate", payDate);
        return returnMap;
    }
    
    @Override
    public Map returnBook(int borrowID,String trackingNo1) {
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
        //book.setStatus(BookStatus.IDLE); 此时不改书的状态，待卖家确认收到后再改
        Date returnDate = new Date();
        /*
        BorrowHistory newBorrowHistory = new BorrowHistory();
        newBorrowHistory.setBookID(bookID);
        newBorrowHistory.setBorrowDate(borrow.getBorrowDate());
        newBorrowHistory.setBorrowPrice(borrow.getBorrowCredit());
        newBorrowHistory.setReturnDate(returnDate);
        newBorrowHistory.setUserID1(user.getUserID());
        newBorrowHistory.setYhDate(borrow.getYhDate());
        newBorrowHistory.setBorrowAddress(borrow.getBorrowAddress());
        this.bookDao.update(book);
        this.borrowHistoryDao.save(newBorrowHistory);
        this.borrowDao.delete(borrow);
        */  //买家还书后，添加还书日期和还书快递单号，改变借阅状态，等待卖家确认
        borrow.setReturnDate(returnDate);
        borrow.setTrackingNo1(trackingNo1);
        borrow.setStatus(BorrowStatus.BUYER_RETURNED);
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

    @Override
    public Map confirmBorrowReceipt(int borrowID){
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
        Date borrowDate = new Date();
        borrow.setBorrowDate(borrowDate); //收货时间，即借书时间

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(borrowDate);
        calendar.add(Calendar.DATE, this.borrowDay);
        Date YhDate = calendar.getTime();
        borrow.setYhDate(YhDate);   //应还时间
        borrow.setStatus(BorrowStatus.BUYER_NOT_RETURNED);
        this.borrowDao.update(borrow);
        returnMap.put("success",true);
        returnMap.put("yhDate",YhDate);
        returnMap.put("borrowDate",borrowDate);
        returnMap.put("returnAddress",borrow.getReturnAddress());
        return returnMap;
    }

    @Override
    public List<BorrowProfile> getLendBookList() {
        List<BorrowProfile> borrowProfileList = new ArrayList<BorrowProfile>();
        User user = this.getLoginedUserInfo();
        int userID = user.getUserID();
        List<Borrow> borrowList = this.borrowDao.getBorrowByLendUserID(userID);
        if(borrowList != null) {
            for (Borrow borrow : borrowList) {
                Book book = this.bookDao.getBookByID(borrow.getBookID());
                BorrowProfile borrowProfile = new BorrowProfile();
                borrowProfile.setBorrowID(borrow.getBorrowID());
                borrowProfile.setBookID(borrow.getBookID());
                borrowProfile.setUserID1(borrow.getUserID1());
                borrowProfile.setUserID2(borrow.getUserID2());
                borrowProfile.setYhDate(borrow.getYhDate());
                borrowProfile.setBorrowCredit(borrow.getBorrowCredit());
                borrowProfile.setDelayCount(borrow.getDelayCount());
                borrowProfile.setStatus(borrow.getStatus());
                borrowProfile.setBorrowStatus(borrow.getStatus().toString());
                borrowProfile.setReturnAddress(borrow.getReturnAddress());
                borrowProfile.setTrackingNo1(borrow.getTrackingNo1());
                borrowProfile.setOrderDate(borrow.getOrderDate());
                borrowProfile.setPayDate(borrow.getPayDate());
                borrowProfile.setFhDate(borrow.getFhDate());
                borrowProfile.setBorrowDate(borrow.getBorrowDate());
                borrowProfile.setReturnDate(borrow.getReturnDate());

                borrowProfile.setBookName(book.getBookName());
                borrowProfile.setIsbn(book.getIsbn());
                borrowProfile.setPress(book.getPress());
                borrowProfile.setAuthor(book.getAuthor());
                borrowProfile.setCategory1(book.getCategory1());
                borrowProfile.setCategory2(book.getCategory2());
                borrowProfile.setImageID(book.getImageID());
                borrowProfile.setEmail(user.getEmail());
                borrowProfileList.add(borrowProfile);
            }
        }
        return borrowProfileList;
    }
    @Override
    public List<BorrowProfile> getLendBookHistoryList() {
        List<BorrowProfile> borrowProfileList = new ArrayList<BorrowProfile>();
        User user = this.getLoginedUserInfo();
        int userID = user.getUserID();
        List<BorrowHistory> borrowHistoryList = this.borrowHistoryDao.getBorrowHistoryByLendUserID(userID);
        if(borrowHistoryList != null) {
            for (BorrowHistory borrowHistory : borrowHistoryList) {
                User buyer = this.userDao.getUserById(borrowHistory.getUserID1());
                Book book = this.bookDao.getBookByID(borrowHistory.getBookID());
                BorrowProfile borrowProfile = new BorrowProfile();
                borrowProfile.setBookID(book.getBookID());
                borrowProfile.setBookName(book.getBookName());
                borrowProfile.setIsbn(book.getIsbn());
                borrowProfile.setPress(book.getPress());
                borrowProfile.setAuthor(book.getAuthor());
                borrowProfile.setCategory1(book.getCategory1());
                borrowProfile.setCategory2(book.getCategory2());
                borrowProfile.setImageID(book.getImageID());
                borrowProfile.setBorrowID(borrowHistory.getBhID());
                borrowProfile.setYhDate(borrowHistory.getYhDate());
                borrowProfile.setBorrowCredit(borrowHistory.getBorrowPrice());
                borrowProfile.setDelayCount(borrowHistory.getDelayCount());
                borrowProfile.setBorrowAddress(borrowHistory.getBorrowAddress());
                borrowProfile.setReturnAddress(borrowHistory.getReturnAddress());
                borrowProfile.setTrackingNo1(borrowHistory.getTrackingNo1());
                borrowProfile.setTrackingNo2(borrowHistory.getTrackingNo2());
                borrowProfile.setOrderDate(borrowHistory.getOrderDate());
                borrowProfile.setPayDate(borrowHistory.getPayDate());
                borrowProfile.setFhDate(borrowHistory.getFhDate());
                borrowProfile.setBorrowDate(borrowHistory.getBorrowDate());
                borrowProfile.setReturnDate(borrowHistory.getReturnDate());
                borrowProfile.setShDate(borrowHistory.getShDate());
                borrowProfile.setComment1(borrowHistory.getComment1());
                borrowProfile.setComment2(borrowHistory.getComment2());
                borrowProfile.setEmail(user.getEmail());
                borrowProfile.setBuyerEmail(buyer.getEmail());
                borrowProfile.setBookComment(false);
                borrowProfileList.add(borrowProfile);
            }
        }
        return borrowProfileList;
    }

    @Override
    public Map deliveryBorrowOrder(int borrowID,String trackingNo2,String returnAddress){
        Map returnMap = new HashMap();
        if(!isLogined()) {
            returnMap.put("success", false);
            return returnMap;
        }
        User user = getLoginedUserInfo();
        Borrow borrow = this.borrowDao.getBorrowByID(borrowID);
        if(borrow.getUserID2() != user.getUserID()){
            returnMap.put("success",false);
            return returnMap;
        }
        Date fhDate = new Date();
        borrow.setTrackingNo2(trackingNo2);
        borrow.setReturnAddress(returnAddress);
        borrow.setFhDate(fhDate);
        borrow.setStatus(BorrowStatus.SELLER_SHIPPED);
        this.borrowDao.update(borrow);
        returnMap.put("success",true);
        returnMap.put("fhDate",fhDate);
        return returnMap;
    }

    @Override
    public Map confirmReturnReceipt(int borrowID){
        Map returnMap = new HashMap();
        if(!isLogined()) {
            returnMap.put("success", false);
            return returnMap;
        }
        User user = getLoginedUserInfo();
        Borrow borrow = this.borrowDao.getBorrowByID(borrowID);
        if(borrow.getUserID2() != user.getUserID()){
            returnMap.put("success",false);
            return returnMap;
        }
        Date shDate = new Date();
        borrow.setShDate(shDate);
        borrow.setStatus(BorrowStatus.COMPLETED); //先更新borrow的信息
        this.borrowDao.update(borrow);

        Book book = this.bookDao.getBookByID(borrow.getBookID());
        book.setStatus(BookStatus.IDLE);    //卖家收货后，修改图书信息为空闲
        this.bookDao.update(book);

        BorrowHistory borrowHistory = new BorrowHistory();
        borrowHistory.setBhID(borrow.getBorrowID());
        borrowHistory.setUserID1(borrow.getUserID1());
        borrowHistory.setUserID2(borrow.getUserID2());
        borrowHistory.setBookID(borrow.getBookID());
        borrowHistory.setYhDate(borrow.getYhDate());
        borrowHistory.setBorrowPrice(borrow.getBorrowCredit());
        borrowHistory.setDelayCount(borrow.getDelayCount());
        borrowHistory.setBorrowAddress(borrow.getBorrowAddress());
        borrowHistory.setReturnAddress(borrow.getReturnAddress());
        borrowHistory.setTrackingNo1(borrow.getTrackingNo1());
        borrowHistory.setTrackingNo2(borrow.getTrackingNo2());
        borrowHistory.setOrderDate(borrow.getOrderDate());
        borrowHistory.setPayDate(borrow.getPayDate());
        borrowHistory.setFhDate(borrow.getFhDate());
        borrowHistory.setBorrowDate(borrow.getBorrowDate());
        borrowHistory.setReturnDate(borrow.getReturnDate());
        borrowHistory.setShDate(borrow.getShDate());
        borrowHistory.setComment1(borrow.getComment1());
        borrowHistory.setComment2(borrow.getComment2());

        this.borrowHistoryDao.save(borrowHistory);
        this.borrowDao.delete(borrow);

        returnMap.put("success",true);
        returnMap.put("shDate",shDate);
        return returnMap;
    }

}