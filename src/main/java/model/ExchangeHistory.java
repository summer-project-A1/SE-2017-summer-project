package model;

import java.sql.Date;

public class ExchangeHistory {         // 准备交换并且已经被发布者确认的图书交换记录
	private int ehID;
    private int userID1;
    private int userID2;
    private int wantedID;           // 希望交换得到的书
    private int hadID;              // 交换发起者拥有的书
    private Date applyDate;
    private Date responseDate;
    private Date fh_date1;
    private Date fh_date2;
    private Date sh_date1;
    private Date sh_date2;
    private int comment1;
    private int comment2;
    
    /* =================================================== */
    
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
}