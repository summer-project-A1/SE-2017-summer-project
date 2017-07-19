package dao.impl;

import java.util.List;

import org.hibernate.query.Query;

import dao.BorrowHistoryDao;
import model.BorrowHistory;

public class BorrowHistoryDaoImpl extends BaseDaoImpl implements BorrowHistoryDao {

    @Override
    public BorrowHistory getBorrowHistoryByBorrowID(int borrowID){
        String hql = "from BorrowHistory where bhID=:borrowID order by orderDate desc";
        Query query = getSession().createQuery(hql);
        query.setParameter("borrowID",borrowID);
        List<BorrowHistory> list = query.list();
        if(list != null & list.size()>0){
            return list.get(0);
        }
        else return null;
    }
 
    @Override
    public List<BorrowHistory> getBorrowHistoryByBorrowUserID(int userID) {
        String hql = "from BorrowHistory where userID1=:userID order by orderDate desc";
        Query query = getSession().createQuery(hql);
        query.setParameter("userID", userID);
        List<BorrowHistory> result = query.list();
        if(result != null & result.size()>0) return result;
        else return null;
    }

    @Override
    public List<BorrowHistory> getBorrowHistoryByLendUserID(int userID) {
        String hql = "from BorrowHistory where userID2=:userID order by orderDate desc";
        Query query = getSession().createQuery(hql);
        query.setParameter("userID", userID);
        List<BorrowHistory> result = query.list();
        if(result != null & result.size()>0) return result;
        else return null;
    }
       
}