package action;

import com.opensymphony.xwork2.ActionSupport;

import service.CommentService;

/**
 * Created by lvjiawei on 2017/7/11.
 */
public class CommentAction extends ActionSupport {
    private CommentService commentService;
    private int borrowID;
    private int bookID;
    private String comment;
    
    /* =================================================== */
    
    public CommentService getCommentService() {
        return commentService;
    }
    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }
    public int getBorrowID() {
        return borrowID;
    }
    public void setBorrowID(int borrowID) {
        this.borrowID = borrowID;
    }
    public int getBookID() {
        return bookID;
    }
    public void setBookID(int bookID) {
        this.bookID = bookID;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }

}
