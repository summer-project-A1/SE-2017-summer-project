package service;

public interface CommentService extends BaseService {
    public boolean commentBook(int borrowID, String comment);
}