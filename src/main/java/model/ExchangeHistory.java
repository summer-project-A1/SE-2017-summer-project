package model;

import java.util.Date;

import common.constants.ExchangeStatus;

public class ExchangeHistory {         // 准备交换并且已经被发布者确认的图书交换记录
	private int ehID;
    private int userID1;
    private int userID2;
    private int wantedID;           // 希望交换得到的书
    private int hadID;              // 交换发起者拥有的书
    private ExchangeStatus status;
    private Date applyDate;
    private Date responseDate;
    private String address1;
    private String address2;
    private Date fh_date1;
    private Date fh_date2;
    private String trackingNo1;    //申请人发货时快递单号
    private String trackingNo2;   //被申请者发货时快递单号 
    private Date sh_date1;
    private Date sh_date2;
    private int comment1;
    private int comment2;
    
    /* =================================================== */
    
    public ExchangeHistory (Exchange exchange)
    {
    	this.ehID = exchange.getExchangeID();
        this.userID1 = exchange.getUserID1();
        this.userID2 = exchange.getUserID2();
        this.wantedID = exchange.getWantedID();           // 希望交换得到的书
        this.hadID = exchange.getHadID();              // 交换发起者拥有的书
        this.applyDate = exchange.getApplyDate();
        this.responseDate = exchange.getResponseDate();
        this.address1 = exchange.getAddress1();
        this.address2 = exchange.getAddress2();
        this.fh_date1 = exchange.getFh_date1();
        this.fh_date2 = exchange.getFh_date2();
        trackingNo1 = exchange.getTrackingNo1();
        trackingNo2 = exchange.getTrackingNo2();
        sh_date1 = exchange.getSh_date1();
        sh_date2 = exchange.getSh_date2();
        comment1 = exchange.getComment1();
        comment2 = exchange.getComment2();
    }
    
	public int getEhID() {
		return ehID;
	}
	public void setEhID(int ehID) {
		this.ehID = ehID;
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