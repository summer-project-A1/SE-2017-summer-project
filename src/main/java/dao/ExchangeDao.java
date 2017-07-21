package dao;

import java.util.List;

import model.Exchange;

public interface ExchangeDao extends BaseDao {
	public Exchange getExchangeByID(int id);
    public List<Exchange> getExchangeByUserID1(int userID1);
    public List<Exchange> getExchangeByUserID2(int userID2);
}