package dao.impl;

import java.util.List;

import org.hibernate.query.Query;

import dao.ExchangeDao;
import model.Exchange;

public class ExchangeDaoImpl extends BaseDaoImpl implements ExchangeDao
{
	@Override
	public Exchange getExchangeByID(int id) {
	    String hql = "from Exchange e where e.exchangeID = :exchangeID order by applyDate desc";
        Query query = getSession().createQuery(hql).setParameter("exchangeID", id);
        List<Exchange> exchanges = query.list();
        Exchange exchange = exchanges.size() == 1 ? exchanges.get(0) : null;
        return exchange;
	}

	@Override
	public List<Exchange> getExchangeByUserID1(int userID1) {  // 申请人
	    String hql = "from Exchange e where e.userID1 = :userID1 order by applyDate desc";
        Query query = getSession().createQuery(hql);
        query.setParameter("userID1", userID1);
        List<Exchange> result = query.list();
        return result;
	}
	
	@Override
    public List<Exchange> getExchangeByUserID2(int userID2) {  // 被申请人
	    String hql = "from Exchange e where e.userID2 = :userID2 order by applyDate desc";
        Query query = getSession().createQuery(hql);
        query.setParameter("userID2", userID2);
        List<Exchange> result = query.list();
        return result;
    }
}
