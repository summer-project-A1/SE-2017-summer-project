package action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import model.Book;
import service.BookService;

public class BookAction extends ActionSupport {
    private static final long serialVersionUID = -9028260230073194219L;
    
    private BookService bookService;
    
    private int userID;
    
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
}