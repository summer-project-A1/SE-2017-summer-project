package dao.impl;

import java.util.List;

import org.hibernate.query.Query;

import dao.ReserveDao;
import model.Reserve;

public class ReserveDaoImpl extends BaseDaoImpl implements ReserveDao {

    @Override
    public List<Reserve> getReservationByUserID(int userID) {
        String hql = "from Reserve as r where r.userID = :userID";
        Query query = getSession().createQuery(hql);
        List<Reserve> reserveList = query.list();
        return reserveList;
    }
    
    @Override
    public Reserve getFirstReserveByBookID(int bookID) {
        String hql = "from Reserve as r where r.bookID = :bookID order by r.due asc";
        Query query = getSession().createQuery(hql);
        query.setFirstResult(0);
        query.setMaxResults(1);
        List<Reserve> reserveList = query.list();
        if(reserveList != null && reserveList.size() > 0) {
            return reserveList.get(0);
        }
        return null;
    }
}