package dao.impl;

import java.util.List;

import org.hibernate.query.Query;

import dao.BorrowDao;
import model.Borrow;

public class BorrowDaoImpl extends BaseDaoImpl implements BorrowDao {

    @Override
    public List<Borrow> getBorrowByUserID(int userID) {
        String hql = "from Borrow where userID=:userID";
        Query query = getSession().createQuery(hql);
        query.setParameter("userID", userID);
        List<Borrow> result = query.list();
        return result;
    }
    
}