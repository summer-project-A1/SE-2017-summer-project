package service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

import dao.BookDao;
import model.Book;
import service.OrderService;

public class OrderServiceImpl implements OrderService {
    private BookDao bookDao;
    
    /* ========================================================= */
    
    public BookDao getBookDao() {
        return bookDao;
    }

    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    /* ========================================================= */
    
    public Map<String, Object> getHttpSession() {
        return ActionContext.getContext().getSession();
    }

    /* ========================================================= */
    
    @Override
    public boolean addToCart(int bookID) {
        // 添加到http session中，不验证登录状态
        if(this.bookDao.getBookByID(bookID) == null) {
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
    public boolean removeFromCart() {
        // TODO 自动生成的方法存根
        return false;
    }

    @Override
    public boolean emptyCart() {
        // TODO 自动生成的方法存根
        return false;
    }

    @Override
    public boolean createOrder() {
        // TODO 自动生成的方法存根
        return false;
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