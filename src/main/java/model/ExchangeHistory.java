package model;

import java.util.Date;

import common.constants.ExchangeStatus;

public class ExchangeHistory {         // 准备交换并且已经被发布者确认的图书交换记录
	private Integer ehID;
    private Integer userID1;
    private Integer userID2;
    private Integer wantedBookID;           // 希望交换得到的书
    private Integer hadBookID;              // 交换发起者拥有的书
    private ExchangeStatus status;
    private Date applyDate;
    private Date responseDate;
    private String address1;
    private String address2;
    private Date fhDate1;
    private Date fhDate2;
    private String trackingNo1;    //申请人发货时快递单号
    private String trackingNo2;   //被申请者发货时快递单号 
    private Date shDate1;
    private Date shDate2;
    private Integer comment1;
    private Integer comment2;
    
    /* =================================================== */
    
    public ExchangeHistory (Exchange exchange)
    {
    	this.ehID = exchange.getExchangeID();
        this.userID1 = exchange.getUserID1();
        this.userID2 = exchange.getUserID2();
        this.wantedBookID = exchange.getWantedBookID();           // 希望交换得到的书
        this.hadBookID = exchange.getHadBookID();              // 交换发起者拥有的书
        this.applyDate = exchange.getApplyDate();
        this.responseDate = exchange.getResponseDate();
        this.address1 = exchange.getAddress1();
        this.address2 = exchange.getAddress2();
        this.fhDate1 = exchange.getFhDate1();
        this.fhDate2 = exchange.getFhDate2();
        this.trackingNo1 = exchange.getTrackingNo1();
        this.trackingNo2 = exchange.getTrackingNo2();
        this.shDate1 = exchange.getShDate1();
        this.shDate2 = exchange.getShDate2();
        this.comment1 = exchange.getComment1();
        this.comment2 = exchange.getComment2();
    }
    
	public Integer getEhID() {
		return ehID;
	}
	public void setEhID(Integer ehID) {
		this.ehID = ehID;
	}
	public Integer getUserID1() {
		return userID1;
	}
	public void setUserID1(Integer userID1) {
		this.userID1 = userID1;
	}
	public Integer getUserID2() {
		return userID2;
	}
	public void setUserID2(Integer userID2) {
		this.userID2 = userID2;
	}
	public Integer getWantedBookID() {
		return wantedBookID;
	}
	public void setWantedBookID(Integer wantedBookID) {
		this.wantedBookID = wantedBookID;
	}
	public Integer getHadBookID() {
		return hadBookID;
	}
	public void setHadBookID(Integer hadBookID) {
		this.hadBookID = hadBookID;
	}
	
	/**
	 * @return the applyDate
	 */
	public Date getApplyDate() {
		return applyDate;
	}
	/**
	 * @param applyDate the applyDate to set
	 */
	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}
	public Date getFhDate1() {
		return fhDate1;
	}
	public void setFhDate1(Date fhDate1) {
		this.fhDate1 = fhDate1;
	}
	public Date getFhDate2() {
		return fhDate2;
	}
	public void setFhDate2(Date fhDate2) {
		this.fhDate2 = fhDate2;
	}
	public Date getShDate1() {
		return shDate1;
	}
	public void setShDate1(Date shDate1) {
		this.shDate1 = shDate1;
	}
	public Date getShDate2() {
		return shDate2;
	}
	public void setShDate2(Date shDate2) {
		this.shDate2 = shDate2;
	}
	public int getComment1() {
		return comment1;
	}
	public void setComment1(int comment1) {
		this.comment1 = comment1;
	}
	public int getComment2() {
		return comment2;
	}
	public void setComment2(int comment2) {
		this.comment2 = comment2;
	}
	/**
	 * @return the responseDate
	 */
	public Date getResponseDate() {
		return responseDate;
	}
	/**
	 * @param responseDate the responseDate to set
	 */
	public void setResponseDate(Date responseDate) {
		this.responseDate = responseDate;
	}
	public ExchangeStatus getStatus() {
		return status;
	}
	public void setStatus(ExchangeStatus status) {
		this.status = status;
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