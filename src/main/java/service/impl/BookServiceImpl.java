package service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

import common.constants.BookStatus;
import dao.BookDao;
import dao.BookReleaseDao;
import dao.ImageDao;
import model.Book;
import model.BookRelease;
import model.User;
import service.BookService;

public class BookServiceImpl extends BaseServiceImpl implements BookService {
    private BookDao bookDao;
    private BookReleaseDao bookReleaseDao;
    private ImageDao imageDao;
    
    /* ===================================================== */
    
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

    public ImageDao getImageDao() {
        return imageDao;
    }

    public void setImageDao(ImageDao imageDao) {
        this.imageDao = imageDao;
    }

    /* ===================================================== */

    @Override
    public Boolean uploadBook(Map bookInfo) {
        if(!isLogined()) {
            return false;
        }
        String bookName = (String)bookInfo.get("bookName");   // mysql
        String isbn = (String)bookInfo.get("isbn");    // mysql
        String author = (String)bookInfo.get("author");         // 作者 mysql
        String press = (String)bookInfo.get("press");          // 出版社 mysql
        String category1 = (String)bookInfo.get("category1");       // 分类 mysql
        String category2 = (String)bookInfo.get("category2");       // 分类 mysql
        String publishYear = (String)bookInfo.get("publishYear");    // 出版 mongo
        String publishMonth = (String)bookInfo.get("publishMonth");  // mongo
        String editionYear = (String)bookInfo.get("editionYear");     // 版次 mongo 
        String editionMonth = (String)bookInfo.get("editionMonth");    // mongo
        String editionVersion = (String)bookInfo.get("editionVersion"); //mongo
        int page = (int)bookInfo.get("page");              // 页数 mongo
        String bookBinding = (String)bookInfo.get("bookBinding");    // 装帧 mongo
        String bookFormat = (String)bookInfo.get("bookFormat");     // 开本 mongo
        String bookQuality = (String)bookInfo.get("bookQuality");    // 成色 mongo
        String bookDamage = (String)bookInfo.get("bookDamage");     // 损毁情况 mongo
        String intro = (String)bookInfo.get("intro");          // 简介 mongo 
        int bookBorrow = (int)bookInfo.get("bookBorrow");        // 是否可借阅 mysql
        int bookExchange = (int)bookInfo.get("bookExchange");      // 是否可交换 mysql
        int borrowCredit = (int)bookInfo.get("borrowCredit");      // 借阅所需积分 mysql
        int exchangeCredit = (int)bookInfo.get("exchangeCredit");    // 交换所需积分 mysql
        File coverPicture = (File)bookInfo.get("coverPicture");     // 封面 mongo
        File[] otherPicture = (File[])bookInfo.get("otherPicture");   // 其他图片 mongo
        
        Map bookProfile = new HashMap();
        bookProfile.put("category2", category2);
        // bookProfile.put("publish", new HashMap(){{put("year",publishYear);put("month",publishMonth);}});
        // bookProfile.put("edtion", new HashMap(){{put("year",edtionYear);put("month",edtionMonth);put("version",edtionVersion);}});
        bookProfile.put("publishYear", publishYear);
        bookProfile.put("publishMonth", publishMonth);
        bookProfile.put("editionYear", editionYear);
        bookProfile.put("editionMonth", editionMonth);
        bookProfile.put("editionVersion", editionVersion);
        bookProfile.put("page", page);
        bookProfile.put("bookBinding", bookBinding);
        bookProfile.put("bookFormat", bookFormat);
        bookProfile.put("bookQuality", bookQuality);
        bookProfile.put("bookDamage", bookDamage);
        bookProfile.put("intro", intro);
        if(otherPicture != null) {
            List otherPictureID = new ArrayList();
            for(File tmp : otherPicture) {
                otherPictureID.add(this.imageDao.saveImage(tmp));
            }
            bookProfile.put("otherPicture", otherPictureID);
        }
        
        Book newBook = new Book();
        newBook.setBookName(bookName);
        newBook.setIsbn(isbn);
        newBook.setAuthor(author);
        newBook.setPress(press);
        newBook.setCategory(category1);
        newBook.setCanBorrow(bookBorrow);
        newBook.setCanExchange(bookExchange);
        newBook.setReserved(0);
        newBook.setStatus(BookStatus.IDLE);
        
        newBook.setProfileID(this.bookDao.saveBookProfile(bookProfile));
        if(coverPicture != null) {
            newBook.setImageID(this.imageDao.saveImage(coverPicture));
        }
        else {
            newBook.setImageID("");
        }
        this.bookDao.save(newBook);
        
        BookRelease newBookRelease = new BookRelease();
        newBookRelease.setBookID(newBook.getBookID());
        newBookRelease.setBorrowPrice(borrowCredit);
        newBookRelease.setExchangePrice(exchangeCredit);
        newBookRelease.setReleaseTime(new Date());
        newBookRelease.setUserID(getLoginedUserInfo().getUserID());
        this.bookReleaseDao.save(newBookRelease);
        
        return true;
    }

    @Override
    public List<Book> showAllBooks() {
        return this.bookDao.getAllBooks();
    }

    @Override
    public List<Book> showUserBooks(int userID) {
        return this.bookDao.getBooksByUserID(userID);
    }

    @Override
    public List<Book> searchBook(Map condition) {
        // TODO 自动生成的方法存根
        return null;
    }

    @Override
    public Map showBookProfile(int bookID) {
        // 这里的返回值不仅包括mongodb中的bookprofile，也包括mysql中的内容
        Book book = this.bookDao.getBookByID(bookID);
        BookRelease bookRelease = this.bookReleaseDao.getReleaseBookByBookID(bookID);
        Map bookProfile = this.bookDao.getBookProfileMap(book);
        bookProfile.remove("_id");
        bookProfile.put("bookID", book.getBookID());
        bookProfile.put("bookName", book.getBookName());
        bookProfile.put("isbn", book.getIsbn());
        bookProfile.put("author", book.getAuthor());
        bookProfile.put("press", book.getPress());
        bookProfile.put("category1", book.getCategory());
        bookProfile.put("canExchange", (book.getCanExchange()!=0));
        bookProfile.put("canBorrow", (book.getCanBorrow()!=0));
        bookProfile.put("borrowCredit", bookRelease.getBorrowPrice());
        bookProfile.put("exchangeCredit", bookRelease.getExchangePrice());
        bookProfile.put("reserved", (book.getReserved()!=0));
        bookProfile.put("coverPicture", book.getImageID());
        bookProfile.put("status", book.getStatus().toString());
        return bookProfile;
    }
    
}