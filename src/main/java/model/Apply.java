package model;

import java.util.Calendar;

public class Apply {           // 延期借阅申请
    private int applyID;
    private int userID;
    private int bookID;
    private Calendar due;      // 延期后的时间
    
    /* ====================================================== */
    
    public int getApplyID() {
        return applyID;
    }
    public void setApplyID(int applyID) {
        this.applyID = applyID;
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
    public Calendar getDue() {
        return due;
    }
    public void setDue(Calendar due) {
        this.due = due;
    }
    
}