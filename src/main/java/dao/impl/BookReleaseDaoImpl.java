package dao.impl;

import java.util.List;

import org.hibernate.query.Query;

import dao.BookReleaseDao;
import model.Book;
import model.BookRelease;
import model.User;

public class BookReleaseDaoImpl extends BaseDaoImpl implements BookReleaseDao {

    @Override
    public BookRelease getBookReleaseByID(int releaseID) {
        String hql = "from BookRelease br where br.releaseID = :releaseID";
        Query query = getSession().createQuery(hql).setParameter("releaseID", releaseID);
        List<BookRelease> bookReleases = query.list();
        BookRelease bookRelease = bookReleases.size() == 0 ? bookReleases.get(0) : null;
        return bookRelease;
    }
    
    @Override
    public List<Book> getReleaseBookByUserID(int userID) {
        // TODO 自动生成的方法存根
        return null;
    }

    @Override
    public BookRelease getReleaseBookByBookID(int bookID) {
        String hql = "from BookRelease br where br.bookID = :bookID";
        Query query = getSession().createQuery(hql).setParameter("bookID", bookID);
        List<BookRelease> bookReleases = query.list();
        BookRelease bookRelease = bookReleases.size() == 1 ? bookReleases.get(0) : null;
        return bookRelease;
    }
    
}