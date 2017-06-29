package dao;

import java.util.List;

import model.Book;

public interface BookReleaseDao {
    public List<Book> getReleaseBookByUserID(int userID);
}