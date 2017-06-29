package dao;

import java.util.List;

import model.Exchange;

public interface ExchangeDao {
    public List<Exchange> getExchangeByUserID(int userID);
}