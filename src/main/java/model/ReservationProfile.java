package model;

import common.constants.BookStatus;

public class ReservationProfile extends Reserve {
    private String bookName;
    private String isbn;
    private String author;            // 作者
    private String press;             // 出版社
    private String category1;
    private String category2;
    private String imageID;
    private String ownerEmail;
    private String bookStatus;
    private BookStatus Status;
    private int borrowCredit;
    private int reserveAmt;


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

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public int getBorrowCredit() {
        return borrowCredit;
    }

    public void setBorrowCredit(int borrowCredit) {
        this.borrowCredit = borrowCredit;
    }

    public int getReserveAmt() {
        return reserveAmt;
    }

    public void setReserveAmt(int reserveAmt) {
        this.reserveAmt = reserveAmt;
    }

    public String getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(String bookStatus) {
        this.bookStatus = bookStatus;
    }

    public BookStatus getStatus() {
        return Status;
    }

    public void setStatus(BookStatus status) {
        Status = status;
    }
}
