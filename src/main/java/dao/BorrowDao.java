package dao;

import java.util.List;

import model.Borrow;

public interface BorrowDao {
    public List<Borrow> getBorrowByUserID(int userID);
}