package dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.hibernate.query.Query;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import dao.BookDao;
import model.Book;

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
    public int getAllBooksCount() {
        String hql = "select count(*) rom Book";
        Query query = getSession().createQuery(hql);
        int result = (int)query.uniqueResult();
        return result;
    }
    
    @Override
    public List<Book> getAllBooksByPage(int part,int pageSize) {
        String hql = "from Book";
        Query query = getSession().createQuery(hql);
        query.setFirstResult((part-1)*pageSize); 
        query.setMaxResults(pageSize); 
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
    public List<Book> searchByTextByPage(String searchText,int part,int pageSize) {
        // 全局搜索，搜索字符串在书名、作者、出版社等多个字段同时尝试匹配
        String hql = " select b from Book as b where 1=1 ";
        hql += " or b.bookName like :bookName ";
        hql += " or b.author like :author ";
        hql += " or b.press like :press ";
        Query query = getSession().createQuery(hql);
        query.setParameter("bookName", searchText);
        query.setParameter("author", searchText);
        query.setParameter("press", searchText);
        query.setFirstResult((part-1)*pageSize); 
        query.setMaxResults(pageSize); 
        List<Book> result = query.list();
        return result;
    }
    
    @Override
    public List<Book> searchByCondition(Map condition) {
        // TODO 自动生成的方法存根
        /*
        a图书所有者
        a地区
        bookName 书名
        isbn
        author 作者
        press 出版社
        category1 类别
        category2 类别
        canExchange 是否可交换
        canBorrow 是否可借阅
        reserved
        status
        editionVersion
        editionYear
        editionMonth
        bookQuality
        bookDamage
        publishYear 出版时间
        publishMonth 出版时间
        page
        bookFormat
        bookBinding
        */
        String bookName = (String)condition.get("bookName");
        String author = (String)condition.get("author");
        String press = (String)condition.get("press");
        String category1 = (String)condition.get("category1");
        Integer canExchange = (Integer)condition.get("canExchange");
        Integer canBorrow = (Integer)condition.get("canBorrow");
        
        String category2 = (String)condition.get("category2");
        Integer publishYear = (Integer)condition.get("publishYear");
        Integer publishMonth = (Integer)condition.get("publishMonth");
        
        DBObject mongoConditions = new BasicDBObject();
        DBObject fieldsObject = new BasicDBObject();
        fieldsObject.put("_id", true);
        DBCollection collection = getMongoDb().getCollection("book_profile");
        DBCursor cursor = collection.find(mongoConditions, fieldsObject);
        List<String> mongoIDs = new ArrayList<String>();
        while(cursor.hasNext()){
            DBObject dbObj= cursor.next();
            mongoIDs.add(dbObj.get("_id").toString());
            System.out.println(dbObj.get("_id").toString());
        }
        
        String hqlTables = " from Book as b, BookRelease as br ";
        String hqlConditions = " where b.bookID=br.bookID ";
        String hql = " select b ";
        String countHql = " select count(*) ";
        hqlConditions += " and b.profileID in (:mongoIDs) ";
        if(bookName != null) {
            hqlConditions += " and b.bookName like :bookName ";
        }
        hql = hql + hqlTables + hqlConditions;
        System.out.println(hql);
        Query query = getSession().createQuery(hql);
        query.setParameterList("mongoIDs", mongoIDs);
        if(bookName != null) {
            query.setParameter("bookName", "%"+bookName+"%");
        }
        query.setFirstResult(0); 
        query.setMaxResults(10); 
        List<Book> result = query.list();
        return result;
    }

    @Override
    public Map getBookProfileMap(Book book) {
        DBCollection collection = getMongoDb().getCollection("book_profile");
        DBObject query=new BasicDBObject("_id", new ObjectId(book.getProfileID()));
        DBObject obj = collection.findOne(query);
        Map bookProfile = (obj!=null) ? (Map)obj : null;
        return bookProfile;
    }

    @Override
    public String saveBookProfile(Map bookProfile) {
        DBCollection collection = getMongoDb().getCollection("book_profile");
        BasicDBObject document = new BasicDBObject(bookProfile);
        collection.insert(document);
        return ((ObjectId)document.get("_id")).toString();
    }
    
    @Override
    public boolean updateBookProfile(Book book, Map bookProfile) {
        DBCollection collection = getMongoDb().getCollection("book_profile");
        DBObject query=new BasicDBObject("_id", new ObjectId(book.getProfileID()));
        DBObject old = collection.findOne(query);

        BasicDBObject document = new BasicDBObject(bookProfile);
        if(old!=null) {
            collection.update(query, document);
            return true;
        }
        else {
            return false;
        }
    }
}