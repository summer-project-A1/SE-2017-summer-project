package dao;

import java.util.List;

import model.BorrowHistory;

public interface BorrowHistoryDao extends BaseDao {
    public List<BorrowHistory> getBorrowHistoryByUserID(int userID);
}