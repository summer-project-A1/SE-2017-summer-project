package dao.impl;

import java.util.List;
import java.util.Map;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import org.bson.types.ObjectId;
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

    @Override
    public String saveBookComment(Map bookComment){
        DBCollection collection = getMongoDb().getCollection("book_comment");
        BasicDBObject document = new BasicDBObject(bookComment);
        collection.insert(document);
        return ((ObjectId)document.get("_id")).toString();
    }
}
