package dao;

import java.util.List;

import model.ExchangeHistory;

public interface ExchangeHistoryDao extends BaseDao {
    public ExchangeHistory getExchangeHistoryByID(int ID);
    public List<ExchangeHistory> getExchangeHistoryByUserID1(int userID1);
    public List<ExchangeHistory> getExchangeHistoryByUserID2(int userID2);
}