package dao;

import java.util.List;

import model.Book;
import model.BookRelease;

public interface BookReleaseDao extends BaseDao {
    public BookRelease getBookReleaseByID(int releaseID);
    public List<Book> getReleaseBookByUserID(int userID);
    public BookRelease getReleaseBookByBookID(int bookID);
}