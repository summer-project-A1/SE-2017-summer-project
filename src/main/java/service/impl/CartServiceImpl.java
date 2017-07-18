package service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import dao.BookDao;
import model.Book;
import service.CartService;

public class CartServiceImpl extends BaseServiceImpl implements CartService {
    private BookDao bookDao;
    
    /* ====================================================== */
    
    public BookDao getBookDao() {
        return bookDao;
    }

    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    /* ====================================================== */
    
    @Override
    public Map showBorrowCart() {
        List<Map<String, Object>> cartList;
        Map result = new HashMap();
        List booksInBorrowCart = new ArrayList();
        Integer totalCredit = 0;
        if(getHttpSession().containsKey("borrowCart")) {
            cartList = (List<Map<String, Object>>)getHttpSession().get("borrowCart");
        }
        else {
            cartList = new ArrayList<Map<String, Object>>();
        }
        
        for(Map<String, Object> cartListItem : cartList) {
            int bookID = (int)cartListItem.get("bookID");
            Book book = this.bookDao.getBookByID(bookID);
            if(book != null) {
                booksInBorrowCart.add(book);
                totalCredit += book.getBorrowCredit();
            }
        }
        result.put("booksInBorrowCart", booksInBorrowCart);
        result.put("totalCredit", totalCredit);
        return result;
    }

    @Override
    public boolean addToBorrowCart(int bookID) {
        // 添加到http session中，不验证登录状态
        Book book = this.bookDao.getBookByID(bookID); 
        if(book == null) {
            return false;
        }
        List<Map<String, Object>> cartList;
        if(getHttpSession().containsKey("borrowCart")) {
            cartList = (List<Map<String, Object>>)getHttpSession().get("borrowCart");
        }
        else {
            cartList = new ArrayList<Map<String, Object>>();
            getHttpSession().put("borrowCart", cartList);
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
        newCartListItem.put("borrowCredit",book.getBorrowCredit());
        cartList.add(newCartListItem);
        return true;
    }

    @Override
    public boolean removeFromBorrowCart(int bookID) {
        List<Map<String, Object>> cartList;
        if(getHttpSession().containsKey("borrowCart")) {
            cartList = (List<Map<String, Object>>)getHttpSession().get("borrowCart");
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
    public boolean emptyBorrowCart() {
        if(getHttpSession().containsKey("borrowCart")) {
            getHttpSession().remove("borrowCart");
        }
        return true;
    }
    
    /* ======================== */
    
    @Override
    public boolean addToBuyCart(int bookID) {
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
        newCartListItem.put("buyCredit",book.getBuyCredit());
        cartList.add(newCartListItem);
        return true;
    }

    @Override
    public Map showBuyCart() {
        List<Map<String, Object>> cartList;
        Map result = new HashMap();
        List booksInBuyCart = new ArrayList();
        Integer totalCredit = 0;
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
                booksInBuyCart.add(book);
                totalCredit += book.getBuyCredit();
            }
        }
        result.put("booksInBuyCart", booksInBuyCart);
        result.put("totalCredit", totalCredit);
        return result;
    }

    @Override
    public boolean removeFromBuyCart(int bookID) {
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
    public boolean emptyBuyCart() {
        if(getHttpSession().containsKey("buyCart")) {
            getHttpSession().remove("buyCart");
        }
        return true;
    }
}