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
import model.User;
import model.OrderProfile;
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
    public Map getOrderDetailByID(int orderID) {
    	/*
        // 返回值Map，其中包含key为order的Order类，key为booksInOrder的List<Book>
        Map result = new HashMap();
        List<Book> booksInOrder = new ArrayList<Book>();
        Order order = this.orderDao.getOrderByID(orderID);
        if(order == null) {
            return null;
        }
        for(OrderItem orderItem : order.getOrderItems()) {
            int bookID = orderItem.getBookID();
            Book book = this.bookDao.getBookByID(bookID);
            book.setBuyCredit(orderItem.getPrice());
            booksInOrder.add(book);
        }
        result.put("order", order);
        result.put("booksInOrder", booksInOrder);
        return result;
        */
    	return null;
    }
    
    
    public List<OrderProfile> createBuyOrder(String address) {
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
        //Order newOrder = new Order();
        //newOrder.setBuyerID(loginedUser.getUserID());
        //newOrder.setOrderDate(new Date());
        //newOrder.setStatus(OrderStatus.UNPAID);

        List<Book> allBook = new ArrayList<Book>();
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
        }
        if(flag) {
        	List<OrderProfile> result=new ArrayList<>();
            for(Book book : allBook) {
                book.setStatus(BookStatus.BOUGHT);
                bookDao.update(book);
                BookRelease bookRelease = this.bookReleaseDao.getReleaseBookByBookID(book.getBookID());
                User user = userDao.getUserById(bookRelease.getUserID());
                OrderProfile orderProfile = new OrderProfile(loginedUser.getUserID(),bookRelease.getUserID(),book.getBookID(),new Date(),book.getBuyCredit(),OrderStatus.UNPAID,address);
                orderDao.save((Order)orderProfile);
                orderProfile.setBookName(book.getBookName());
                orderProfile.setIsbn(book.getIsbn());
                orderProfile.setAuthor(book.getAuthor());
                orderProfile.setPress(book.getPress());
                orderProfile.setCategory1(book.getCategory1());
                orderProfile.setCategory2(book.getCategory2());
                orderProfile.setImageID(book.getImageID());
                orderProfile.setEmail(user.getEmail());
                result.add(orderProfile);
            }
            return result;
        }
        else {
            return null;
        }
    }

    
    @Override
    public boolean submitBuyOrder(List<Integer> orderIDs) {
        // 确认付款
        // 需要检查用户的积分是否足够支付
        User loginedUser = getLoginedUserInfo();
        List<Order> orders=new ArrayList<>();
        int totalPrice = 0;
        for(Integer orderID : orderIDs)
        {
        	Order order = orderDao.getOrderByID(orderID);
        	totalPrice += order.getPrice();
        	orders.add(order);
        }
        int userCredit = loginedUser.getCredit();
        if(userCredit < totalPrice) {
            return false;
        }
        loginedUser.setCredit(userCredit-totalPrice);
        userDao.update(loginedUser);
        for(Order order : orders)
        {
        	Date date = new Date();
        	order.setPayDate(date);
        	order.setStatus(OrderStatus.NOTSHIPPED);
        	orderDao.update(order);
        }
        return true;
    }
    

    
    @Override
    public boolean cancelOrder(int orderID) {
        if(!isLogined()) {
            return false;
        }
        Order order = orderDao.getOrderByID(orderID);
        if(order == null) {
            return false;
        }
        if(order.getBuyerID()!=getLoginedUserInfo().getUserID())
        	return false;
        if(order.getStatus() != OrderStatus.UNPAID) {
            return false;
        }
        order.setStatus(OrderStatus.CANCELLED);
        orderDao.update(order);
        return true;
    }
    
}