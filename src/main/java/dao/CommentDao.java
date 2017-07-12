package dao;

import java.util.List;
import java.util.Map;

import model.Comment;

public interface CommentDao extends BaseDao {
    public List<Comment> getCommentsByUserID(int userID);
    public String saveBookComment(Map bookComment);
}
