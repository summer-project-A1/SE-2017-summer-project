package model;

import java.util.Date;

public class Reserve {           // 图书预约
    private int reserveID;
    private int userID;
    private int bookID;
    private Date due;      // 预约的时间
    
    /* ====================================================== */
    
    public int getReserveID() {
        return reserveID;
    }
    public void setReserveID(int reserveID) {
        this.reserveID = reserveID;
    }
    public int getUserID() {
        return userID;
    }
    public void setUserID(int userID) {
        this.userID = userID;
    }
    public int getBookID() {
        return bookID;
    }
    public void setBookID(int bookID) {
        this.bookID = bookID;
    }
    public Date getDue() {
        return due;
    }
    public void setDue(Date due) {
        this.due = due;
    }
    
}