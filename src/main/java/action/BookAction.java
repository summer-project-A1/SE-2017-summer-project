package action;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;

import model.Book;
import service.BookService;

public class BookAction extends ActionSupport {
    private static final long serialVersionUID = -9028260230073194219L;
    
    private BookService bookService;
    
    private int userID;
    
    private int bookID;
    private String bookName;
    private String isbn;
    private String author;         // 作者
    private String press;          // 出版社
    private String category1;      // 大分类
    private String category2;      // 小分类
    private String publishYear;    // 出版
    private String publishMonth;
    private String edtionYear;     // 版次
    private String edtionMonth;
    private String edtionVersion;
    private int page;              // 页数
    private String bookBinding;    // 装帧
    private String bookFormat;     // 开本
    private String bookQuality;    // 成色
    private String bookDamage;     // 损毁情况
    private String intro;          // 简介
    private Integer bookBorrow;        // 是否可借阅
    private Integer bookExchange;      // 是否可交换
    private int borrowCredit;      // 借阅所需积分
    private int exchangeCredit;    // 交换所需积分
    private File coverPicture;     // 封面
    private File[] otherPicture;   // 其他图片
    
    private Map bookProfile;
    
    /* ============================================================== */
    
    public BookService getBookService() {
        return bookService;
    }
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    public int getUserID() {
        return userID;
    }
    public void setUserID(int userID) {
        this.userID = userID;
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
    public String getPublishYear() {
        return publishYear;
    }
    public void setPublishYear(String publishYear) {
        this.publishYear = publishYear;
    }
    public String getPublishMonth() {
        return publishMonth;
    }
    public void setPublishMonth(String publishMonth) {
        this.publishMonth = publishMonth;
    }
    public String getEdtionYear() {
        return edtionYear;
    }
    public void setEdtionYear(String edtionYear) {
        this.edtionYear = edtionYear;
    }
    public String getEdtionMonth() {
        return edtionMonth;
    }
    public void setEdtionMonth(String edtionMonth) {
        this.edtionMonth = edtionMonth;
    }
    public String getEdtionVersion() {
        return edtionVersion;
    }
    public void setEdtionVersion(String edtionVersion) {
        this.edtionVersion = edtionVersion;
    }
    public int getPage() {
        return page;
    }
    public void setPage(int page) {
        this.page = page;
    }
    public String getBookBinding() {
        return bookBinding;
    }
    public void setBookBinding(String bookBinding) {
        this.bookBinding = bookBinding;
    }
    public String getBookFormat() {
        return bookFormat;
    }
    public void setBookFormat(String bookFormat) {
        this.bookFormat = bookFormat;
    }
    public String getBookQuality() {
        return bookQuality;
    }
    public void setBookQuality(String bookQuality) {
        this.bookQuality = bookQuality;
    }
    public String getBookDamage() {
        return bookDamage;
    }
    public void setBookDamage(String bookDamage) {
        this.bookDamage = bookDamage;
    }
    public String getIntro() {
        return intro;
    }
    public void setIntro(String intro) {
        this.intro = intro;
    }
    public Integer getBookBorrow() {
        return bookBorrow;
    }
    public void setBookBorrow(Integer bookBorrow) {
        this.bookBorrow = bookBorrow;
    }
    public Integer getBookExchange() {
        return bookExchange;
    }
    public void setBookExchange(Integer bookExchange) {
        this.bookExchange = bookExchange;
    }
    public int getBorrowCredit() {
        return borrowCredit;
    }
    public void setBorrowCredit(int borrowCredit) {
        this.borrowCredit = borrowCredit;
    }
    public int getExchangeCredit() {
        return exchangeCredit;
    }
    public void setExchangeCredit(int exchangeCredit) {
        this.exchangeCredit = exchangeCredit;
    }
    public File getCoverPicture() {
        return coverPicture;
    }
    public void setCoverPicture(File coverPicture) {
        this.coverPicture = coverPicture;
    }
    public File[] getOtherPicture() {
        return otherPicture;
    }
    public void setOtherPicture(File[] otherPicture) {
        this.otherPicture = otherPicture;
    }
    public Map getBookProfile() {
        return bookProfile;
    }
    public void setBookProfile(Map bookProfile) {
        this.bookProfile = bookProfile;
    }    
    
    /* ============================================================== */
    
    public String showAllBooks() {
        List<Book> allBooks = this.bookService.showAllBooks();
        for(Book tmp:allBooks) {
            System.out.println(tmp.getBookID());
        }
        return SUCCESS;
    }
    public String showUserBooks() {
        List<Book> userBooks = this.bookService.showUserBooks(this.userID);
        for(Book tmp:userBooks) {
            System.out.println(tmp.getBookID());
        }
        return SUCCESS;
    }
    public String uploadBook() {
        Map bookInfo = new HashMap();
        bookInfo.put("bookName", this.bookName);
        bookInfo.put("isbn", this.isbn);
        bookInfo.put("author", this.author);         // 作者
        bookInfo.put("press", this.press);          // 出版社
        bookInfo.put("category1", this.category1);       // 大分类
        bookInfo.put("category2", this.category2);       // 小分类
        bookInfo.put("publishYear", this.publishYear);    // 出版
        bookInfo.put("publishMonth", this.publishMonth);
        bookInfo.put("edtionYear", this.edtionYear);     // 版次
        bookInfo.put("edtionMonth", this.edtionMonth);
        bookInfo.put("edtionVersion", this.edtionVersion);
        bookInfo.put("page", this.page);              // 页数
        bookInfo.put("bookBinding", this.bookBinding);    // 装帧
        bookInfo.put("bookFormat", this.bookFormat);     // 开本
        bookInfo.put("bookQuality", this.bookQuality);    // 成色
        bookInfo.put("bookDamage", this.bookDamage);     // 损毁情况
        bookInfo.put("intro", this.intro);          // 简介
        bookInfo.put("bookBorrow", (this.bookBorrow==null)? 0:this.bookBorrow);        // 是否可借阅
        bookInfo.put("bookExchange", (this.bookExchange==null)? 0:this.bookExchange);      // 是否可交换
        bookInfo.put("borrowCredit", this.borrowCredit);      // 借阅所需积分
        bookInfo.put("exchangeCredit", this.exchangeCredit);    // 交换所需积分
        bookInfo.put("coverPicture", this.coverPicture);     // 封面
        bookInfo.put("otherPicture", this.otherPicture);   // 其他图片
        this.bookService.uploadBook(bookInfo);
        return SUCCESS;
    }
    public String showBookProfile() {
        this.bookProfile = this.bookService.showBookProfile(this.bookID);
        return SUCCESS;
    }
}