package service.impl;

import java.util.Date;
import java.util.List;

import dao.BookDao;
import dao.ReserveDao;
import model.Book;
import model.Reserve;
import model.User;
import service.ReserveService;

public class ReserveServiceImpl extends BaseServiceImpl implements ReserveService {

    private ReserveDao reserveDao;
    private BookDao bookDao;
    
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

    /* ============================================================ */

    @Override
    public String reserveBook(int bookID) {  // 预约书
        User user = this.getLoginedUserInfo();
        int userID = user.getUserID();
        
        if(this.reserveDao.isReserved(userID, bookID)) {
            return "already";
        }
        
        Book book = this.bookDao.getBookByID(bookID);
        book.setReserved(book.getReserved()+1);
        this.bookDao.update(book);
        Reserve newReserve = new Reserve();
        newReserve.setBookID(bookID);
        newReserve.setUserID(userID);
        newReserve.setDue(new Date());
        this.reserveDao.save(newReserve);
        return "success";
    }

    @Override
    public List<Reserve> showReservation() {
        User user = this.getLoginedUserInfo();
        int userID = user.getUserID();
        return this.reserveDao.getReservationByUserID(userID);
    }

    @Override
    public Boolean cancelReservation(int applyID) {
        Reserve reserve = this.reserveDao.getReserveByID(applyID);
        Book book = this.bookDao.getBookByID(reserve.getBookID());
        book.setReserved(book.getReserved()-1);
        this.bookDao.update(book);
        this.reserveDao.delete(reserve);
        return true;
    }
    
}