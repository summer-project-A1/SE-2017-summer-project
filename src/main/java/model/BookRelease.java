package model;

import java.util.Date;

public class BookRelease {             // 用户发布书的信息
    private int releaseID;
    private int userID;
    private int bookID;
    private Date releaseTime;      // 发布时间
    private int borrowCredit;           // 设定借书需要的积分
    private int buyCredit;         // 设定购买书需要的积分
    
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
    public Date getReleaseTime() {
        return releaseTime;
    }
    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }
    public int getBorrowCredit() {
        return borrowCredit;
    }
    public void setBorrowCredit(int borrowCredit) {
        this.borrowCredit = borrowCredit;
    }
    public int getBuyCredit() {
        return buyCredit;
    }
    public void setBuyCredit(int buyCredit) {
        this.buyCredit = buyCredit;
    }
    
}
