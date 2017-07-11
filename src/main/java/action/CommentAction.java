package action;

import com.opensymphony.xwork2.ActionSupport;

import service.CommentService;

/**
 * Created by lvjiawei on 2017/7/11.
 */
public class CommentAction extends ActionSupport {
    private CommentService commentService;
    private int bookID;
    
    /* =================================================== */
    
    public CommentService getCommentService() {
        return commentService;
    }
    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }
    public int getBookID() {
        return bookID;
    }
    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

}
