package dao.impl;

import java.util.List;

import org.hibernate.query.Query;

import dao.CommentDao;
import model.Comment;

public class CommentDaoImpl extends BaseDaoImpl implements CommentDao {

    @Override
    public List<Comment> getCommentsByUserID(int userID) {
        String hql = "from Comment c where c.userID = :userID";
        Query query = getSession().createQuery(hql).setParameter("userID", userID);
        List<Comment> comments = query.list();
        return comments;
    }
    
}