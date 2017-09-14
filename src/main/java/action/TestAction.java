package action;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

import job.OneTimeJobManager;
import job.QuartzManager;
import job.oneTimeJobs.UnactiveUserJob;
import service.BookService;
import service.UserService;

public class TestAction extends ActionSupport {
    private OneTimeJobManager oneTimeJobManager;
    private UserService userService;
    private QuartzManager quartzManager;
    private BookService bookService;
    
    private int bookID;
    
    public OneTimeJobManager getOneTimeJobManager() {
        return oneTimeJobManager;
    }
    public void setOneTimeJobManager(OneTimeJobManager oneTimeJobManager) {
        this.oneTimeJobManager = oneTimeJobManager;
    }
    public UserService getUserService() {
        return userService;
    }
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    public QuartzManager getQuartzManager() {
        return quartzManager;
    }
    public void setQuartzManager(QuartzManager quartzManager) {
        this.quartzManager = quartzManager;
    }    
    public BookService getBookService() {
        return bookService;
    }
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    public int getBookID() {
        return bookID;
    }
    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    /* ============================================================== */

    public String addUnactiveUserJob() {
        oneTimeJobManager.addUnactiveUserJob(123789, 10*1000);
        return SUCCESS;
    }
    public String startJobs() {
        quartzManager.startJobs();
        return SUCCESS;
    }
    public String shutdownJobs() {
        quartzManager.shutdownJobs();
        return SUCCESS;
    }
    public String standbyJobs() {
        quartzManager.standbyJobs();
        return SUCCESS;
    }
    public String getBasePath() {
        System.out.println(userService.getBasePath());
        return SUCCESS;
    }
    public String deleteBook() {
        this.bookService.deleteBook(this.bookID);
        return SUCCESS;
    }
}