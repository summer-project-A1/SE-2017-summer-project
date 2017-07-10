package model;

import java.util.Date;

public class Borrow {                // 保存已被借出但尚未还回的借书记录
    private int borrowID;
    private int userID;
    private int bookID;
    private Date borrowDate;     // 借书时间
    private Date yhDate;         // 应还时间
    private int borrowPrice;         // 借书花费的积分
    private int delayCount;        // 延期次数（目前设定最多1次）
    private String province;        // 收货地址
    private String city;
    private String district;
    private String address;
    
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
    public int getDelayCount() {
        return delayCount;
    }
    public void setDelayCount(int delayCount) {
        this.delayCount = delayCount;
    }
    public String getProvince() {
        return province;
    }
    public void setProvince(String province) {
        this.province = province;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getDistrict() {
        return district;
    }
    public void setDistrict(String district) {
        this.district = district;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
}