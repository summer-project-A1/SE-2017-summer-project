package dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.query.Query;

import dao.BaseDao;
import dao.BookDao;
import model.Book;
import model.User;

public class BookDaoImpl extends BaseDao implements BookDao {

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
        // TODO 自动生成的方法存根
        return null;
    }
    
}