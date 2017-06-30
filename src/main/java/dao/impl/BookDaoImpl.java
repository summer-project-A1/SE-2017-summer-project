package dao.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.hibernate.query.Query;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

import dao.BaseDao;
import dao.BookDao;
import model.Book;
import model.User;

public class BookDaoImpl extends BaseDaoImpl implements BookDao {

    @Override
    public Book getBookByID(int bookID) {
        String hql = "from Book b where b.bookID = :bookID";
        Query query = getSession().createQuery(hql).setParameter("bookID", bookID);
        List<Book> books = query.list();
        Book book = books.size() == 1 ? books.get(0) : null;
        return book;
    }
    
    @Override
    public List<Book> getAllBooks() {
        String hql = "from Book";
        Query query = getSession().createQuery(hql);
        List<Book> result = query.list();
        return result;
    }

    @Override
    public List<Book> getBooksByUserID(int userID) {
        String hql = "select b from Book as b,BookRelease as br where b.bookID=br.bookID and br.userID=:userID";
        Query query = getSession().createQuery(hql);
        query.setParameter("userID", userID);
        List<Book> result = query.list();
        return result;
    }

    @Override
    public List<Book> searchByCondition(Map condition) {
        // TODO 自动生成的方法存根
        return null;
    }

    @Override
    public Map getBookProfileMap(int bookID) {
        DBCollection collection = getMongoDb().getCollection("book_profile");
        DBObject query=new BasicDBObject("book_id", bookID);
        DBObject obj = collection.findOne(query);
        Map bookProfile = (obj!=null) ? (Map)obj : null;
        return bookProfile;
    }

    @Override
    public boolean addOrUpdateBookProfile(Map bookProfile) {
        DBCollection collection = getMongoDb().getCollection("book_profile");
        DBObject query=new BasicDBObject("book_id", (int)bookProfile.get("bookID"));
        DBObject old = collection.findOne(query);

        BasicDBObject document = new BasicDBObject(bookProfile);
        if(old!=null) {
            collection.update(query, document);
        }
        else {
            collection.insert(document);
        }
        return true;
    }
    
}