package action;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import model.Book;
import service.BookService;

public class BookAction extends ActionSupport {
    private static final long serialVersionUID = -9028260230073194219L;
    
    private BookService bookService;

    /*
        分页使用的两个变量
     */
    private Integer part;
    private int firstPage;

    private int userID;
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
    
    private Map bookProfile;

    public BookAction() {
    }
    
    /* ============================================================== */
    
    public BookService getBookService() {
        return bookService;
    }
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    public int getPart(){
        return this.part;
    }

    public void setPart(Integer part){
        this.part=part;
    }

    public int getFirstPage(){
        return this.part;
    }

    public void setFirstPage(int firstPage){
        this.firstPage=firstPage;
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
    public Map getBookProfile() {
        return bookProfile;
    }
    public void setBookProfile(Map bookProfile) {
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
        List<Book> allBooks = this.bookService.showAllBooksByPage(this.part,100);
        ActionContext.getContext().put("allBooks",allBooks);
        ActionContext.getContext().put("totalBookAmount",allBooks.size());//应从数据库获取allBooks的大小
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
        Map bookInfo = new HashMap();
        bookInfo.put("bookName", this.bookName);
        bookInfo.put("isbn", this.isbn);
        bookInfo.put("author", this.author);         // 作者
        bookInfo.put("press", this.press);          // 出版社
        bookInfo.put("category1", this.category1);       // 大分类
        bookInfo.put("category2", this.category2);       // 小分类
        bookInfo.put("publishYear", this.publishYear);    // 出版
        bookInfo.put("publishMonth", this.publishMonth);
        bookInfo.put("editionYear", this.editionYear);     // 版次
        bookInfo.put("editionMonth", this.editionMonth);
        bookInfo.put("editionVersion", this.editionVersion);
        bookInfo.put("page", this.page);              // 页数
        bookInfo.put("bookBinding", this.bookBinding);    // 装帧
        bookInfo.put("bookFormat", this.bookFormat);     // 开本
        bookInfo.put("bookQuality", this.bookQuality);    // 成色
        bookInfo.put("bookDamage", this.bookDamage);     // 损毁情况
        bookInfo.put("intro", this.intro);          // 简介
        bookInfo.put("canBorrow", (this.canBorrow==null)? 0:this.canBorrow);        // 是否可借阅
        bookInfo.put("canExchange", (this.canExchange==null)? 0:this.canExchange);      // 是否可交换
        bookInfo.put("borrowCredit", this.borrowCredit);      // 借阅所需积分
        bookInfo.put("buyCredit", this.buyCredit);    // 购买所需积分
        bookInfo.put("coverPicture", this.coverPicture);     // 封面
        bookInfo.put("otherPicture", this.otherPicture);   // 其他图片
        this.bookService.uploadBook(bookInfo);
        return "showBooks";
    }
    public String showBookProfile() {
        this.bookProfile = this.bookService.showBookProfile(this.bookID);
        ActionContext.getContext().put("bookProfile",bookProfile);
        return "showBookProfile";
    }
}