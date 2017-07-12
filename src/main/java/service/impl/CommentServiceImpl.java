package service.impl;

import dao.BorrowDao;
import dao.CommentDao;
import model.Borrow;
import model.Comment;
import service.CommentService;

public class CommentServiceImpl extends BaseServiceImpl implements CommentService {
    private CommentDao commentDao;
    private BorrowDao borrowDao;
    
    /* ======================================================== */
    
    public CommentDao getCommentDao() {
        return commentDao;
    }
    public void setCommentDao(CommentDao commentDao) {
        this.commentDao = commentDao;
    }
    public BorrowDao getBorrowDao() {
        return borrowDao;
    }
    public void setBorrowDao(BorrowDao borrowDao) {
        this.borrowDao = borrowDao;
    }

    /* ======================================================== */

    @Override
    public boolean commentBook(int borrowID, String comment) {
        Borrow borrow = this.borrowDao.getBorrowByID(borrowID);
        int userID = borrow.getUserID();
        Comment newComment = new Comment();
        newComment.setBookID(borrowID);   ///////???????
        newComment.setUserID(userID);
        newComment.setProfileID(0);     //////???????
        this.commentDao.save(newComment);
        return true;
    }
    
}