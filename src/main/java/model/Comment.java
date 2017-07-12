package model;

public class Comment {         // 用户对书评论，只能在还书的时候进行
    private int commentID;
    private int userID;
    private int bookID;
    private String profileID;     // 评论的具体内容存在monggodb中，这里只保留id
    
    /* ============================================= */
    
    public int getCommentID() {
        return commentID;
    }
    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }
    public int getUserID() {
        return userID;
    }
    public void setUserID(int userID) {
        this.userID = userID;
    }
    public int getBookID() {
        return bookID;
    }
    public void setBookID(int bookID) {
        this.bookID = bookID;
    }
    public String getProfileID() {
        return profileID;
    }
    public void setProfileID(String profileID) {
        this.profileID = profileID;
    }
    
}
