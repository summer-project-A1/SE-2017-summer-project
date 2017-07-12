package model;

import java.util.Date;
import common.constants.OrderStatus;

public class Order {               // 使用积分下单购买书
    private int orderID;
    private int buyerID;
    private int sellerID;
    private Date orderDate;
    private int price;
    private OrderStatus status;
    private String address;
    private Date fhDate;
    private Date shDate;
    private String trackingNo;
    private int buyer_comment;
    
    public Order(int buyerID, int sellerID, Date orderDate, int price, OrderStatus status, String address) {
		super();
		this.buyerID = buyerID;
		this.sellerID = sellerID;
		this.orderDate = orderDate;
		this.price = price;
		this.status = status;
		this.address = address;
	}
	private int seller_comment;
    
    /* ================================================= */
    
	public int getOrderID() {
		return orderID;
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
	public int getBuyer_comment() {
		return buyer_comment;
	}
	public void setBuyer_comment(int buyer_comment) {
		this.buyer_comment = buyer_comment;
	}
	public int getSeller_comment() {
		return seller_comment;
	}
	public void setSeller_comment(int seller_comment) {
		this.seller_comment = seller_comment;
	}
    
}