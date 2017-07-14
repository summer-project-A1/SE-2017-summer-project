package action;

import java.io.File;
import java.util.List;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import model.Book;
import model.BookProfile;
import model.CommentProfile;
import service.BookService;
import service.CommentService;

public class BookAction extends ActionSupport {
    private static final long serialVersionUID = -9028260230073194219L;
    
    private BookService bookService;
	private CommentService commentService;

    /*
        分页使用的两个变量
     */
    private Integer part;
    private Integer firstPage;

    private String category1Name;
    private String category2Name;


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
    /* ============================================================== */

    public String showAllBooks() {
        if(this.part == null) {
            this.part = 1;
        }
        if(this.firstPage == null) {
            this.firstPage = 0;
        }
        List<Book> allBooks = this.bookService.showAllBooksByPage(this.part,9);
        List<Book> nextPage = this.bookService.showAllBooksByPage(this.part+1, 9);
        ActionContext.getContext().put("isLastPart",(nextPage.size()==0));
        ActionContext.getContext().put("allBooks",allBooks);
        ActionContext.getContext().put("totalBookAmount",allBooks.size());//应从数据库获取allBooks的大小
        ActionContext.getContext().put("firstPage", this.firstPage);
        ActionContext.getContext().put("categoryNameOfBooks","");
        return "showBooks";
    }

    public String showBookRelease(){
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


