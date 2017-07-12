package service.impl;

import dao.UserDao;
import model.CommentProfile;
import model.User;
import service.CommentService;
import dao.CommentDao;
import model.Comment;

import java.util.*;

public class CommentServiceImpl extends BaseServiceImpl implements CommentService {
    private CommentDao commentdao;
    private UserDao userDao;

    public CommentDao getCommentdao() {
        return commentdao;
    }

    public void setCommentdao(CommentDao commentdao) {
        this.commentdao = commentdao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }


    @Override
    public boolean commentBook(int borrowID, int bookID, String comment) {
        if (!isLogined()) {
            return false;
        }
        Map commentInMongo = new HashMap();
        commentInMongo.put("bookComment", comment);
        commentInMongo.put("commentTime", new Date());

        User user = getLoginedUserInfo();
        Comment newComment = new Comment();
        newComment.setCommentID(borrowID);
        newComment.setBookID(bookID);
        newComment.setUserID(user.getUserID());
        newComment.setProfileID(this.commentdao.saveBookComment(commentInMongo));
        this.commentdao.save(newComment);
        return true;
    }

    @Override
    public List<CommentProfile> getBookComment(int bookID) {
        List list = this.commentdao.getCommentsByBookID(bookID);
        List<CommentProfile> commentProfileList = new ArrayList<>();

        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Comment comment = (Comment) list.get(i);
                Map commentProfileInMongo = this.commentdao.getBookCommentProfileMap(comment);
                CommentProfile commentProfile = new CommentProfile();
                commentProfile.setCommentID(comment.getCommentID());
                commentProfile.setBookID(bookID);
                commentProfile.setUserID(comment.getUserID());
                commentProfile.setEmail((this.userDao.getUserById(comment.getUserID())).getEmail());
                commentProfile.setCommentContent((String) commentProfileInMongo.get("bookComment"));
                commentProfile.setCommentDate((Date) commentProfileInMongo.get("commentTime"));
                commentProfileList.add(commentProfile);
            }
            return commentProfileList;
        }
        return null;
    }

    @Override
    public boolean deleteComment(int commentID){
        Comment comment = this.commentdao.getCommentsByCommentID(commentID);
        Map commentProfileInMongo = this.commentdao.getBookCommentProfileMap(comment);
        this.commentdao.deleteCommentProfile(commentProfileInMongo);
        this.commentdao.delete(comment);
        return true;
    }

}