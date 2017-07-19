package action;

import java.io.File;
import java.util.HashMap;
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

    // 图书查找的条件
    private String category1Name;
    private String category2Name;
    private Integer year;
    private String status;  // "canBorrow"：用户设定能借但不能交换的书 ；"canExchange"：用户设定能换/买但不能借的书； null：不考虑用户如何设定

    private int userID;
    private int bookID;
    private String bookName;
    private BookProfile bookProfile;
	private List<CommentProfile> commentProfileList;

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
    
    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    /* ============================================================== */

    public String showAllBooks() {    // 查找满足筛选条件的图书，分页展示
        this.bookNumPerPage = 9;
        if(this.part == null) {
            this.part = 1;
        }
        if(this.firstPage == null) {
            this.firstPage = 0;
        }
        
        Map conditions = new HashMap();
        conditions.put("part", this.part);
        conditions.put("pageSize", this.bookNumPerPage);
        if(category1Name != null) {
            conditions.put("category1", this.category1Name);
        }
        if(category2Name != null) {
            conditions.put("category2", this.category2Name);
        }
        if(year != null) {
            conditions.put("publishYear", this.year);
        }
        if(status != null) {
            if(status.equals("canBorrow")) {
                conditions.put("canBorrow", 1);
                conditions.put("canExchange", 0);
            }
            else if(status.equals("canExchange")) {
                conditions.put("canBorrow", 0);
                conditions.put("canExchange", 1);
            }
        }
        List<Book> allBooks = this.bookService.searchBook(conditions);
        conditions.put("part", (Integer)conditions.get("part")+1);
        List<Book> nextPage = this.bookService.searchBook(conditions);
        /*
        //List<Book> allBooks = this.bookService.showAllBooksByPage(this.part, this.bookNumPerPage);
        List<Book> allBooks = this.bookService.showAllBooksByPage(this.part, this.bookNumPerPage);
        List<Book> nextPage = this.bookService.showAllBooksByPage(this.part+1, this.bookNumPerPage);
        */
        ActionContext.getContext().put("isLastPart",(nextPage.size()==0));
        ActionContext.getContext().put("allBooks",allBooks);
        ActionContext.getContext().put("totalBookAmount",allBooks.size());//应从数据库获取allBooks的大小
        ActionContext.getContext().put("firstPage", this.firstPage);
        ActionContext.getContext().put("category1List",this.bookService.showAllCategory1s());
        return "showBooks";
    }

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
}


