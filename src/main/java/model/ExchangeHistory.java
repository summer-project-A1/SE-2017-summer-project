package model;

import java.util.Calendar;

public class ExchangeHistory {         // 准备交换并且已经被发布者确认的图书交换记录
    private int ehID;
    private int user1ID;
    private int user2ID;
    private int book1ID;
    private int book2ID;
    private Calendar exchangeDate;     // 申请交换的时间
    private Calendar applyDate;        // 发布者确认的时间
    
    /* ========================================================== */
    
    public int getEhID() {
        return ehID;
    }
    public void setEhID(int ehID) {
        this.ehID = ehID;
    }
    public int getUser1ID() {
        return user1ID;
    }
    public void setUser1ID(int user1id) {
        user1ID = user1id;
    }
    public int getUser2ID() {
        return user2ID;
    }
    public void setUser2ID(int user2id) {
        user2ID = user2id;
    }
    public int getBook1ID() {
        return book1ID;
    }
    public void setBook1ID(int book1id) {
        book1ID = book1id;
    }
    public int getBook2ID() {
        return book2ID;
    }
    public void setBook2ID(int book2id) {
        book2ID = book2id;
    }
    public Calendar getExchangeDate() {
        return exchangeDate;
    }
    public void setExchangeDate(Calendar exchangeDate) {
        this.exchangeDate = exchangeDate;
    }
    public Calendar getApplyDate() {
        return applyDate;
    }
    public void setApplyDate(Calendar applyDate) {
        this.applyDate = applyDate;
    }
    
}