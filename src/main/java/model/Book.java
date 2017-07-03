package model;

import common.constants.BookStatus;

public class Book {
    private int bookID;
    private String bookName;
    private String isbn;
    private String author;            // 作者
    private String press;             // 出版社
    private String category;
    private int canExchange;      // 用户发布时决定书是否可被交换
    private int canBorrow;        // 用户发布时决定书是否可被借阅
    private int reserved;         // 书当前是否已被预约 
    private BookStatus status;               // 书当前状态（空闲/正被借阅/正被交换/正被售出）
    private String profileID;         // 书的详细信息在monggodb中的id
    private String imageID;           // 书的图片在monggodb中的图片
    
    /* ============================================================== */
    
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
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public int getCanExchange() {
        return canExchange;
    }
    public void setCanExchange(int canExchange) {
        this.canExchange = canExchange;
    }
    public int getCanBorrow() {
        return canBorrow;
    }
    public void setCanBorrow(int canBorrow) {
        this.canBorrow = canBorrow;
    }
    public int getReserved() {
        return reserved;
    }
    public void setReserved(int reserved) {
        this.reserved = reserved;
    }
    public BookStatus getStatus() {
        return status;
    }
    public void setStatus(BookStatus status) {
        this.status = status;
    }
    public String getProfileID() {
        return profileID;
    }
    public void setProfileID(String profileID) {
        this.profileID = profileID;
    }
    public String getImageID() {
        return imageID;
    }
    public void setImageID(String imageID) {
        this.imageID = imageID;
    }
    
}
