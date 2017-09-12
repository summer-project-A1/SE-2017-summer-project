package service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.constants.BookStatus;
import common.constants.ExchangeStatus;
import common.utils.SendEmail;
import dao.BookDao;
import dao.BookReleaseDao;
import dao.ExchangeDao;
import dao.ExchangeHistoryDao;
import dao.ReserveDao;
import dao.UserDao;

import model.Book;
import model.BookRelease;
import model.Exchange;
import model.ExchangeHistory;
import model.ExchangeProfile;
import model.Reserve;
import model.User;

import service.ExchangeService;

public class ExchangeServiceImpl extends BaseServiceImpl implements ExchangeService
{
    private UserDao userDao;
    private BookDao bookDao;
    private BookReleaseDao bookReleaseDao;
    private ExchangeDao exchangeDao;
    private ExchangeHistoryDao exchangeHistoryDao;
    private ReserveDao reserveDao;
    private int reserveDay;    // 单次预约时间，由spring注入
    /*=========================================================*/
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
    public UserDao getUserDao(){
        return this.userDao;
    }

    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public BookDao getBookDao(){
        return this.bookDao;
    }

    public void setBookReleaseDao(BookReleaseDao bookReleaseDao) {
        this.bookReleaseDao = bookReleaseDao;
    }

    public BookReleaseDao getBookReleaseDao(){
        return this.bookReleaseDao;
    }

    public void setExchangeDao(ExchangeDao exchangeDao) {
        this.exchangeDao = exchangeDao;
    }


    public ExchangeDao getExchangeDao(){
        return this.exchangeDao;
    }

    public void setExchangeHistoryDao(ExchangeHistoryDao exchangeHistoryDao) {
        this.exchangeHistoryDao = exchangeHistoryDao;
    }

    public ExchangeHistoryDao getExchangeHistoryDao(){
        return this.exchangeHistoryDao;
    }

    public ReserveDao getReserveDao() {
        return reserveDao;
    }
    public void setReserveDao(ReserveDao reserveDao) {
        this.reserveDao = reserveDao;
    }
    public int getReserveDay() {
        return reserveDay;
    }
    public void setReserveDay(int reserveDay) {
        this.reserveDay = reserveDay;
    }
    /*==================================================================*/
    @Override
    public Map prepareExchange(int wantedBookID)
    {
        // 跳转到交换界面
        Map map = new HashMap();
        User user = this.getLoginedUserInfo();
        Book wantedBook = bookDao.getBookByID(wantedBookID);
        map.put("wantedBook", wantedBook);
        List<Book> userReleasedBookList = bookReleaseDao.getReleaseBookByUserID(user.getUserID());
        //System.out.println(userReleasedBookList.size()+"???");
        map.put("userReleasedBookList", userReleasedBookList);
        return map;
    }

    @Override
    public Boolean applyExchange(int wantedBookID, int hadBookID, String address)
    {
        // 发出交换申请，修改两本书的状态，锁定它们
        User user1 = this.getLoginedUserInfo();
        Book wanted = bookDao.getBookByID(wantedBookID);
        Book had = bookDao.getBookByID(hadBookID);
        if (wanted.getStatus() != BookStatus.IDLE)
            return false;
        if (had.getStatus() != BookStatus.IDLE)
            return false;
        if(wanted.getReserved()>0)    // 如果有预约
        {
            Reserve reserve = reserveDao.getFirstReserveByBookID(wanted.getBookID());
            if(reserve.getUserID() != user1.getUserID())
                return false;    // 如果第一个预约者不是自己，拒绝
            reserveDao.delete(reserve);    // 删除自己的预约记录
            wanted.setReserved(wanted.getReserved()-1);    // 书的预约人数减1
        }
        BookRelease bookRelease = bookReleaseDao.getReleaseBookByBookID(wanted.getBookID());
        User user2 = userDao.getUserById(bookRelease.getUserID());
        int userID1 = user1.getUserID();
        int userID2 = user2.getUserID();
        Date date = new Date();
        Exchange exchange = new Exchange(userID1,userID2,wantedBookID,hadBookID,ExchangeStatus.WAITING,date,address);
        exchangeDao.save(exchange);
        wanted.setStatus(BookStatus.EXCHANGED);
        had.setStatus(BookStatus.EXCHANGED);
        bookDao.update(wanted);
        bookDao.update(had);
        return true;
    }

