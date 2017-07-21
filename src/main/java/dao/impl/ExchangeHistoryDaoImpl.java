package dao.impl;

import dao.ExchangeHistoryDao;
import model.ExchangeHistory;

import java.util.List;

import org.hibernate.query.Query;

public class ExchangeHistoryDaoImpl extends BaseDaoImpl implements ExchangeHistoryDao{

    @Override
    public ExchangeHistory getExchangeHistoryByID(int ehID) {
        String hql = "from ExchangeHistory eh where eh.ehID = :ehID";
        Query query = getSession().createQuery(hql).setParameter("ehID", ehID);
        List<ExchangeHistory> exchangeHistorys = query.list();
        ExchangeHistory exchangeHistory = exchangeHistorys.size() == 1 ? exchangeHistorys.get(0) : null;
        return exchangeHistory;
    }

    @Override
    public List<ExchangeHistory> getExchangeHistoryByUserID1(int userID1) {
        String hql = "from ExchangeHistory eh where eh.userID1 = :userID1";
        Query query = getSession().createQuery(hql);
        query.setParameter("userID1", userID1);
        List<ExchangeHistory> result = query.list();
        return result;
    }

    @Override
    public List<ExchangeHistory> getExchangeHistoryByUserID2(int userID2) {
        String hql = "from ExchangeHistory eh where eh.userID2 = :userID2";
        Query query = getSession().createQuery(hql);
        query.setParameter("userID2", userID2);
        List<ExchangeHistory> result = query.list();
        return result;
    }
    
}
