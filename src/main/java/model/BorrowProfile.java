package model;

import java.util.Date;

public class BorrowProfile {
    private int borrowID;         // 对应Borrow类的borrowID或BorrowHistory类的borrowHistoryID
    private int bookID;           // 图书id
    private String bookName;      // 书名
    private String author;        // 作者
    private String category1;     // 大分类
    private String imageID;       // 图片id
    private String isbn;          // isbn
    private int borrowPrice;      // 借阅积分
    private Boolean returned;     // 是否归还了
    private Boolean delayed;      // 是否延过期
    private Date yhDate;          // 应还日期
    private Date returnDate;      // 归还日期
    
    /* ========================================================= */

    public int getBorrowID() {
        return borrowID;
    }
    public void setBorrowID(int borrowID) {
        this.borrowID = borrowID;
    }
    public int getBookID() {
        return bookID;
    }
    public void setBookID(int bookID) {
        this.bookID = bookID;
    }
    public String getBookName() {
        return bookName;
    }
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getCategory1() {
        return category1;
    }
    public void setCategory1(String category1) {
        this.category1 = category1;
    }
    public String getImageID() {
        return imageID;
    }
    public void setImageID(String imageID) {
        this.imageID = imageID;
    }
    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    public int getBorrowPrice() {
        return borrowPrice;
    }
    public void setBorrowPrice(int borrowPrice) {
        this.borrowPrice = borrowPrice;
    }
    public Boolean getReturned() {
        return returned;
    }
    public void setReturned(Boolean returned) {
        this.returned = returned;
    }
    public Boolean getDelayed() {
        return delayed;
    }
    public void setDelayed(Boolean delayed) {
        this.delayed = delayed;
    }
    public Date getYhDate() {
        return yhDate;
    }
    public void setYhDate(Date yhDate) {
        this.yhDate = yhDate;
    }
    public Date getReturnDate() {
        return returnDate;
    }
    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

}