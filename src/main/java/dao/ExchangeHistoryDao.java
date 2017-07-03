package dao;

import java.util.List;

import model.ExchangeHistory;

public interface ExchangeHistoryDao extends BaseDao {
    public List<ExchangeHistory> getExchangeHistoryByUserID(int userID);
}