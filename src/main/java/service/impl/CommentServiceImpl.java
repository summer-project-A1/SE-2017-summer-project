package service.impl;

import model.User;
import service.CommentService;
import dao.CommentDao;
import model.Comment;

import java.util.HashMap;
import java.util.Map;

public class CommentServiceImpl extends BaseServiceImpl implements CommentService {
    private CommentDao commentdao;

    public CommentDao getCommentdao(){return commentdao;}
    public void setCommentdao(CommentDao commentdao){this.commentdao = commentdao;}

    @Override
    public boolean commentBook(int borrowID,int bookID,String comment){
        if(!isLogined()) {
            return false;
        }
        Map commentInMongo = new HashMap();
        commentInMongo.put("bookComment",comment);

        User user = getLoginedUserInfo();
        Comment newComment = new Comment();
        newComment.setCommentID(borrowID);
        newComment.setBookID(bookID);
        newComment.setUserID(user.getUserID());
        newComment.setProfileID(this.commentdao.saveBookComment(commentInMongo));
        this.commentdao.save(newComment);
        return true;
    }
}
