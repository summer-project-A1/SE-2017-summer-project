package service.impl;

import java.util.*;

import common.constants.BookStatus;
import common.constants.BorrowStatus;
import common.constants.OrderStatus;
import dao.BookDao;
import dao.BookReleaseDao;
import dao.OrderDao;
import dao.UserDao;
import model.Book;
import model.BookRelease;
import model.Borrow;
import model.BorrowProfile;
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
    public Map showMyOrder() {
        User buyer = getLoginedUserInfo();
        int buyerID = buyer.getUserID();
        List<Order>orders = this.orderDao.getOrdersByBuyerID(buyerID);
        List<OrderProfile>orderProfileList = new ArrayList<>();
        if(orders != null){
            for(Order order : orders){
                OrderProfile orderProfile = new OrderProfile();
                int bookID = order.getBookID();
                Book book = this.bookDao.getBookByID(bookID);
                BookRelease bookRelease = this.bookReleaseDao.getReleaseBookByBookID(bookID);
                User seller = this.userDao.getUserById(order.getSellerID());

                orderProfile.setOrderID(order.getOrderID());
                orderProfile.setBuyerID(buyerID);
                orderProfile.setSellerID(order.getSellerID());
                orderProfile.setBookID(bookID);
                orderProfile.setOrderDate(order.getOrderDate());
                orderProfile.setPayDate(order.getPayDate());
                orderProfile.setBuyCredit(order.getBuyCredit());
                orderProfile.setAddress(order.getAddress());
                orderProfile.setFhDate(order.getFhDate());
                orderProfile.setShDate(order.getShDate());
                orderProfile.setTrackingNo(order.getTrackingNo());
                orderProfile.setBuyerComment(order.getBuyerComment());
                orderProfile.setSellerComment(order.getSellerComment());
                orderProfile.setStatus(order.getStatus());
                orderProfile.setOrderStatus(order.getStatus().toString());

                orderProfile.setBookName(book.getBookName());
                orderProfile.setIsbn((book.getIsbn()));
                orderProfile.setAuthor(book.getAuthor());
                orderProfile.setPress(book.getPress());
                orderProfile.setCategory1(book.getCategory1());
                orderProfile.setCategory2(book.getCategory2());
                orderProfile.setImageID(book.getImageID());
                orderProfile.setEmail(seller.getEmail());
                orderProfileList.add(orderProfile);
            }
            Map result = new HashMap();
            Collections.sort(orderProfileList, new Comparator<OrderProfile>() {
                @Override
                public int compare(OrderProfile o1, OrderProfile o2) {
                    if(o1.getOrderID() > o2.getOrderID()){
                        return -1;
                    }
                    if(o1.getOrderID() < o2.getOrderID()){
                        return 1;
                    }
                    return 0;
                }
            });
            result.put("orderProfileList", orderProfileList);
            return result;
        }
        return null;
    }
    
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
    
    
    public Map createBuyOrder(String fullAddress) {
        /* 不验证积分是否足够
         * 不修改书的状态
         * 跳转到订单页面
         */
        Map returnMap = new HashMap();    // 返回值
        User user = this.getLoginedUserInfo();
        List<Map<String, Object>> cartList;
        List<OrderProfile> orderProfileList = new ArrayList();
        int totalNeededCredit = 0;  // 所需总积分
        if(getHttpSession().containsKey("buyCart")) {
            cartList = (List<Map<String, Object>>)getHttpSession().get("buyCart");
        }
        else {
            cartList = new ArrayList<Map<String, Object>>();
        }
        
        for(Map<String, Object> cartListItem : cartList) {
            int bookID = (int)cartListItem.get("bookID");
            Book book = this.bookDao.getBookByID(bookID);
            BookRelease bookRelease = this.bookReleaseDao.getReleaseBookByBookID(bookID);
            totalNeededCredit += book.getBorrowCredit();     
            
            Order newOrder = new Order();
            newOrder.setBookID(book.getBookID());
            newOrder.setBuyerID(user.getUserID());  // 买家
            newOrder.setSellerID(bookRelease.getUserID());  // 卖家
            newOrder.setOrderDate(new Date());
            newOrder.setBuyCredit(book.getBuyCredit());
            newOrder.setAddress(fullAddress);  // 买家收货地址
            newOrder.setStatus(OrderStatus.NOTPAID);
            this.orderDao.save(newOrder);
            
            OrderProfile newOrderProfile = new OrderProfile();
            newOrderProfile.setOrderID(newOrder.getOrderID());
            newOrderProfile.setBookID(newOrder.getBookID());
            newOrderProfile.setImageID(book.getImageID());
            newOrderProfile.setIsbn(book.getIsbn());
            newOrderProfile.setOrderStatus(newOrder.getStatus().toString());
            newOrderProfile.setAuthor(book.getAuthor());
            newOrderProfile.setCategory1(book.getCategory1());
            newOrderProfile.setCategory2(book.getCategory2());
            newOrderProfile.setBuyCredit(book.getBuyCredit());
            orderProfileList.add(newOrderProfile);
        }
        getHttpSession().remove("borrowCart");
        
        returnMap.put("orderProfileList", orderProfileList);
        returnMap.put("totalCredit", totalNeededCredit);
        return returnMap;
    }

    
    @Override
    public Map confirmBuyOrder(List<Integer> orderIDList) {
        // 确认付款
        // 需要检查用户的积分是否足够支付
        // 需要检查书的状态
        Map returnMap = new HashMap();
        User user = this.getLoginedUserInfo();
        int totalCredit = 0;        // 所有书总计需要的积分
        List<Book> allIdleBook = new ArrayList();    // 临时保存空闲的书
        List<BookRelease> allIdleBookRelease = new ArrayList();    // 临时保存空闲的书
        List<Order> allSuccessOrder = new ArrayList();   // 保存所有能够成功的order
        List<Book> allNotIdleBook = new ArrayList();    // 临时保存非空闲的书
        List<BookRelease> allNotIdleBookRelease = new ArrayList();    // 临时保存非空闲的书
        List<Order> allFailOrder = new ArrayList();   // 保存所有能够成功的order
        
        for(Integer orderID : orderIDList) {
            Order order = this.orderDao.getOrderByID(orderID);
            Integer bookID = order.getBookID();
            Book book = this.bookDao.getBookByID(bookID);
            BookRelease bookRelease = this.bookReleaseDao.getReleaseBookByBookID(bookID);
            totalCredit += order.getBuyCredit();    // 使用下单时的所需积分
            if(book.getStatus().equals(BookStatus.IDLE) && book.getReserved()==0) {  // 书是空闲的并且没有被预约
                allIdleBook.add(book);
                allIdleBookRelease.add(bookRelease);
                allSuccessOrder.add(order);
            }
            else {
                allNotIdleBook.add(book);
                allNotIdleBookRelease.add(bookRelease);
                allFailOrder.add(order);
            }
        }
        
        boolean result = true;
        boolean creditNotEnough = false;
        boolean bookNotIdle = false;
        
        // 只有用户积分能够完成本批订单的整体支付才能继续
        if(user.getCredit() < totalCredit) {
            result = false;
            creditNotEnough = true;
        }
        
        // 如果某本书已被买走/借走/交换等，则失败
        if(!allNotIdleBook.isEmpty()) {
            result = false;
            bookNotIdle = true;
        }
        
        Date payDate = new Date();
        if(result) {
            user.setCredit(user.getCredit() - totalCredit);
            this.userDao.update(user);
            for(int i=0; i<allSuccessOrder.size(); i++) {
                Book book = allIdleBook.get(i);
                BookRelease bookRelease = allIdleBookRelease.get(i);
                Order order = allSuccessOrder.get(i);
                User seller = this.userDao.getUserById(order.getSellerID());
                seller.setCredit(seller.getCredit() + order.getBuyCredit());
                this.userDao.update(seller);
                order.setStatus(OrderStatus.NOTSHIPPED);
                order.setPayDate(payDate);
                this.orderDao.update(order);
                book.setStatus(BookStatus.EXCHANGED);
                this.bookDao.update(book);
            }
        }
        returnMap.put("success", result);
        returnMap.put("payDate", payDate);
        return returnMap;
    }
    

    
    @Override
    public boolean cancelBuyOrder(int orderID) {
        if(!isLogined()) {
            return false;
        }
        Order order = orderDao.getOrderByID(orderID);
        if(order == null) {
            return false;
        }
        if(order.getBuyerID()!=getLoginedUserInfo().getUserID())
        	return false;
        if(order.getStatus() != OrderStatus.NOTPAID) {
            return false;
        }
        order.setStatus(OrderStatus.CANCELED);
        orderDao.update(order);
        return true;
    }

    @Override
    public Map confirmBuyReceipt(int orderID){
        Map returnMap = new HashMap();
        if(!isLogined()) {
            returnMap.put("success", false);
            return returnMap;
        }
        User user = getLoginedUserInfo();
        Order order = this.orderDao.getOrderByID(orderID);
        if(order.getBuyerID() != user.getUserID()){
            returnMap.put("success",false);
            return returnMap;
        }

        Date shDate = new Date();
        order.setShDate(shDate);
        order.setStatus(OrderStatus.COMPLETED);
        this.orderDao.update(order);
        returnMap.put("success",true);
        returnMap.put("shDate",shDate);
        return returnMap;
    }

    @Override
    public List<OrderProfile> getSellBookList() {
        List<OrderProfile> orderProfileList = new ArrayList<OrderProfile>();
        User user = this.getLoginedUserInfo();
        int userID = user.getUserID();
        List<Order> orderList = this.orderDao.getOrdersBySellerID(userID);
        if(orderList != null) {
            for (Order order : orderList) {
                Book book = this.bookDao.getBookByID(order.getBookID());
                OrderProfile orderProfile = new OrderProfile();
                orderProfile.setOrderID(order.getOrderID());
                orderProfile.setBuyerID(order.getBuyerID());
                orderProfile.setSellerID(order.getSellerID());
                orderProfile.setBookID(book.getBookID());
                orderProfile.setOrderDate(order.getOrderDate());
                orderProfile.setPayDate(order.getPayDate());
                orderProfile.setBuyCredit(order.getBuyCredit());
                orderProfile.setAddress(order.getAddress());
                orderProfile.setFhDate(order.getFhDate());
                orderProfile.setShDate(order.getShDate());
                orderProfile.setTrackingNo(order.getTrackingNo());
                orderProfile.setBuyerComment(order.getBuyerComment());
                orderProfile.setSellerComment(order.getSellerComment());
                orderProfile.setStatus(order.getStatus());
                orderProfile.setOrderStatus(order.getStatus().toString());

                orderProfile.setBookName(book.getBookName());
                orderProfile.setIsbn((book.getIsbn()));
                orderProfile.setAuthor(book.getAuthor());
                orderProfile.setPress(book.getPress());
                orderProfile.setCategory1(book.getCategory1());
                orderProfile.setCategory2(book.getCategory2());
                orderProfile.setImageID(book.getImageID());
                orderProfile.setEmail(user.getEmail());   // user is seller
                orderProfileList.add(orderProfile);
            }
        }
        return orderProfileList;
    }

    @Override
    public Map deliverBuyOrder(int orderID,String trackingNo){
        Map returnMap = new HashMap();
        if(!isLogined()) {
            returnMap.put("success", false);
            return returnMap;
        }
        User user = getLoginedUserInfo();
        Order order = this.orderDao.getOrderByID(orderID);
        if(order.getSellerID() != user.getUserID()){
            returnMap.put("success",false);
            return returnMap;
        }
        Date fhDate = new Date();
        order.setFhDate(fhDate);
        order.setTrackingNo(trackingNo);
        order.setStatus(OrderStatus.SHIPPED);
        this.orderDao.update(order);
        returnMap.put("success",true);
        returnMap.put("fhDate",fhDate);
        return returnMap;
    }

}