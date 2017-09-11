package service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.BookDao;
import dao.BookReleaseDao;
import dao.ReserveDao;
import dao.UserDao;
import model.*;
import service.ReserveService;

/*
 * 预约
 * 
 * 规则：对于借阅（borrow），用户点击借阅时验证预约状态，但直到用户支付积分完成订单（或者因超时未操作）时才删除自己的预约记录，并把预约交给下一个人（设定其预约过期时间）
 *       对于交换（exchange），用户点击交换时验证预约状态并删除自己的预约记录。交换申请如果被同意，删除后面所有的预约记录并邮件通知；如果被拒绝或主动取消（包括超时未操作），删除自己的预约记录，并把预约交给下一个人
 *       对于购买（order），用户点击购买时验证预约状态，但直到用户支付积分完成订单,删除后面所有的预约记录并邮件通知（如果超时未操作，则仅删除自己的预约记录，并把预约交给下一个人）
 *       
 * 删除自己的预约记录应与改变书的状态同时进行
 */
public class ReserveServiceImpl extends BaseServiceImpl implements ReserveService {

    private ReserveDao reserveDao;
    private BookDao bookDao;
    private BookReleaseDao bookReleaseDao;
    private UserDao userDao;
    
    /* ============================================================ */
    
    public ReserveDao getReserveDao() {
        return reserveDao;
    }
    public void setReserveDao(ReserveDao reserveDao) {
        this.reserveDao = reserveDao;
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
    /* ============================================================ */

    @Override
    public String reserveBook(int bookID) {  // 预约书
        User user = this.getLoginedUserInfo();
        int userID = user.getUserID();
        
        if(this.reserveDao.isReserved(userID, bookID)) {
            return "already";
        }

        Book book = this.bookDao.getBookByID(bookID);
        BookRelease bookRelease = this.bookReleaseDao.getReleaseBookByBookID(bookID);
        User Owner = this.userDao.getUserById(bookRelease.getUserID());
        if(Owner.getUserID() == userID){
            return "error";
        }
        book.setReserved(book.getReserved()+1);
        this.bookDao.update(book);
        Reserve newReserve = new Reserve();
        newReserve.setBookID(bookID);
        newReserve.setUserID(userID);
        newReserve.setDue(null);    // due为null的预约不会过期，在书恢复为空闲状态时才开始计算预约有效期
        this.reserveDao.save(newReserve);
        return "success";
    }

    @Override
    public List<ReservationProfile> showReservation() {
        User user = this.getLoginedUserInfo();
        int userID = user.getUserID();
        List<ReservationProfile> reservationProfileList = new ArrayList<>();
        List<Reserve> reservationList = this.reserveDao.getReservationByUserID(userID);
        if(reservationList != null){
            for(Reserve reserve : reservationList){
                Book book = this.bookDao.getBookByID(reserve.getBookID());
                BookRelease bookRelease = this.bookReleaseDao.getReleaseBookByBookID(book.getBookID());
                User owner = this.userDao.getUserById(bookRelease.getUserID());

                ReservationProfile reservationProfile = new ReservationProfile();
                reservationProfile.setReserveID(reserve.getReserveID());
                reservationProfile.setUserID(reserve.getUserID());
                reservationProfile.setBookID(reserve.getBookID());
                reservationProfile.setDue(reserve.getDue());
                reservationProfile.setBookName(book.getBookName());
                reservationProfile.setAuthor(book.getAuthor());
                reservationProfile.setBorrowCredit(book.getBorrowCredit());
                reservationProfile.setIsbn(book.getIsbn());
                reservationProfile.setPress(book.getPress());
                reservationProfile.setCategory1(book.getCategory1());
                reservationProfile.setCategory2(book.getCategory2());
                reservationProfile.setImageID(book.getImageID());
                reservationProfile.setReserveAmt(book.getReserved());
                reservationProfile.setStatus(book.getStatus());
                reservationProfile.setBookStatus(book.getStatus().toString());

                reservationProfile.setOwnerEmail(owner.getEmail());
                reservationProfileList.add(reservationProfile);
            }
        }
        return reservationProfileList;
    }

    @Override
    public Boolean cancelReservation(int reserveID) {
        Reserve reserve = this.reserveDao.getReserveByID(reserveID);
        Book book = this.bookDao.getBookByID(reserve.getBookID());
        book.setReserved(book.getReserved()-1);
        this.bookDao.update(book);
        this.reserveDao.delete(reserve);
        return true;
    }


}