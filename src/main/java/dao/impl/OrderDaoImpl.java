package dao.impl;

import java.util.List;

import org.hibernate.query.Query;

import dao.OrderDao;
import model.Book;
import model.Order;

public class OrderDaoImpl extends BaseDaoImpl implements OrderDao {

    @Override
    public List<Order> getOrdersByUserID(int userID) {
        // TODO 自动生成的方法存根
        return null;
    }

    @Override
    public Order getOrderByID(int orderID) {
        String hql = "from Order where orderID = :orderID";
        Query query = getSession().createQuery(hql).setParameter("orderID", orderID);
        List<Order> orders = query.list();
        Order order = orders.size() == 1 ? orders.get(0) : null;
        return order;
    }
    
}