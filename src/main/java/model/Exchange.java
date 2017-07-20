package model;

import java.util.Date;

import common.constants.ExchangeStatus;

public class Exchange {             // 准备交换但尚未被发布者确认的图书交换记录
    private int exchangeID;
    private int userID1;     //申请交换人的id
    private int userID2;     //被申请人的id
    private int wantedID;           // 希望交换得到的书
    private int hadID;              // 交换发起者拥有的书
    private ExchangeStatus status;
    private Date applyDate;   //申请人申请时间
    private Date responseDate;  //被申请人回应时间
    private String address1;    // 申请人收货地址
    private String address2;    // 被申请人收货地址
    private Date fh_date1;    //申请人发货时间
    private Date fh_date2;    //被申请人发货时间
    private String trackingNo1;    //申请人发货时快递单号
    private String trackingNo2;   //被申请者发货时快递单号 
    private Date sh_date1;    //申请人收货时间
    private Date sh_date2;    //被申请者收货时间
    private Integer comment1;     //申请人对被申请者的信用评价
    private Integer comment2;     //被申请人对申请人的信用评价
    
    /* =================================================== */
    public Exchange(int userID1, int userID2, int wantedID, int hadID, ExchangeStatus status, Date date, String address1) {
		super();
		this.userID1 = userID1;
		this.userID2 = userID2;
		this.wantedID = wantedID;
		this.hadID = hadID;
		this.status = status;
		this.applyDate = date;
		this.address1 = address1;
	}
    
	public int getExchangeID() {
		return exchangeID;
	}
	
	public void setExchangeID(int exchangeID) {
		this.exchangeID = exchangeID;
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
	public ExchangeStatus getStatus() {
		return status;
	}
	public void setStatus(ExchangeStatus status) {
		this.status = status;
	}
	
	public Date getApplyDate() {
		return applyDate;
	}
	
	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}
	public Date getFh_date1() {
		return fh_date1;
	}
	public void setFh_date1(Date fh_date1) {
		this.fh_date1 = fh_date1;
	}
	public Date getFh_date2() {
		return fh_date2;
	}
	public void setFh_date2(Date fh_date2) {
		this.fh_date2 = fh_date2;
	}
	public Date getSh_date1() {
		return sh_date1;
	}
	public void setSh_date1(Date sh_date1) {
		this.sh_date1 = sh_date1;
	}
	public Date getSh_date2() {
		return sh_date2;
	}
	public void setSh_date2(Date sh_date2) {
		this.sh_date2 = sh_date2;
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
	
	public Date getResponseDate() {
		return responseDate;
	}
	
	public void setResponseDate(Date responseDate) {
		this.responseDate = responseDate;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
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
    
}