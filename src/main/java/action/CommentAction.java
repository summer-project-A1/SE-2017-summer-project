package action;

import com.opensymphony.xwork2.ActionSupport;

import service.CommentService;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lvjiawei on 2017/7/11.
 */
public class CommentAction extends ActionSupport {
    private CommentService commentService;
    private int borrowID;
    private int bookID;
    private int commentID;
    private String comment;

    private Map params;
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
    public int getCommentID() {
        return commentID;
    }

    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }

    public Map getParams() {
        return params;
    }

    public void setParams(Map params) {
        this.params = params;
    }


    public String commentBook(){
        this.commentService.commentBook(this.borrowID,this.bookID,this.comment);
        return "commentBook";
    }

    public String deleteComment(){
        params = new HashMap();
        if(this.commentService.deleteComment(this.commentID)){
            params.put("success",true);
        }else{
            params.put("success",false);
        }
        return "ajax";
    }



}
