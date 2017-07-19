package dao.impl;

import java.util.List;

import org.hibernate.query.Query;

import dao.OrderDao;
import model.Book;
import model.Order;

public class OrderDaoImpl extends BaseDaoImpl implements OrderDao {

    @Override
    public List<Order> getOrdersByBuyerID(int buyerID) {
        String hql = "from Order where buyerID = :buyerID order by orderDate desc";
        Query query = getSession().createQuery(hql).setParameter("buyerID", buyerID);
        List<Order> orders = query.list();
        return orders;
    }
    
    @Override
    public List<Order> getOrdersBySellerID(int sellerID) {
        String hql = "from Order where sellerID = :sellerID order by orderDate desc";
        Query query = getSession().createQuery(hql).setParameter("sellerID", sellerID);
        List<Order> orders = query.list();
        return orders;
    }

    @Override
    public Order getOrderByID(int orderID) {
        String hql = "from Order where orderID = :orderID order by orderDate desc";
        Query query = getSession().createQuery(hql).setParameter("orderID", orderID);
        List<Order> orders = query.list();
        Order order = orders.size() == 1 ? orders.get(0) : null;
        return order;
    }
    
}