package service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.BorrowDao;
import dao.BorrowHistoryDao;
import model.BookRelease;
import model.Borrow;
import model.BorrowHistory;
import model.Reserve;
import service.HistoryService;

public class HistoryServiceImpl extends BaseServiceImpl implements HistoryService {
    private BorrowDao borrowDao;
    private BorrowHistoryDao borrowHistoryDao;
    
    /* ========================================================= */
    
    public BorrowDao getBorrowDao() {
        return borrowDao;
    }

    public void setBorrowDao(BorrowDao borrowDao) {
        this.borrowDao = borrowDao;
    }

    public BorrowHistoryDao getBorrowHistoryDao() {
        return borrowHistoryDao;
    }

    public void setBorrowHistoryDao(BorrowHistoryDao borrowHistoryDao) {
        this.borrowHistoryDao = borrowHistoryDao;
    }

    /* ========================================================= */
    
    @Override
    public Map getUserExchangeHistory(int userID) {
        // TODO 自动生成的方法存根
        return null;
    }

    @Override
    public Map getUserBorrowHistory(int userID) {
        Map result = new HashMap();
        List<Borrow> userBorrow = this.borrowDao.getBorrowByBorrowUserID(userID);  // 用户借来书的记录
        List<BorrowHistory> userBorrowHistory = this.borrowHistoryDao.getBorrowHistoryByBorrowUserID(userID);
        result.put("userBorrow", userBorrow);
        result.put("userBorrowHistory", userBorrowHistory);
        return result;
    }

    @Override
    public Map getUserOrder(int userID) {
        // TODO 自动生成的方法存根
        return null;
    }

    @Override
    public List<Reserve> getUserReservation(int userID) {
        // TODO 自动生成的方法存根
        return null;
    }

    @Override
    public List<BookRelease> getReleaseBook(int userID) {
        // TODO 自动生成的方法存根
        return null;
    }
    
}