    @Override
    public Boolean cancelExchange(int exchangeID)
    {
        // 取消发出的交换申请
        Exchange exchange = exchangeDao.getExchangeByID(exchangeID);
        if (exchange.getStatus() != ExchangeStatus.WAITING)
            return false;
        Book wanted = bookDao.getBookByID(exchange.getWantedBookID());
        if (wanted.getStatus() != BookStatus.EXCHANGED)
            return false;
        Book had = bookDao.getBookByID(exchange.getHadBookID());
        if (had.getStatus() != BookStatus.EXCHANGED)
            return false;
        wanted.setStatus(BookStatus.IDLE);
        had.setStatus(BookStatus.IDLE);
        bookDao.update(wanted);
        bookDao.update(had);
        ExchangeHistory exchangeHistory = new ExchangeHistory(exchange);
        exchangeHistory.setStatus(ExchangeStatus.CANCELED);
        exchangeHistory.setResponseDate(new Date());
        exchangeHistory.setComment1(0);
		exchangeHistory.setComment2(0);
        exchangeHistoryDao.save(exchangeHistory);
        exchangeDao.delete(exchange);
        
        if(wanted.getReserved()>0)    // 如果仍有其他人预约（注意自己的预约记录已在applyExchange中处理）
        {
            Reserve newFirstReserve = this.reserveDao.getFirstReserveByBookID(wanted.getBookID());
            Calendar calendar2 = Calendar.getInstance();
            calendar2.add(Calendar.DATE, this.reserveDay);
            Date due = calendar2.getTime();
            newFirstReserve.setDue(due);    // 开始下一个人的预约有效期倒计时
            this.reserveDao.update(newFirstReserve);
            User reserveUser = this.userDao.getUserById(newFirstReserve.getUserID());
            SendEmail sendEmail = new SendEmail();
            String emailContent = "您预约的图书:"+wanted.getBookName()+"已到货，请速去借阅，若超过"+due+"将取消预约！";
            String emailSubject = "到货通知";
            sendEmail.send(emailContent,emailSubject,reserveUser.getEmail());
        }
        return true;
    }

    @Override
    public Boolean agreeExchange(int exchangeID,String address)
    {
        // 同意对方发来的交换申请
        Exchange exchange = exchangeDao.getExchangeByID(exchangeID);
        if(exchange.getStatus() != ExchangeStatus.WAITING)
            return false;
        exchange.setStatus(ExchangeStatus.AGREED);
        exchange.setResponseDate(new Date());
        exchange.setAddress2(address);
        exchangeDao.update(exchange);
        
        // 书已被交换，后续的预约记录全部作废
        Integer wantedBookID = exchange.getWantedBookID();
        Book wantedBook = this.bookDao.getBookByID(wantedBookID);
        wantedBook.setReserved(0);    
        List<Reserve> reserveList = this.reserveDao.getReservationByBookID(wantedBookID);
        for(Reserve reserve : reserveList) {    // 不包括当前用户的预约（当前用户的预约已在applyExchange中处理）
            int userID = reserve.getUserID();
            User reserver = this.userDao.getUserById(userID);
                   
            SendEmail sendEmail = new SendEmail();
            String emailContent = "您预约的图书:"+wantedBook.getBookName()+"已被交换或购买。预约已自动取消。";
            String emailSubject = "预约取消";
            sendEmail.send(emailContent,emailSubject,reserver.getEmail());
            this.reserveDao.delete(reserve);
        }
        return true;
    }

