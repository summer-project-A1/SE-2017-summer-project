package action;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import model.Book;
import model.BookInfo;
import model.BookProfile;
import service.BookService;

public class BookAction extends ActionSupport {
    private static final long serialVersionUID = -9028260230073194219L;
    
    private BookService bookService;
    
    private int userID;
    
    private Integer part;
    private Integer pageSize;
    
    private int bookID;
    private String bookName;
    private String isbn;
    private String author;         // 作者
    private String press;          // 出版社
    private String category1;      // 大分类
    private String category2;      // 小分类
    private int publishYear;    // 出版
    private int publishMonth;
    private int editionYear;     // 版次
    private int editionMonth;
    private int editionVersion;
    private int page;              // 页数
    private String bookBinding;    // 装帧
    private String bookFormat;     // 开本
    private String bookQuality;    // 成色
    private String bookDamage;     // 损毁情况
    private String intro;          // 简介
    private Integer canBorrow;        // 是否可借阅
    private Integer canExchange;      // 是否可交换
    private int borrowCredit;      // 借阅所需积分
    private int buyCredit;    // 购买所需积分
    private File coverPicture;     // 封面
    private String coverPictureFileName;
    private String coverPictureContentType;
    private File[] otherPicture;   // 其他图片
    private String[] otherPictureFileName;
    private String[] otherPictureContentType;
    
    private BookProfile bookProfile;
    
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

    public Integer getPart() {
        return part;
    }
    public void setPart(Integer part) {
        this.part = part;
    }
    public Integer getPageSize() {
        return pageSize;
    }
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
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
    public int getPublishYear() {
        return publishYear;
    }
    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }
    public int getPublishMonth() {
        return publishMonth;
    }
    public void setPublishMonth(int publishMonth) {
        this.publishMonth = publishMonth;
    }
    public int getEditionYear() {
        return editionYear;
    }
    public void setEditionYear(int editionYear) {
        this.editionYear = editionYear;
    }
    public int getEditionMonth() {
        return editionMonth;
    }
    public void setEditionMonth(int editionMonth) {
        this.editionMonth = editionMonth;
    }
    public int getEditionVersion() {
        return editionVersion;
    }
    public void setEditionVersion(int editionVersion) {
        this.editionVersion = editionVersion;
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
    public Integer getCanBorrow() {
        return canBorrow;
    }
    public void setCanBorrow(Integer canBorrow) {
        this.canBorrow = canBorrow;
    }
    public Integer getCanExchange() {
        return canExchange;
    }
    public void setCanExchange(Integer canExchange) {
        this.canExchange = canExchange;
    }
    public int getBorrowCredit() {
        return borrowCredit;
    }
    public void setBorrowCredit(int borrowCredit) {
        this.borrowCredit = borrowCredit;
    }
    public int getBuyCredit() {
        return buyCredit;
    }
    public void setBuyCredit(int buyCredit) {
        this.buyCredit = buyCredit;
    }
    public File getCoverPicture() {
        return coverPicture;
    }
    public void setCoverPicture(File coverPicture) {
        this.coverPicture = coverPicture;
    }
    public String getCoverPictureFileName() {
        return coverPictureFileName;
    }
    public void setCoverPictureFileName(String coverPictureFileName) {
        this.coverPictureFileName = coverPictureFileName;
    }
    public String getCoverPictureContentType() {
        return coverPictureContentType;
    }
    public void setCoverPictureContentType(String coverPictureContentType) {
        this.coverPictureContentType = coverPictureContentType;
    }
    public File[] getOtherPicture() {
        return otherPicture;
    }
    public void setOtherPicture(File[] otherPicture) {
        this.otherPicture = otherPicture;
    }
    public BookProfile getBookProfile() {
        return bookProfile;
    }
    public void setBookProfile(BookProfile bookProfile) {
        this.bookProfile = bookProfile;
    }
    public String[] getOtherPictureFileName() {
        return otherPictureFileName;
    }
    public void setOtherPictureFileName(String[] otherPictureFileName) {
        this.otherPictureFileName = otherPictureFileName;
    }
    public String[] getOtherPictureContentType() {
        return otherPictureContentType;
    }
    public void setOtherPictureContentType(String[] otherPictureContentType) {
        this.otherPictureContentType = otherPictureContentType;
    }    
    
    /* ============================================================== */
    
    public String showAllBooks() {
        if(this.part == null) {
            this.part = 1;
        }
        this.pageSize = 100;
        List<BookInfo> allBooks = this.bookService.showAllBookInfoByPage(this.part,this.pageSize);
        ActionContext.getContext().put("allBooks",allBooks);
        ActionContext.getContext().put("totalBookAmount",allBooks.size());
        return "showBooks";
    }

    public String showBookRelease(){
        return "showBookRelease";
    }

    public String showUserReleasedBooks() {
        List<Book> userReleasedBooks = this.bookService.showUserBooks(this.userID);
        for(Book tmp:userReleasedBooks) {
            System.out.println(tmp.getBookID());
        }
        ActionContext.getContext().put("allBooks",userReleasedBooks);
        return "showBooks";
    }
    public String uploadBook() {
        BookProfile newBookProfile = new BookProfile();
        newBookProfile.setBookName(this.bookName);
        newBookProfile.setIsbn(this.isbn);
        newBookProfile.setAuthor(this.author);         // 作者
        newBookProfile.setPress(this.press);          // 出版社
        newBookProfile.setCategory1(this.category1);       // 大分类
        newBookProfile.setCategory2(this.category2);       // 小分类
        newBookProfile.setPublishYear(this.publishYear);    // 出版
        newBookProfile.setPublishMonth(this.publishMonth);
        newBookProfile.setEditionYear(this.editionYear);     // 版次
        newBookProfile.setEditionMonth(this.editionMonth);
        newBookProfile.setEditionVersion(this.editionVersion);
        newBookProfile.setPage(this.page);              // 页数
        newBookProfile.setBookBinding(this.bookBinding);    // 装帧
        newBookProfile.setBookFormat(this.bookFormat);     // 开本
        newBookProfile.setBookQuality(this.bookQuality);    // 成色
        newBookProfile.setBookDamage(this.bookDamage);     // 损毁情况
        newBookProfile.setIntro(this.intro);          // 简介
        newBookProfile.setCanBorrow((this.canBorrow==null)? 0:this.canBorrow);        // 是否可借阅
        newBookProfile.setCanExchange((this.canExchange==null)? 0:this.canExchange);      // 是否可交换
        newBookProfile.setBorrowCredit(this.borrowCredit);      // 借阅所需积分
        newBookProfile.setBuyCredit(this.buyCredit);    // 购买所需积分
        newBookProfile.setCoverPicture(this.coverPicture);     // 封面
        newBookProfile.setOtherPicture(this.otherPicture);   // 其他图片
        this.bookService.uploadBook(newBookProfile);
        return "showBooks";
    }
    public String showBookProfile() {
        this.bookProfile = this.bookService.showBookProfile(this.bookID);
        ActionContext.getContext().put("bookProfile",bookProfile);
        return "showBookProfile";
    }
}