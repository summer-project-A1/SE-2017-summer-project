package action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

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
        ActionContext.getContext().put("title","ͼ�������ƽ̨");
        return "showIndex";
    }    
    public String header() {
        List<Category1> category1List = this.bookService.showAllCategory1s();
        ActionContext.getContext().put("category1List", category1List);
        return "showHeader";
    }

}
