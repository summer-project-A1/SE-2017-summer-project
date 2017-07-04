package model;

import java.util.Date;

public class Borrow {                // 保存已被借出但尚未还回的借书记录
    private int borrowID;
    private int userID;
    private int bookID;
    private Date borrowDate;     // 借书时间
    private Date yhDate;         // 应还时间
    private int borrowPrice;         // 借书花费的积分
    
    /* =============================================================== */
    
    public int getBorrowID() {
        return borrowID;
    }
    public void setBorrowID(int borrowID) {
        this.borrowID = borrowID;
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
    public Date getBorrowDate() {
        return borrowDate;
    }
    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }
    public Date getYhDate() {
        return yhDate;
    }
    public void setYhDate(Date yhDate) {
        this.yhDate = yhDate;
    }
    public int getBorrowPrice() {
        return borrowPrice;
    }
    public void setBorrowPrice(int borrowPrice) {
        this.borrowPrice = borrowPrice;
    }
    
}