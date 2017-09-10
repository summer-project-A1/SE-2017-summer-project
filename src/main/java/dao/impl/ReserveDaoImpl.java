package dao.impl;

import java.util.List;

import org.hibernate.query.Query;

import dao.ReserveDao;
import model.Reserve;

public class ReserveDaoImpl extends BaseDaoImpl implements ReserveDao {

    @Override
    public Boolean isReserved(int userID, int bookID) {   // Reserve表中存在的记录都是现存的预约记录；预约过程处理后从Reserve表中删除
        String hql = "select count(*) from Reserve as r where r.userID = :userID and r.bookID = :bookID";
        Query query = getSession().createQuery(hql);
        query.setParameter("userID", userID);
        query.setParameter("bookID", bookID);
        Long count = (Long)query.uniqueResult();
        return (count > 0);
    }
    
    @Override
    public Reserve getReserveByID(int reserveID) {
        String hql = "from Reserve as r where r.reserveID = :reserveID";
        Query query = getSession().createQuery(hql);
        query.setParameter("reserveID", reserveID);
        List<Reserve> reserveList = query.list();
        if(reserveList != null && reserveList.size() > 0) {
            return reserveList.get(0);
        }
        return null;
    }
    
    @Override
    public List<Reserve> getReservationByUserID(int userID) {
        String hql = "from Reserve as r where r.userID = :userID";
        Query query = getSession().createQuery(hql);
        query.setParameter("userID", userID);
        List<Reserve> reserveList = query.list();
        return reserveList;
    }
    
    @Override
    public List<Reserve> getReservationByBookID(int bookID) {
        String hql = "from Reserve as r where r.bookID = :bookID";
        Query query = getSession().createQuery(hql);
        query.setParameter("bookID", bookID);
        List<Reserve> reserveList = query.list();
        return reserveList;
    }
    
    @Override
    public Reserve getFirstReserveByBookID(int bookID) {
        String hql = "from Reserve as r where r.bookID = :bookID order by r.due asc";
        Query query = getSession().createQuery(hql);
        query.setParameter("bookID", bookID);
        query.setFirstResult(0);
        query.setMaxResults(1);
        List<Reserve> reserveList = query.list();
        if(reserveList != null && reserveList.size() > 0) {
            return reserveList.get(0);
        }
        return null;
    }
}