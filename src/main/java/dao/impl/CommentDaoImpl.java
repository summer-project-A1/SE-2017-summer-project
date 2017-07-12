package dao.impl;

import java.util.List;
import java.util.Map;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
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

    @Override
    public List<Comment> getCommentsByBookID(int bookID){
        String hql = "from Comment c where c.bookID = :bookID";
        Query query = getSession().createQuery(hql).setParameter("bookID",bookID);
        List<Comment> comment = query.list();
        return comment;
    }

    @Override
    public Map getBookCommentProfileMap(Comment comment){
        DBCollection collection = getMongoDb().getCollection("book_comment");
        DBObject query=new BasicDBObject("_id", new ObjectId(comment.getProfileID()));
        DBObject obj = collection.findOne(query);
        Map bookCommentProfile = (obj!=null) ? (Map)obj : null;
        return bookCommentProfile;
    }

    @Override
    public Comment getCommentsByCommentID(int commentID){
        String hql = "from Comment c where c.commentID = :commentID";
        Query query = getSession().createQuery(hql).setParameter("commentID", commentID);
        List<Comment> comments = query.list();
        Comment comment = comments.size() == 1 ? comments.get(0) : null;
        return comment;
    }

    @Override
    public boolean deleteCommentProfile(Map bookComment){
        DBCollection collection = getMongoDb().getCollection("book_comment");
        BasicDBObject document = new BasicDBObject(bookComment);
        collection.remove(document);
        return true;
    }
}