    @Override
    public Boolean rejectExchange(int exchangeID)
    {
        // 拒绝对方发来的交换申请
        Exchange exchange = exchangeDao.getExchangeByID(exchangeID);
        if(exchange.getStatus() != ExchangeStatus.WAITING)
            return false;
        Book wanted = bookDao.getBookByID(exchange.getWantedBookID());
        if (wanted.getStatus() != BookStatus.EXCHANGED)
            return false;
        Book had = bookDao.getBookByID(exchange.getHadBookID());
        if (had.getStatus() != BookStatus.EXCHANGED)
            return false;
        wanted.setStatus(BookStatus.IDLE);
        had.setStatus(BookStatus.IDLE);
        bookDao.update(wanted);
        bookDao.update(had);
        exchange.setResponseDate(new Date());
        ExchangeHistory exchangeHistory = new ExchangeHistory(exchange);
        exchangeHistory.setStatus(ExchangeStatus.REJECTED);
        exchangeHistory.setComment1(0);
		exchangeHistory.setComment2(0);
        exchangeHistoryDao.save(exchangeHistory);
        exchangeDao.delete(exchange);
        if(wanted.getReserved()>0)    // 如果仍有其他人预约（注意当前用户的的预约已在applyExchange中处理）
        {
            //开启下一个人的定时任务
            Reserve newFirstReserve = this.reserveDao.getFirstReserveByBookID(wanted.getBookID());
            Calendar calendar2 = Calendar.getInstance();
            calendar2.add(Calendar.DATE, this.reserveDay);
            Date due = calendar2.getTime();
            newFirstReserve.setDue(due);    // 开始下一个人的预约有效期倒计时
            this.reserveDao.update(newFirstReserve);
            User reserveUser = this.userDao.getUserById(newFirstReserve.getUserID());
            SendEmail sendEmail = new SendEmail();
            String emailContent = "您预约的图书:"+wanted.getBookName()+"已到货，请速去借阅，若超过"+due+"将取消预约！";
            String emailSubject = "到货通知";
            sendEmail.send(emailContent,emailSubject,reserveUser.getEmail());
        }
        return true;
    }

    @Override
    public Boolean fh1(int exchangeID,String trackingNo)
    {
        Exchange exchange = exchangeDao.getExchangeByID(exchangeID);
        if(exchange.getStatus() != ExchangeStatus.AGREED)
            return false;
        Date date = new Date();
        exchange.setTrackingNo1(trackingNo);
        exchange.setFhDate1(date);
        exchangeDao.update(exchange);
        return true;
    }

    @Override
    public Boolean fh2(int exchangeID,String trackingNo)
    {
        Exchange exchange = exchangeDao.getExchangeByID(exchangeID);
        if(exchange.getStatus() != ExchangeStatus.AGREED)
            return false;
        Date date = new Date();
        exchange.setTrackingNo2(trackingNo);
        exchange.setFhDate2(date);
        exchangeDao.update(exchange);
        return true;
    }

    @Override
    public Boolean sh1(int exchangeID)
    {
        Exchange exchange = exchangeDao.getExchangeByID(exchangeID);
        if(exchange.getStatus() != ExchangeStatus.AGREED)
            return false;
        Date date = new Date();
        exchange.setShDate1(date);
        if (exchange.getShDate2() == null)
            exchangeDao.update(exchange);
        else
        {
            ExchangeHistory exchangeHistory = new ExchangeHistory(exchange);
            exchangeHistory.setStatus(ExchangeStatus.COMPLETED);
            exchangeHistoryDao.save(exchangeHistory);
            exchangeDao.delete(exchange);
        }
        return true;
    }

    @Override
    public Boolean sh2(int exchangeID)
    {
        Exchange exchange = exchangeDao.getExchangeByID(exchangeID);
        if(exchange.getStatus() != ExchangeStatus.AGREED)
            return false;
        Date date = new Date();
        exchange.setShDate2(date);
        if (exchange.getShDate1() == null)
            exchangeDao.update(exchange);
        else
        {
            ExchangeHistory exchangeHistory = new ExchangeHistory(exchange);
            exchangeHistory.setStatus(ExchangeStatus.COMPLETED);
            exchangeHistoryDao.save(exchangeHistory);
            exchangeDao.delete(exchange);
        }
        return true;
    }

    /*
    @Override
    public Boolean comment1(int exchangeID, int comment)
    {
        Exchange exchange = exchangeDao.getExchangeByID(exchangeID);
        if(exchange.getStatus() != ExchangeStatus.AGREED)
            return false;
        exchange.setComment1(comment);
        User user = userDao.getUserById(exchange.getUserID2());
        user.setHonesty(user.getHonesty()+comment);
        userDao.update(user);
        if (exchange.getComment2() == null)
            exchangeDao.update(exchange);
        else
        {
            ExchangeHistory exchangeHistory = new ExchangeHistory(exchange);
            exchangeHistory.setStatus(ExchangeStatus.COMPLETED);
            exchangeHistoryDao.save(exchangeHistory);
            exchangeDao.delete(exchange);
        }
        return true;
    }

    @Override
    public Boolean comment2(int exchangeID, int comment)
    {
        Exchange exchange = exchangeDao.getExchangeByID(exchangeID);
        if(exchange.getStatus() != ExchangeStatus.AGREED)
            return false;
        exchange.setComment1(comment);
        User user = userDao.getUserById(exchange.getUserID1());
        user.setHonesty(user.getHonesty()+comment);
        userDao.update(user);
        if (exchange.getComment1() == null)
            exchangeDao.update(exchange);
        else
        {
            ExchangeHistory exchangeHistory = new ExchangeHistory(exchange);
            exchangeHistory.setStatus(ExchangeStatus.COMPLETED);
            exchangeHistoryDao.save(exchangeHistory);
            exchangeDao.delete(exchange);
        }
        return true;
    }
    */

