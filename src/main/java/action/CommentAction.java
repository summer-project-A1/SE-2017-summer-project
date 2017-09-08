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
    private int exchangeID;
    private int ehID;
    private int orderID;
    private int bookID;
    private int commentID;
    private String comment;
    private int creditRating;

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
    public int getExchangeID() {
        return exchangeID;
    }
    public void setExchangeID(int exchangeID) {
        this.exchangeID = exchangeID;
    }
    public int getEhID() {
        return ehID;
    }
    public void setEhID(int ehID) {
        this.ehID = ehID;
    }
    public int getOrderID(){return orderID;}
    public void setOrderID(int orderID){this.orderID = orderID;}
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
    public int getCreditRating() {
        return creditRating;
    }

    public void setCreditRating(int creditRating) {
        this.creditRating = creditRating;
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

    public String honestyRatingWhenBorrow(){
        params = new HashMap();
        System.out.println(this.creditRating);
        System.out.println(getCreditRating());
        this.commentService.honestyRatingInBorrow(this.borrowID,this.creditRating);
        params.put("success",true);
        return "ajax";
        //return "honestyRatingWhenBorrow";
    }

    public String honestyRatingWhenBuy(){
        params = new HashMap();
        this.commentService.honestyRatingInBuy(this.orderID,this.creditRating);
        params.put("success",true);
        return "ajax";
        //return "honestyRatingWhenBuy";
    }

    public String honestyRatingWhenExchange(){
        params = new HashMap();
        this.commentService.honestyRatingInExchange(this.exchangeID,this.creditRating);
        params.put("success",true);
        return "ajax";
        //return "honestyRatingWhenExchange";
    }
    public String honestyRatingWhenExchangeHistory(){
        params = new HashMap();
        this.commentService.honestyRatingInExchangeHistory(this.ehID,this.creditRating);
        params.put("success",true);
        return "ajax";
        //return "honestyRatingWhenExchangeHistory";
    }
}
