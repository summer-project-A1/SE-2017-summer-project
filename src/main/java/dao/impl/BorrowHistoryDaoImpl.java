package dao.impl;

import java.util.List;

import org.hibernate.query.Query;

import dao.BorrowHistoryDao;
import model.BorrowHistory;

public class BorrowHistoryDaoImpl extends BaseDaoImpl implements BorrowHistoryDao {

    @Override
    public List<BorrowHistory> getBorrowHistoryByUserID(int userID) {
        String hql = "from BorrowHistory where userID=:userID";
        Query query = getSession().createQuery(hql);
        query.setParameter("userID", userID);
        List<BorrowHistory> result = query.list();
        return result;
    }
    
}