package action;

import java.util.List;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import model.Book;
import model.Category1;
import service.BookService;

/**
 * Created by zzy on 2017/7/4.
 */
public class IndexAction extends ActionSupport {
    private BookService bookService;
    public BookService getBookService() {
        return bookService;
    }
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }
    public String index() {
        ActionContext.getContext().put("title","图书分享交流平台");
        //List<Book> recommendBookList = this.bookService.getRecommendBookList();
        //ActionContext.getContext().put("recommendBook1",recommendBookList[0]);
        //ActionContext.getContext().put("recommendBook2",recommendBookList[1]);
        return "showIndex";
    }
    public String header(){
        ActionContext.getContext().put("category1List",bookService.showAllCategory1s());
        return"showHeader";
    }
}
