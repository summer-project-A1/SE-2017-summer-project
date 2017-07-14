package action;

import java.util.List;
import java.util.Set;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import model.Category1;
import model.Category2;
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
    public String header(){
        List<Category1> c1 = this.bookService.showAllCategory1s();
        System.out.println(c1.size());
        for(Category1 t1 : c1) {
            System.out.println(t1.getCategory1Name());
            List<Category2> c2 = t1.getCategory2List();
            System.out.println(c2.size());
            for(Category2 t2 : c2) {
                System.out.println(t2.getCategory2Name());
            }
        }
        ActionContext.getContext().put("category1List",bookService.showAllCategory1s());
        return"showHeader";
    }
}
