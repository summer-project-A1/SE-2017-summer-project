package model;

import java.util.Date;
import common.constants.OrderStatus;

public class Order {               // 使用积分下单购买书
    private int orderID;
    private int buyerID;
    private int sellerID;
    private int bookID;
    private Date orderDate;
    private Date payDate;
    private int price;
    private OrderStatus status;
    private String address;
    private Date fhDate;
    private Date shDate;
	private String trackingNo;
	private int buyerComment;
	private int sellerComment;
    
    /* ================================================= */
	
	
    
	public int getOrderID() {
		return orderID;
	}
	public Order(int buyerID, int sellerID, int bookID, Date orderDate, int price, OrderStatus status, String address) {
		super();
		this.buyerID = buyerID;
		this.sellerID = sellerID;
		this.bookID = bookID;
		this.orderDate = orderDate;
		this.price = price;
		this.status = status;
		this.address = address;
	}
	
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	public int getBuyerID() {
		return buyerID;
	}
	public void setBuyerID(int buyerID) {
		this.buyerID = buyerID;
	}
	public int getSellerID() {
		return sellerID;
	}
	public void setSellerID(int sellerID) {
		this.sellerID = sellerID;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public OrderStatus getStatus() {
		return status;
	}
	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getFhDate() {
		return fhDate;
	}
	public void setFhDate(Date fhDate) {
		this.fhDate = fhDate;
	}
	public Date getShDate() {
		return shDate;
	}
	public void setShDate(Date shDate) {
		this.shDate = shDate;
	}
	public String getTrackingNo() {
		return trackingNo;
	}
	public void setTrackingNo(String trackingNo) {
		this.trackingNo = trackingNo;
	}
	
	public Date getPayDate() {
		return payDate;
	}
	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	/**
	 * @return the bookID
	 */
	public int getBookID() {
		return bookID;
	}

	/**
	 * @param bookID the bookID to set
	 */
	public void setBookID(int bookID) {
		this.bookID = bookID;
	}
    
	public int getBuyerComment() {
		return buyerComment;
	}
	public void setBuyerComment(int buyerComment) {
		this.buyerComment = buyerComment;
	}
	public int getSellerComment() {
		return sellerComment;
	}
	public void setSellerComment(int sellerComment) {
		this.sellerComment = sellerComment;
	}
}