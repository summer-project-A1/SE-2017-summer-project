package dao;

import java.util.List;

import model.Book;

public interface BookReleaseDao extends BaseDao {
    public List<Book> getReleaseBookByUserID(int userID);
}