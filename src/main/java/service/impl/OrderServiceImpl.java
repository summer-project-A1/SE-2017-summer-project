package service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

import dao.BookDao;
import model.Book;
import service.OrderService;

public class OrderServiceImpl extends BaseServiceImpl implements OrderService {
    private BookDao bookDao;
    
    /* ========================================================= */
    
    public BookDao getBookDao() {
        return bookDao;
    }

    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
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
        if(getHttpSession().containsKey("cart")) {
            cartList = (List<Map<String, Object>>)getHttpSession().get("cart");
        }
        else {
            cartList = new ArrayList<Map<String, Object>>();
            getHttpSession().put("cart", cartList);
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
        if(getHttpSession().containsKey("cart")) {
            cartList = (List<Map<String, Object>>)getHttpSession().get("cart");
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
        if(getHttpSession().containsKey("cart")) {
            cartList = (List<Map<String, Object>>)getHttpSession().get("cart");
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
        if(getHttpSession().containsKey("cart")) {
            getHttpSession().remove("cart");
        }
        return true;
    }

    @Override
    public List<Map> createOrder() {
        // TODO 自动生成的方法存根
        return null;
    }

    @Override
    public boolean submitOrder(int orderID) {
        // TODO 自动生成的方法存根
        return false;
    }

    @Override
    public boolean cancelOrder(int orderID) {
        // TODO 自动生成的方法存根
        return false;
    }
    
}