package model;

import java.util.Date;

public class BorrowHistory {                // 保存已被借出但尚未还回的借书记录
    private int bhID;
    private int userID1;
    private int userID2;
    private int bookID;
    private Date yhDate;         // 应还时间
    private int borrowPrice;         // 借书花费的积分
    private int delayCount;     // 延期次数（目前设定最多1次）
    private String borrowAddress;
    private String returnAddress;
    private String trackingNo1;
    private String trackingNo2;
    private Date orderDate;
    private Date payDate;    //付款时间
    private Date fhDate;   //卖家发货时间
    private Date borrowDate;   //买家确认收货时间，即借书时间
    private Date returnDate;   //买家还书时间
    private Date shDate;  //卖家收货时间
    private Integer comment1;
    private Integer comment2;
    
    /* =============================================================== */
    
	public int getBhID() {
		return bhID;
	}
	public void setBhID(int bhID) {
		this.bhID = bhID;
	}
	
	public int getUserID1() {
		return userID1;
	}
	public void setUserID1(int userID1) {
		this.userID1 = userID1;
	}
	public int getUserID2() {
		return userID2;
	}
	public void setUserID2(int userID2) {
		this.userID2 = userID2;
	}
	public int getBookID() {
		return bookID;
	}
	public void setBookID(int bookID) {
		this.bookID = bookID;
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
	
	public String getBorrowAddress() {
		return borrowAddress;
	}
	public void setBorrowAddress(String borrowAddress) {
		this.borrowAddress = borrowAddress;
	}
	public String getReturnAddress() {
		return returnAddress;
	}
	public void setReturnAddress(String returnAddress) {
		this.returnAddress = returnAddress;
	}
	public String getTrackingNo1() {
		return trackingNo1;
	}
	public void setTrackingNo1(String trackingNo1) {
		this.trackingNo1 = trackingNo1;
	}
	public String getTrackingNo2() {
		return trackingNo2;
	}
	public void setTrackingNo2(String trackingNo2) {
		this.trackingNo2 = trackingNo2;
	}
	public Date getPayDate() {
		return payDate;
	}
	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}
	public Date getFhDate() {
		return fhDate;
	}
	public void setFhDate(Date fhDate) {
		this.fhDate = fhDate;
	}
	
	public Date getBorrowDate() {
		return borrowDate;
	}
	public void setBorrowDate(Date borrowDate) {
		this.borrowDate = borrowDate;
	}
	public Date getReturnDate() {
		return returnDate;
	}
	public Integer getComment1() {
		return comment1;
	}
	public void setComment1(Integer comment1) {
		this.comment1 = comment1;
	}
	public Integer getComment2() {
		return comment2;
	}
	public void setComment2(Integer comment2) {
		this.comment2 = comment2;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	public Date getShDate() {
		return shDate;
	}
	public void setShDate(Date shDate) {
		this.shDate = shDate;
	}
	/**
	 * @return the orderDate
	 */
	public Date getOrderDate() {
		return orderDate;
	}
	/**
	 * @param orderDate the orderDate to set
	 */
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
    
}
