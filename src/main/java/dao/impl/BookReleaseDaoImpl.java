package dao.impl;

import java.util.ArrayList;
import java.util.List;

import common.constants.BookStatus;
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
        String hql = "from BookRelease br where br.userID = :userID";
        Query query = getSession().createQuery(hql).setParameter("userID",userID);
        List<BookRelease> bookReleases = query.list();
        List<Book> bookList = new ArrayList<>();
        if(bookReleases.size() != 0){
            for(int i = 0;i < bookReleases.size();i++){
                Query qry = getSession().createQuery("from Book b where b.bookID = :bookID");
                qry.setParameter("bookID",bookReleases.get(i).getBookID());
                //qry.setParameter("status", BookStatus.IDLE);
                List tmp_book = qry.list();
                System.out.println(tmp_book.size());
                if(tmp_book.size() == 0 ) return null;
                Book book = (Book)tmp_book.get(0);
                book.setBookStatus(book.getStatus().toString());
                if(book.getCanExchange()==1 && book.getStatus()==BookStatus.IDLE){
                    bookList.add(book);
                }
            }
        }

        return bookList;
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