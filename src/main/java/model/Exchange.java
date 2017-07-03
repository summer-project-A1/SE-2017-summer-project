package model;

import java.util.Calendar;

public class Exchange {             // 准备交换但尚未被发布者确认的图书交换记录
    private int exchangeID;
    private int userID;
    private int wantedID;           // 希望交换得到的书
    private int hadID;              // 交换发起者拥有的书
    private Calendar applyDate;
    
    /* =================================================== */
    
    public int getExchangeID() {
        return exchangeID;
    }
    public void setExchangeID(int exchangeID) {
        this.exchangeID = exchangeID;
    }
    public int getUserID() {
        return userID;
    }
    public void setUserID(int userID) {
        this.userID = userID;
    }
    public int getWantedID() {
        return wantedID;
    }
    public void setWantedID(int wantedID) {
        this.wantedID = wantedID;
    }
    public int getHadID() {
        return hadID;
    }
    public void setHadID(int hadID) {
        this.hadID = hadID;
    }
    public Calendar getApplyDate() {
        return applyDate;
    }
    public void setApplyDate(Calendar applyDate) {
        this.applyDate = applyDate;
    }
    
}