    @Override
    public Map showMyExchange() {
        User user = this.getLoginedUserInfo();
        Integer userID = user.getUserID();
        
        List<Exchange> activeExchange = this.exchangeDao.getExchangeByUserID1(userID);  // 主动发起的交换申请
        List<ExchangeHistory> activeExchangeHistory = this.exchangeHistoryDao.getExchangeHistoryByUserID1(userID); 
        List<Exchange> passiveExchange = this.exchangeDao.getExchangeByUserID2(userID);  // 收到的交换申请
        List<ExchangeHistory> passiveExchangeHistory = this.exchangeHistoryDao.getExchangeHistoryByUserID2(userID);
        
        List<ExchangeProfile> activeExchangeProfile = new ArrayList<ExchangeProfile>();
        List<ExchangeProfile> activeExchangeHistoryProfile = new ArrayList<ExchangeProfile>();
        List<ExchangeProfile> passiveExchangeProfile = new ArrayList<ExchangeProfile>();
        List<ExchangeProfile> passiveExchangeHistoryProfile = new ArrayList<ExchangeProfile>();
        
        for(Exchange exchange : activeExchange) {
            Integer wantedBookID = exchange.getWantedBookID();
            Integer hadBookID = exchange.getHadBookID();
            Book wantedBook = this.bookDao.getBookByID(wantedBookID);
            Book hadBook = this.bookDao.getBookByID(hadBookID);
            ExchangeProfile exchangeProfile = new ExchangeProfile(exchange,wantedBook,hadBook);
            activeExchangeProfile.add(exchangeProfile);
        }
        for(ExchangeHistory exchangeHistory : activeExchangeHistory) {
            Integer wantedBookID = exchangeHistory.getWantedBookID();
            Integer hadBookID = exchangeHistory.getHadBookID();
            Book wantedBook = this.bookDao.getBookByID(wantedBookID);
            Book hadBook = this.bookDao.getBookByID(hadBookID);
            ExchangeProfile exchangeProfile = new ExchangeProfile(exchangeHistory,wantedBook,hadBook);
            activeExchangeHistoryProfile.add(exchangeProfile);
        }
        for(Exchange exchange : passiveExchange) {
            Integer wantedBookID = exchange.getWantedBookID();
            Integer hadBookID = exchange.getHadBookID();
            Book wantedBook = this.bookDao.getBookByID(wantedBookID);
            Book hadBook = this.bookDao.getBookByID(hadBookID);
            ExchangeProfile exchangeProfile = new ExchangeProfile(exchange,wantedBook,hadBook);
            passiveExchangeProfile.add(exchangeProfile);
        }
        for(ExchangeHistory exchangeHistory : passiveExchangeHistory) {
            Integer wantedBookID = exchangeHistory.getWantedBookID();
            Integer hadBookID = exchangeHistory.getHadBookID();
            Book wantedBook = this.bookDao.getBookByID(wantedBookID);
            Book hadBook = this.bookDao.getBookByID(hadBookID);
            ExchangeProfile exchangeProfile = new ExchangeProfile(exchangeHistory,wantedBook,hadBook);
            passiveExchangeHistoryProfile.add(exchangeProfile);
        }
        
        Map<String, List<ExchangeProfile>> returnMap = new HashMap<String, List<ExchangeProfile>>();
        returnMap.put("activeExchange", activeExchangeProfile);
        returnMap.put("activeExchangeHistory", activeExchangeHistoryProfile);
        returnMap.put("passiveExchange", passiveExchangeProfile);
        returnMap.put("passiveExchangeHistory", passiveExchangeHistoryProfile);
        return returnMap;
    }
}
