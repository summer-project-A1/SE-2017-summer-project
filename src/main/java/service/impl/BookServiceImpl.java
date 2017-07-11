package service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.constants.BookStatus;
import dao.BookDao;
import dao.BookReleaseDao;
import dao.ImageDao;
import model.Book;
import model.BookInfo;
import model.BookProfile;
import model.BookRelease;
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
    public Boolean uploadBook(BookProfile bookProfile) {
        if(!isLogined()) {
            return false;
        }
        Map bookProfileInMongo = new HashMap();
        bookProfileInMongo.put("category2", bookProfile.getCategory2());
        // bookProfileInMongo.put("publish", new HashMap(){{put("year",publishYear);put("month",publishMonth);}});
        // bookProfileInMongo.put("edtion", new HashMap(){{put("year",edtionYear);put("month",edtionMonth);put("version",edtionVersion);}});
        bookProfileInMongo.put("publishYear", bookProfile.getPublishYear());
        bookProfileInMongo.put("publishMonth", bookProfile.getPublishMonth());
        bookProfileInMongo.put("editionYear", bookProfile.getEditionYear());
        bookProfileInMongo.put("editionMonth", bookProfile.getEditionMonth());
        bookProfileInMongo.put("editionVersion", bookProfile.getEditionVersion());
        bookProfileInMongo.put("page", bookProfile.getPage());
        bookProfileInMongo.put("bookBinding", bookProfile.getBookBinding());
        bookProfileInMongo.put("bookFormat", bookProfile.getBookFormat());
        bookProfileInMongo.put("bookQuality", bookProfile.getBookQuality());
        bookProfileInMongo.put("bookDamage", bookProfile.getBookDamage());
        bookProfileInMongo.put("intro", bookProfile.getIntro());
        if(bookProfile.getOtherPicture() != null) {
            List otherPictureID = new ArrayList();
            for(File tmp : bookProfile.getOtherPicture()) {
                // save otherPicture
                otherPictureID.add(this.imageDao.saveImage(tmp));
            }
            bookProfileInMongo.put("otherPictureID", otherPictureID);
        }
        
        Book newBook = new Book();
        newBook.setBookName(bookProfile.getBookName());
        newBook.setIsbn(bookProfile.getIsbn());
        newBook.setAuthor(bookProfile.getAuthor());
        newBook.setPress(bookProfile.getPress());
        newBook.setCategory1(Integer.parseInt(bookProfile.getCategory1()));
        newBook.setCategory2(Integer.parseInt(bookProfile.getCategory2()));
        newBook.setCanBorrow(bookProfile.getCanBorrow());
        newBook.setCanExchange(bookProfile.getCanExchange());
        newBook.setReserved(0);
        newBook.setStatus(BookStatus.IDLE);
        
        // saveBookProfile
        newBook.setProfileID(this.bookDao.saveBookProfile(bookProfileInMongo));
        if(bookProfile.getCoverPicture() != null) {
            // save coverPicture
            newBook.setImageID(this.imageDao.saveImage(bookProfile.getCoverPicture()));
        }
        else {
            newBook.setImageID("");
        }
        this.bookDao.save(newBook);
        
        BookRelease newBookRelease = new BookRelease();
        newBookRelease.setBookID(newBook.getBookID());
        newBookRelease.setBorrowCredit(bookProfile.getBorrowCredit());
        newBookRelease.setBuyCredit(bookProfile.getBuyCredit());
        newBookRelease.setReleaseTime(new Date());
        newBookRelease.setUserID(getLoginedUserInfo().getUserID());
        this.bookReleaseDao.save(newBookRelease);
        
        return true;
    }

    @Override
    public List<BookInfo> showAllBookInfoByPage(int part,int pageSize) {
        return this.bookDao.getAllBookInfoByPage(part,pageSize);
    }

    @Override
    public List<Book> showUserBooks(int userID) {
        return this.bookDao.getBooksByUserID(userID);
    }

    @Override
    public List<Book> searchByTextByPage(String searchText,int part,int pageSize) {
        return this.bookDao.searchByTextByPage(searchText,part,pageSize);
    }
    
    @Override
    public List<Book> searchBook(Map condition) {
        // TODO 自动生成的方法存根
        return null;
    }

    public BookInfo showBookInfo(int bookID) {
        return this.bookDao.getBookInfoByID(bookID);
    }
    
    @Override
    public BookProfile showBookProfile(int bookID) {
        // 这里的返回值不仅包括mongodb中的内容，也包括mysql中的内容
        Book book = this.bookDao.getBookByID(bookID);
        BookRelease bookRelease = this.bookReleaseDao.getReleaseBookByBookID(bookID);
        Map bookProfileInMongo = this.bookDao.getBookProfileMap(book);
        BookProfile bookProfile = new BookProfile();
        
        bookProfile.setBookID(book.getBookID());
        bookProfile.setBookName(book.getBookName());
        bookProfile.setIsbn(book.getIsbn());
        bookProfile.setAuthor(book.getAuthor());
        bookProfile.setPress(book.getPress());
        bookProfile.setCategory1(book.getCategory1().toString());
        bookProfile.setCategory2(book.getCategory2().toString());
        bookProfile.setCanExchange(book.getCanExchange());
        bookProfile.setCanBorrow(book.getCanBorrow());
        bookProfile.setReserved(book.getReserved());
        bookProfile.setStatus(book.getStatus());
        bookProfile.setBookStatus(book.getStatus().toString());
        bookProfile.setImageID(book.getImageID());
        
        bookProfile.setBorrowCredit(bookRelease.getBorrowCredit());
        bookProfile.setBuyCredit(bookRelease.getBuyCredit());
        bookProfile.setReleaseTime(bookRelease.getReleaseTime());
        
        bookProfile.setPublishYear((int)bookProfileInMongo.get("publishYear"));
        bookProfile.setPublishMonth((int)bookProfileInMongo.get("publishMonth"));
        bookProfile.setEditionYear((int)bookProfileInMongo.get("editionYear"));
        bookProfile.setEditionMonth((int)bookProfileInMongo.get("editionMonth"));
        bookProfile.setEditionVersion((int)bookProfileInMongo.get("editionVersion"));
        bookProfile.setPage((int)bookProfileInMongo.get("page"));
        bookProfile.setBookBinding((String)bookProfileInMongo.get("bookBinding"));
        bookProfile.setBookFormat((String)bookProfileInMongo.get("bookFormat"));
        bookProfile.setBookQuality((String)bookProfileInMongo.get("bookQuality"));
        bookProfile.setBookDamage((String)bookProfileInMongo.get("bookDamage"));
        bookProfile.setIntro((String)bookProfileInMongo.get("intro"));
        bookProfile.setOtherPictureIDList((List<String>)bookProfileInMongo.get("otherPictureID"));
        
        return bookProfile;
    }
    
}