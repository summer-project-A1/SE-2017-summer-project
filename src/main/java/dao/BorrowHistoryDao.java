package dao;

import java.util.List;

import model.BorrowHistory;

public interface BorrowHistoryDao extends BaseDao {
    public BorrowHistory getBorrowHistoryByBorrowID(int borrowID);
    public List<BorrowHistory> getBorrowHistoryByBorrowUserID(int userID);
    public List<BorrowHistory> getBorrowHistoryByLendUserID(int userID);
}