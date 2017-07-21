package action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import model.Book;
import model.BookProfile;
import model.Category1;
import model.CommentProfile;
import service.BookService;
import service.CommentService;

public class BookAction extends ActionSupport {
    private static final long serialVersionUID = -9028260230073194219L;
    
    private BookService bookService;
	private CommentService commentService;

	private Integer bookNumPerPage;
    /*
        分页使用的两个变量
     */
    private Integer part;
    private Integer firstPage;
    /*
        筛选使用的变量
     */

    private String year;
    private String status;
    private String category1Name;
    private String category2Name;


    private int userID;
    private int bookID;
    private String bookName;
    private BookProfile bookProfile;
	private List<CommentProfile> commentProfileList;

	private Map params;
	private String isbn;

    public BookAction() {
    }
    
    /* ============================================================== */
    
    public BookService getBookService() {
        return bookService;
    }
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    public CommentService getCommentService() {
        return commentService;
    }

    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }
    public int getPart(){
        return this.part;
    }

    public Integer getBookNumPerPage() {
        return bookNumPerPage;
    }

    public void setBookNumPerPage(Integer bookNumPerPage) {
        this.bookNumPerPage = bookNumPerPage;
    }

    public List<CommentProfile> getCommentProfileList() {
        return commentProfileList;
    }

    public void setCommentProfileList(List<CommentProfile> commentProfileList) {
        this.commentProfileList = commentProfileList;
    }

    public void setPart(Integer part){
        this.part=part;
    }

    public Integer getFirstPage() {
        return this.part;
    }

    public void setFirstPage(Integer firstPage){
        this.firstPage = firstPage;
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
    public BookProfile getBookProfile(){
        return this.bookProfile;
    }
    public void setBookProfile(BookProfile bookProfile) {
        this.bookProfile = bookProfile;
    }
    public String getCategory1Name() {
        return category1Name;
    }

    public void setCategory1Name(String category1Name) {
        this.category1Name = category1Name;
    }

    public String getCategory2Name() {
        return category2Name;
    }

    public void setCategory2Name(String category2Name) {
        this.category2Name = category2Name;
    }
    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Map getParams() {
        return params;
    }

    public void setParams(Map params) {
        this.params = params;
    }

    public String getIsbn(){return isbn;}
    public void setIsbn(String isbn){this.isbn = isbn;}
    /* ============================================================== */
    /*
    public String showAllBooks() {    // 查找满足筛选条件的图书，分页展示
        this.bookNumPerPage = 9;
        if(this.part == null) {
            this.part = 1;
        }
        if(this.firstPage == null) {
            this.firstPage = 0;
        }
        if(this.year==null){
            this.year="";
        }
        if(this.status==null){
            this.status="";
        }
        if(this.category1Name==null){
            this.category1Name="";
        }
        if(this.category2Name==null){
            this.category2Name="";
        }


        List<Book> allBooks = this.bookService.searchBook(part,bookNumPerPage,category1Name,category2Name,year,status);
        List<Book> nextPage = this.bookService.searchBook(part+1,bookNumPerPage,category1Name,category2Name,year,status);
        ActionContext.getContext().put("isLastPart",(nextPage.size()==0));
        ActionContext.getContext().put("part", this.part);
        ActionContext.getContext().put("category1Name", this.category1Name);
        ActionContext.getContext().put("category2Name", this.category2Name);
        ActionContext.getContext().put("status", this.status);
        ActionContext.getContext().put("year", this.year);
        ActionContext.getContext().put("allBooks",allBooks);
        System.out.println("all books Num:"+allBooks.size());
        System.out.println("cate1: "+category1Name );
        System.out.println("cate2: "+category2Name );
        ActionContext.getContext().put("totalBookAmount",allBooks.size());//应从数据库获取allBooks的大小
        ActionContext.getContext().put("firstPage", this.firstPage);
        ActionContext.getContext().put("category1List",this.bookService.showAllCategory1s());
        return "showBooks";
    }*/

    public String showBookRelease(){
        ActionContext.getContext().put("category1List",this.bookService.showAllCategory1s());
        return "showBookRelease";
    }

    public String showUserReleasedBooks() {
        List<Book> userReleasedBooks = this.bookService.showUserBooks(this.userID);
        ActionContext.getContext().put("allBooks",userReleasedBooks);
        return "showBooks";
    }
    public String uploadBook() {
        this.bookService.uploadBook(bookProfile);
        return "showBooks";
    }
    public String showBookProfile() {
        this.bookProfile = this.bookService.showBookProfile(this.bookID);
        this.commentProfileList = this.commentService.getBookComment(this.bookID);
        ActionContext.getContext().put("bookProfile",bookProfile);
        ActionContext.getContext().put("commentProfileList",commentProfileList);
        return "showBookProfile";
    }

    public String showBooksByCategory1Name(){
        if(this.part == null) {
            this.part = 1;
        }
        if(this.firstPage == null) {
            this.firstPage = 0;
        }
        List<Book> allBooks = this.bookService.showBooksByCategory1NameByPage(category1Name,this.part,9);
        List<Book> nextPage = this.bookService.showBooksByCategory1NameByPage(category1Name,this.part+1, 9);
        ActionContext.getContext().put("isLastPart",(nextPage.size()==0));
        ActionContext.getContext().put("allBooks",allBooks);
        ActionContext.getContext().put("totalBookAmount",allBooks.size());//应从数据库获取allBooks的大小
        ActionContext.getContext().put("firstPage", this.firstPage);
        ActionContext.getContext().put("categoryNameOfBooks",category1Name);
        return "showBooks";
    }
    public String showBooksByCategory2Name(){
        if(this.part == null) {
            this.part = 1;
        }
        if(this.firstPage == null) {
            this.firstPage = 0;
        }
        List<Book> allBooks = this.bookService.showBooksByCategory2NameByPage(category2Name,this.part,9);
        List<Book> nextPage = this.bookService.showBooksByCategory2NameByPage(category2Name,this.part+1, 9);
        ActionContext.getContext().put("isLastPart",(nextPage.size()==0));
        ActionContext.getContext().put("allBooks",allBooks);
        ActionContext.getContext().put("totalBookAmount",allBooks.size());//应从数据库获取allBooks的大小
        ActionContext.getContext().put("firstPage", this.firstPage);
        ActionContext.getContext().put("categoryNameOfBooks",category2Name);
        return "showBooks";
    }
    public String showRecommendBooks() {
        List<Book> bookList = this.bookService.getRecommendBookList();
        List<Book> recommendBookList = new ArrayList<Book>();
        if(bookList.size() >= 1) {
            ActionContext.getContext().put("recommendBook1", bookList.get(0));
        }
        else {
            ActionContext.getContext().put("recommendBook1", "");
        }
        if(bookList.size() >= 2) {
            ActionContext.getContext().put("recommendBook2", bookList.get(1));
        }
        else {
            ActionContext.getContext().put("recommendBook2", "");
        }
        for(int i=2; i<bookList.size() && i<10; i++) {
            recommendBookList.add(bookList.get(i));
        }
        ActionContext.getContext().put("recommendBookList", recommendBookList);
        return "showRecommendBooks";
    }

    public String getInfoByIsbn(){
        this.params = this.bookService.getInfoByIsbn(this.isbn);
        return "ajax";
    }


}


