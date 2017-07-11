package action;

import com.opensymphony.xwork2.ActionSupport;

/**
 * Created by lvjiawei on 2017/7/11.
 */

public class CommentAction extends ActionSupport {
    private CommentService commentservice;

    private int borrowID;
    private int bookID;
    private String comment;

    /* ============================================================== */

    public CommentService getCommentservice(){return commentservice;}
    public void setCommentservice(CommentService commentservice){this.commentservice = commentservice;}
    public int getBorrowID(){return borrowID;}
    public void setBorrowID(int borrowID){this.borrowID = borrowID;}
    public int getBookID(){return bookID;}
    public void setBookID(int bookID){this.bookID = bookID;}
    public String getComment(){return comment;}
    public void setComment(String comment){this.comment = comment;}

    /* ============================================================== */

    public String commentBook(){
        this.commentservice.commentBook(this.borrowID,this.bookID,this.comment);
        return "commentBook";
    }

}
