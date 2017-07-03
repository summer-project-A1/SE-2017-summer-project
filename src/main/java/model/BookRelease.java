package model;

import java.util.Calendar;

public class BookRelease {             // 用户发布书的信息
    private int releaseID;
    private int userID;
    private int bookID;
    private Calendar releaseTime;      // 发布时间
    private int borrowPrice;           // 设定借书需要的积分
    private int exchangePrice;         // 设定交换书需要的积分
    
    /* ========================================================== */
    
    public int getReleaseID() {
        return releaseID;
    }
    public void setReleaseID(int releaseID) {
        this.releaseID = releaseID;
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
    public Calendar getReleaseTime() {
        return releaseTime;
    }
    public void setReleaseTime(Calendar releaseTime) {
        this.releaseTime = releaseTime;
    }
    public int getBorrowPrice() {
        return borrowPrice;
    }
    public void setBorrowPrice(int borrowPrice) {
        this.borrowPrice = borrowPrice;
    }
    public int getExchangePrice() {
        return exchangePrice;
    }
    public void setExchangePrice(int exchangePrice) {
        this.exchangePrice = exchangePrice;
    }
    
}
