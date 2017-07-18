package dao.impl;

import java.util.List;

import org.hibernate.query.Query;

import dao.BorrowDao;
import model.Book;
import model.Borrow;

public class BorrowDaoImpl extends BaseDaoImpl implements BorrowDao {

    @Override
    public Borrow getBorrowByID(int borrowID) {
        String hql = "from Borrow b where b.borrowID = :borrowID";
        Query query = getSession().createQuery(hql).setParameter("borrowID", borrowID);
        List<Borrow> borrows = query.list();
        Borrow borrow = borrows.size() == 1 ? borrows.get(0) : null;
        return borrow;
    }

    @Override
    public List<Borrow> getBorrowByBorrowUserID(int userID) {
        String hql = "from Borrow where userID1=:userID";   // 查找用户借来书的记录
        Query query = getSession().createQuery(hql);
        query.setParameter("userID", userID);
        List<Borrow> result = query.list();
        return result;
    }
    
    @Override
    public List<Borrow> getBorrowByLendUserID(int userID) {
        String hql = "from Borrow where userID2=:userID";   // 查找用户借出书的记录
        Query query = getSession().createQuery(hql);
        query.setParameter("userID", userID);
        List<Borrow> result = query.list();
        return result;
    }
}