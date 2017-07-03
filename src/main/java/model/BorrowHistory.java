package model;

import java.util.Calendar;

public class BorrowHistory {         // 保存已被借出并已还回的借书记录
    private int bhID;
    private int userID;
    private int bookID;
    private Calendar borrowDate;     // 借书时间
    private Calendar yhDate;         // 应还时间
    private Calendar inDate;         // 实际还书时间
    private int borrowPrice;         // 借书花费的积分
    
    /* =============================================================== */
    
    public int getBhID() {
        return bhID;
    }
    public void setBhID(int bhID) {
        this.bhID = bhID;
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
    public Calendar getBorrowDate() {
        return borrowDate;
    }
    public void setBorrowDate(Calendar borrowDate) {
        this.borrowDate = borrowDate;
    }
    public Calendar getYhDate() {
        return yhDate;
    }
    public void setYhDate(Calendar yhDate) {
        this.yhDate = yhDate;
    }
    public Calendar getInDate() {
        return inDate;
    }
    public void setInDate(Calendar inDate) {
        this.inDate = inDate;
    }
    public int getBorrowPrice() {
        return borrowPrice;
    }
    public void setBorrowPrice(int borrowPrice) {
        this.borrowPrice = borrowPrice;
    }
    
}
