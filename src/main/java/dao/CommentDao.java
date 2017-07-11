package dao;

import java.util.List;

import model.Comment;

public interface CommentDao extends BaseDao {
    public List<Comment> getCommentsByUserID(int userID); 
}