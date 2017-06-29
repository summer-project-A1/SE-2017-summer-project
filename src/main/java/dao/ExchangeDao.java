package dao;

import java.util.List;

import model.Exchange;

public interface ExchangeDao extends BaseDao {
    public List<Exchange> getExchangeByUserID(int userID);
}