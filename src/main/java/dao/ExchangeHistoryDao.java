package dao;

import java.util.List;

import model.ExchangeHistory;

public interface ExchangeHistoryDao {
    public List<ExchangeHistory> getExchangeHistoryByUserID(int userID);
}