package model;


import java.util.Date;

public class CommentProfile {
    private int commentID;
    private int bookID; //书的id
    private int userID; //用户id
    private String email;
    private Date commentDate; //评论时间
    private String commentContent; //评论内容

    public int getBookID(){return bookID;}
    public void setBookID(int bookID){this.bookID = bookID;}
    public int getUserID(){return userID;}
    public void setUserID(int userID){this.userID = userID;}
    public Date getCommentDate(){return commentDate;}
    public void setCommentDate(Date commentDate){this.commentDate = commentDate;}
    public String getCommentContent(){return commentContent;}
    public void setCommentContent(String commentContent){this.commentContent = commentContent;}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCommentID() {
        return commentID;
    }

    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }
}
