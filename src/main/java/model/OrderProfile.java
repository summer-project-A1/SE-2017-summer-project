package model;

import java.util.Date;

import common.constants.OrderStatus;

public class OrderProfile extends Order
{
	private String bookName;
	private String isbn;
    private String author;            // 作者
    private String press;             // 出版社
    private String category1;
    private String category2;
    private String imageID;
    private String email;
    private String buyerEmail;
    
    public OrderProfile() {
        
    }
    
	public OrderProfile(int buyerID, int sellerID, int bookID, Date orderDate, int price, OrderStatus status,
			String address) {
		super(buyerID, sellerID, bookID, orderDate, price, status, address);
		// TODO Auto-generated constructor stub
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPress() {
		return press;
	}

	public void setPress(String press) {
		this.press = press;
	}

	public String getCategory1() {
		return category1;
	}

	public void setCategory1(String category1) {
		this.category1 = category1;
	}

	public String getCategory2() {
		return category2;
	}

	public void setCategory2(String category2) {
		this.category2 = category2;
	}

	public String getImageID() {
		return imageID;
	}

	public void setImageID(String imageID) {
		this.imageID = imageID;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBuyerEmail() {
		return buyerEmail;
	}

	public void setBuyerEmail(String buyerEmail) {
		this.buyerEmail = buyerEmail;
	}
}
