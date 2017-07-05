package service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import common.constants.BookStatus;
import common.constants.OrderStatus;
import dao.BookDao;
import dao.BookReleaseDao;
import dao.OrderDao;
import dao.UserDao;
import model.Book;
import model.BookRelease;
import model.Order;
import model.OrderItem;
import model.User;
import service.OrderService;

public class OrderServiceImpl extends BaseServiceImpl implements OrderService {
    private UserDao userDao;
    private BookDao bookDao;
    private BookReleaseDao bookReleaseDao; 
    private OrderDao orderDao;
    
    /* ========================================================= */
    
    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public BookDao getBookDao() {
        return bookDao;
    }

    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }
    
    public BookReleaseDao getBookReleaseDao() {
        return bookReleaseDao;
    }

    public void setBookReleaseDao(BookReleaseDao bookReleaseDao) {
        this.bookReleaseDao = bookReleaseDao;
    }

    public OrderDao getOrderDao() {
        return orderDao;
    }

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    /* ========================================================= */
    
    @Override
    public boolean addToCart(int bookID) {
        // 添加到http session中，不验证登录状态
        Book book = this.bookDao.getBookByID(bookID); 
        if(book == null) {
            return false;
        }
        List<Map<String, Object>> cartList;
        if(getHttpSession().containsKey("buyCart")) {
            cartList = (List<Map<String, Object>>)getHttpSession().get("buyCart");
        }
        else {
            cartList = new ArrayList<Map<String, Object>>();
            getHttpSession().put("buyCart", cartList);
        }
        
        // 如果bookID已存在session中，不做任何操作
        for(Map<String, Object> cartListItem : cartList) {
            if((int)cartListItem.get("bookID") == bookID) {
                return false;
            }
        }
        Map<String, Object> newCartListItem = new HashMap();
        newCartListItem.put("bookID", bookID);
        newCartListItem.put("bookName", book.getBookName());
        newCartListItem.put("amount", 1);
        cartList.add(newCartListItem);
        return true;
    }

    @Override
    public List showCart() {
        List<Map<String, Object>> cartList;
        List resultList = new ArrayList();
        if(getHttpSession().containsKey("buyCart")) {
            cartList = (List<Map<String, Object>>)getHttpSession().get("buyCart");
        }
        else {
            cartList = new ArrayList<Map<String, Object>>();
        }
        
        for(Map<String, Object> cartListItem : cartList) {
            int bookID = (int)cartListItem.get("bookID");
            Book book = this.bookDao.getBookByID(bookID);
            if(book != null) {
                resultList.add(book);
            }
        }
        return resultList;
    }

    @Override
    public boolean removeFromCart(int bookID) {
        List<Map<String, Object>> cartList;
        if(getHttpSession().containsKey("buyCart")) {
            cartList = (List<Map<String, Object>>)getHttpSession().get("buyCart");
        }
        else {
            cartList = new ArrayList<Map<String, Object>>();
        }
        
        Iterator iterator = cartList.iterator();
        while(iterator.hasNext()) {
            Map<String, Object> cartListItem = (Map<String, Object>) iterator.next();
            int existedBookID = (int)cartListItem.get("bookID");
            if(existedBookID == bookID) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean emptyCart() {
        if(getHttpSession().containsKey("buyCart")) {
            getHttpSession().remove("buyCart");
        }
        return true;
    }

    @Override
    public Order createOrder() {
        /* 不验证积分是否足够
         * 修改书的状态
         * 跳转到订单页面
         */
        if(!isLogined()) {
            return null;
        }
        User loginedUser = getLoginedUserInfo();
        List<Map<String, Object>> buyCartList;
        if(getHttpSession().containsKey("buyCart")) {
            buyCartList = (List<Map<String, Object>>)getHttpSession().get("buyCart");
        }
        else {
            return null;
        }
        
        if(buyCartList.isEmpty()) {
            return null;
        }
        // now buyCartList must not be empty
        Order newOrder = new Order();
        newOrder.setUserID(loginedUser.getUserID());
        newOrder.setOrderDate(new Date());
        newOrder.setStatus(OrderStatus.UNPAID);

        Set<OrderItem> newOrderItemList = new HashSet<OrderItem>();
        List<Book> allBook = new ArrayList<Book>();
        int totalCredit = 0;
        boolean flag = true;
        Iterator iterator = buyCartList.iterator();
        while(iterator.hasNext()) {
            Map<String, Object> cartListItem = (Map<String, Object>) iterator.next();
            int bookID = (int)cartListItem.get("bookID");
            Book book = this.bookDao.getBookByID(bookID);
            // 检查书的状态，修改并保存
            if(book.getStatus() == BookStatus.IDLE) {
                allBook.add(book);
            }
            else {
                flag = false;
            }
            BookRelease bookRelease = this.bookReleaseDao.getReleaseBookByBookID(bookID);
            OrderItem orderItem = new OrderItem();
            orderItem.setBookID(bookID);
            orderItem.setPrice(bookRelease.getBuyCredit());
            totalCredit += bookRelease.getBuyCredit();
            newOrderItemList.add(orderItem);
            //iterator.remove();
        }
        if(flag) {
            for(Book book : allBook) {
                book.setStatus(BookStatus.BOUGHT);
                this.bookDao.update(book);
            }
        }
        else {
            return null;
        }
        newOrder.setOrderItems(newOrderItemList);
        newOrder.setTotalPrice(totalCredit);
        this.orderDao.save(newOrder);
        getHttpSession().remove("buyCart");
        return newOrder;
    }

    @Override
    public boolean submitOrder(int orderID) {
        // 需要检查用户的积分是否足够支付
        if(!isLogined()) {
            return false;
        }
        User loginedUser = getLoginedUserInfo();
        Order order = this.orderDao.getOrderByID(orderID);
        if(order == null) {
            return false;
        }
        int userCredit = loginedUser.getCredit();
        int orderCredit = order.getTotalPrice();
        if(userCredit < orderCredit) {
            return false;
        }
        loginedUser.setCredit(userCredit-orderCredit);
        order.setStatus(OrderStatus.FINISHED);
        this.userDao.update(loginedUser);
        this.orderDao.update(order);
        return true;
    }

    @Override
    public boolean cancelOrder(int orderID) {
        // TODO 自动生成的方法存根
        return false;
    }
    
}