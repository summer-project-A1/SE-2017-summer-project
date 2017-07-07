package dao;

import java.util.List;

import model.Borrow;

public interface BorrowDao extends BaseDao {
    public Borrow getBorrowByID(int borrowID);
    public List<Borrow> getBorrowByUserID(int userID);
}