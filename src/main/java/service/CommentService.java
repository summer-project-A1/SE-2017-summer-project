package service;

import model.CommentProfile;

import java.util.List;
import java.util.Map;

public interface CommentService extends BaseService {
    public boolean commentBook(int borrowID,int bookID,String comment);
    public List<CommentProfile> getBookComment(int bookID);
    public boolean deleteComment(int commentID);
}