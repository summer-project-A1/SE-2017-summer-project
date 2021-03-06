package dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.hibernate.Hibernate;
import org.hibernate.query.Query;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.hibernate.type.Type;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import common.constants.BookStatus;
import dao.BookDao;
import model.Book;
import model.BookRelease;

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
        String hql = "from Book order by status";
        Query query = getSession().createQuery(hql);
        List<Book> result = query.list();
        return result;
    }
    
    @Override
    public int getAllBooksCount() {
        String hql = "select count(*) from Book";
        Query query = getSession().createQuery(hql);
        Long result = (Long)query.uniqueResult();
        return (int)(long)result;
    }
    
    @Override
    public List<Book> getAllBooksByPage(int part,int pageSize) {
        String hql = "from Book order by status";
        Query query = getSession().createQuery(hql);
        query.setFirstResult((part-1)*pageSize); 
        query.setMaxResults(pageSize); 
        List<Book> result = query.list();
        return result;
    }

    @Override
    public List<Book> getBooksByUserID(int userID) {
        String hql = "select b from Book as b,BookRelease as br where b.bookID=br.bookID and br.userID=:userID order by b.bookID desc";
        Query query = getSession().createQuery(hql);
        query.setParameter("userID", userID);
        List<Book> result = query.list();
        return result;
    }

    @Override
    public List<Book> getBooksByCategory1NameByPage(String category1Name, int part, int pageSize) {
        String hql = "from Book where category1=:category1Name order by status";
        Query query = getSession().createQuery(hql);
        query.setParameter("category1Name", category1Name);
        query.setFirstResult((part-1)*pageSize); 
        query.setMaxResults(pageSize); 
        List<Book> result = query.list();
        return result;
    }
    
    @Override
    public List<Book> getBooksByCategory2NameByPage(String category2Name, int part, int pageSize) {
        String hql = "from Book where category2=:category2Name order by status";
        Query query = getSession().createQuery(hql);
        query.setParameter("category2Name", category2Name);
        query.setFirstResult((part-1)*pageSize); 
        query.setMaxResults(pageSize); 
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
        hql += " order by status ";
        Query query = getSession().createQuery(hql);
        query.setParameter("bookName", "%"+searchText+"%");
        query.setParameter("author", "%"+searchText+"%");
        query.setParameter("press", "%"+searchText+"%");
        query.setFirstResult((part-1)*pageSize); 
        query.setMaxResults(pageSize); 
        List<Book> result = query.list();
        return result;
    }
    
    @Override
    public List<Book> searchByCondition(Map condition, Integer part, Integer pageSize) {
        String hqlTables = " from Book as b";
        String hqlConditions = " where 1=1 ";
        String hql = " select b ";
        String countHql = " select count(*) ";
        List<Object> args = new ArrayList<Object>();
        List<Type> types = new ArrayList<Type>();
        if(condition.containsKey("category1")) {
            hqlConditions += " and b.category1=? ";
            args.add(condition.get("category1"));
            types.add(new StringType());
        }
        if(condition.containsKey("category2")) {
            hqlConditions += " and b.category2=? ";
            args.add(condition.get("category2"));
            types.add(new StringType());
        }
        if(condition.containsKey("canBorrow")) {
            hqlConditions += " and b.canBorrow=? ";
            args.add(condition.get("canBorrow"));
            types.add(new IntegerType());
        }
        if(condition.containsKey("canExchange")) {
            hqlConditions += " and b.canExchange=? ";
            args.add(condition.get("canExchange"));
            types.add(new IntegerType());
        }
        if(condition.containsKey("publishYear")) {
            hqlConditions += " and b.publishYear=? ";
            args.add(condition.get("publishYear"));
            types.add(new IntegerType());
        }
        if(condition.containsKey("searchName")) {
            hqlConditions += " and (b.bookName like ? or b.author like ?) ";
            args.add("%"+condition.get("searchName")+"%");
            types.add(new StringType());
            
            args.add("%"+condition.get("searchName")+"%");
            types.add(new StringType());
        }
        hqlConditions += " order by status ";
        hql = hql + hqlTables + hqlConditions;
        System.out.println(hql);
        Query query = getSession().createQuery(hql);
        query.setParameters(args.toArray(), types.toArray(new Type[0]));
        if(part != null && pageSize != null) {
            query.setFirstResult((part-1)*pageSize); 
            query.setMaxResults(pageSize); 
        }
        List<Book> books = query.list();
        return books;
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
        /*
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
        */
    }

    @Override
    public List<Book> getRecommendBook() {
        // 查找推荐书籍：被借阅次数最多的totalCount本书
        int totalCount = 8;
        String hql1 = "select b.bookID from Book as b, BorrowHistory as bh where b.bookID=bh.bookID group by b.bookID order by count(*) desc";
        Query query1 = getSession().createQuery(hql1);
        query1.setFirstResult(0);
        query1.setMaxResults(totalCount);
        List<Integer> bookIDList1 = query1.list();
        System.out.println("bookIDList1");
        System.out.println(bookIDList1.size());
        if(bookIDList1.size() < totalCount) { ///////// 
            String hql2 = "select b.bookID from Book as b, BookRelease as br where b.bookID=br.bookID and not exists (from BorrowHistory as bh where bh.bookID=b.bookID) order by br.releaseTime desc";
            Query query2 = getSession().createQuery(hql2);
            query2.setFirstResult(0);
            query2.setMaxResults(totalCount-bookIDList1.size());
            List<Integer> bookIDList2 = query2.list();
            System.out.println("bookIDList2");
            System.out.println(bookIDList2.size());
            bookIDList1.addAll(bookIDList2);
        }
        List<Book> bookList = new ArrayList<Book>();
        for(Integer bookID : bookIDList1) {
            bookList.add(this.getBookByID(bookID));
        }
        return bookList;
    }
    
    @Override
    public Map getBookProfileMapInMongo(String profileID) {
        DBCollection collection = getMongoDb().getCollection("book_profile");
        DBObject query=new BasicDBObject("_id", new ObjectId(profileID));
        DBObject obj = collection.findOne(query);
        Map bookProfileInMongo = (obj!=null) ? (Map)obj : null;
        return bookProfileInMongo;
    }

    @Override
    public String saveBookProfileInMongo(Map bookProfileInMongo) {
        DBCollection collection = getMongoDb().getCollection("book_profile");
        BasicDBObject document = new BasicDBObject(bookProfileInMongo);
        collection.insert(document);
        return ((ObjectId)document.get("_id")).toString();
    }
    
    @Override
    public boolean deleteBookProfileInMongo(String profileID) {
        DBCollection collection = getMongoDb().getCollection("book_profile");
        DBObject query=new BasicDBObject("_id", new ObjectId(profileID));
        collection.remove(query);
        return true;
    }
    
    @Override
    public boolean updateBookProfileInMongo(Book book, Map bookProfileInMongo) {
        DBCollection collection = getMongoDb().getCollection("book_profile");
        DBObject query=new BasicDBObject("_id", new ObjectId(book.getProfileID()));
        DBObject old = collection.findOne(query);

        BasicDBObject document = new BasicDBObject(bookProfileInMongo);
        if(old!=null) {
            collection.update(query, document);
            return true;
        }
        else {
            return false;
        }
    }
}