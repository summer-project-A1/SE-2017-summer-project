package service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import common.cache.AllBookCategory;
import common.constants.BookStatus;
import dao.BookDao;
import dao.BookReleaseDao;
import dao.CategoryDao;
import dao.ImageDao;
import model.*;
import net.sf.json.JSONObject;
import org.apache.http.*;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import service.BookService;

public class BookServiceImpl extends BaseServiceImpl implements BookService {
    private BookDao bookDao;
    private CategoryDao categoryDao;
    private BookReleaseDao bookReleaseDao;
    private ImageDao imageDao;
    
    /* ===================================================== */
    
    public BookDao getBookDao() {
        return bookDao;
    }

    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }   
    
    
    public CategoryDao getCategoryDao() {
        return categoryDao;
    }

    public void setCategoryDao(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
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
        Map bookProfileInMongo = new HashMap();
//        bookProfileInMongo.put("category2", bookProfile.getCategory2());
        // bookProfileInMongo.put("publish", new HashMap(){{put("year",publishYear);put("month",publishMonth);}});
        // bookProfileInMongo.put("edtion", new HashMap(){{put("year",edtionYear);put("month",edtionMonth);put("version",edtionVersion);}});
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
        newBook.setCategory1(bookProfile.getCategory1());
        newBook.setCategory2(bookProfile.getCategory2());
        newBook.setPublishYear(bookProfile.getPublishYear());
        newBook.setPublishMonth(bookProfile.getPublishMonth());;
        newBook.setCanBorrow(bookProfile.getCanBorrow());
        newBook.setCanExchange(bookProfile.getCanExchange());
        newBook.setBorrowCredit(bookProfile.getBorrowCredit());
        newBook.setBuyCredit(bookProfile.getBuyCredit());
        newBook.setReserved(0);
        newBook.setStatus(BookStatus.IDLE);
        
        // saveBookProfile
        newBook.setProfileID(this.bookDao.saveBookProfileInMongo(bookProfileInMongo));
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
        newBookRelease.setReleaseTime(new Date());
        newBookRelease.setUserID(getLoginedUserInfo().getUserID());
        this.bookReleaseDao.save(newBookRelease);
        
        return true;
    }

    @Override
    public Boolean isLastPart(int part, int pageSize) {  // 数据库分页取数据，判断当前页是否是最后一部分数据
        int totalCount = this.bookDao.getAllBooksCount();
        return part*pageSize >= totalCount;
    }
    
    @Override
    public List<Book> showAllBooksByPage(int part,int pageSize) {
        List<Book> bookList = this.bookDao.getAllBooksByPage(part, pageSize);
        for(Book book : bookList) {
            book.setBookStatus(book.getStatus().toString());
        }
        return bookList;
    }

    @Override
    public List<Book> showUserBooks() {
        User user = this.getLoginedUserInfo();
        int userID = user.getUserID();
        List<Book> bookList = this.bookDao.getBooksByUserID(userID);
        for(Book book : bookList) {
            book.setBookStatus(book.getStatus().toString());
        }
        return bookList;
    }

    @Override
    public List<Book> searchByTextByPage(String searchText,int part,int pageSize) {
        List<Book> bookList = this.bookDao.searchByTextByPage(searchText,part,pageSize);
        for(Book book : bookList) {
            book.setBookStatus(book.getStatus().toString());
        }
        return bookList;
    }
    
    @Override
    public List<Book> searchBook(Integer part, Integer pageSize, String category1NameString, String category2NameString, String yearString, String statusString) {
        Map conditions = new HashMap();
        conditions.put("part", part);
        conditions.put("pageSize", pageSize);
        if(category1NameString != null && !category1NameString.equals("")) {
            conditions.put("category1", category1NameString);
            System.out.println(category1NameString);
        }
        if(category2NameString != null && !category2NameString.equals("")) {
            conditions.put("category2", category2NameString);
        }
        if(yearString != null && !yearString.equals("")) {
            conditions.put("publishYear", Integer.valueOf(yearString));
        }
        if(statusString != null && !statusString.equals("")) {
            if(statusString.equals("canBorrow")) {
                conditions.put("canBorrow", 1);
                conditions.put("canExchange", 0);
            }
            else if(statusString.equals("canExchange")) {
                conditions.put("canBorrow", 0);
                conditions.put("canExchange", 1);
            }
        }
        
        List<Book> bookList = this.bookDao.searchByCondition(conditions, part, pageSize);
        for(Book book : bookList) {
            book.setBookStatus(book.getStatus().toString());
        }
        return bookList;
    }

    @Override
    public Book showBook(int bookID) {
        Book book = this.bookDao.getBookByID(bookID);
        book.setBookStatus(book.getStatus().toString());
        return book;
    }
    
    @Override
    public List<Book> getRecommendBookList() {
        return this.bookDao.getRecommendBook();
    }
    
    @Override
    public BookProfile showBookProfile(int bookID) {
        // 这里的返回值不仅包括mongodb中的内容，也包括mysql中的内容
        Book book = this.bookDao.getBookByID(bookID);
        BookRelease bookRelease = this.bookReleaseDao.getReleaseBookByBookID(bookID);
        Map bookProfileInMongo = this.bookDao.getBookProfileMapInMongo(book);
        BookProfile bookProfile = new BookProfile();
        
        bookProfile.setBookID(book.getBookID());
        bookProfile.setBookName(book.getBookName());
        bookProfile.setIsbn(book.getIsbn());
        bookProfile.setAuthor(book.getAuthor());
        bookProfile.setPress(book.getPress());
        bookProfile.setCategory1(book.getCategory1());
        bookProfile.setCategory2(book.getCategory2());
        bookProfile.setPublishYear(book.getPublishYear());
        bookProfile.setPublishMonth(book.getPublishMonth());
        bookProfile.setCanExchange(book.getCanExchange());
        bookProfile.setCanBorrow(book.getCanBorrow());
        bookProfile.setReserved(book.getReserved());
        bookProfile.setStatus(book.getStatus());
        bookProfile.setBookStatus(book.getStatus().toString());
        bookProfile.setImageID(book.getImageID());
        
        bookProfile.setBorrowCredit(book.getBorrowCredit());
        bookProfile.setBuyCredit(book.getBuyCredit());
        bookProfile.setReleaseTime(bookRelease.getReleaseTime());
        
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
    
    @Override
    public Boolean updateBook(BookProfile bookProfile) {
        Integer bookID = bookProfile.getBookID();
        System.out.println(bookID);
        BookRelease bookRelease = this.bookReleaseDao.getReleaseBookByBookID(bookID);
        Book oldBook = this.bookDao.getBookByID(bookID);
        if(bookRelease.getUserID() != this.getLoginedUserInfo().getUserID()
                || oldBook.getStatus() != BookStatus.IDLE
                || oldBook.getCanBorrow() != 0 || oldBook.getCanExchange() != 0
                ) {
            return false;    // 只能修改自己发布的书
        }
        Map bookProfileInMongo = this.bookDao.getBookProfileMapInMongo(oldBook);
//      bookProfileInMongo.put("category2", bookProfile.getCategory2());
        // bookProfileInMongo.put("publish", new HashMap(){{put("year",publishYear);put("month",publishMonth);}});
        // bookProfileInMongo.put("edtion", new HashMap(){{put("year",edtionYear);put("month",edtionMonth);put("version",edtionVersion);}});
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
            List<String> oldOtherPictureID = (List<String>)bookProfileInMongo.get("otherPictureID");
            for(String tmp : oldOtherPictureID) {
                this.imageDao.deleteImageById(tmp);
            }
            List otherPictureID = new ArrayList();
            for(File tmp : bookProfile.getOtherPicture()) {
                // save otherPicture
                otherPictureID.add(this.imageDao.saveImage(tmp));
            }
            bookProfileInMongo.put("otherPictureID", otherPictureID);
        }
        
        oldBook.setBookName(bookProfile.getBookName());
        oldBook.setIsbn(bookProfile.getIsbn());
        oldBook.setAuthor(bookProfile.getAuthor());
        oldBook.setPress(bookProfile.getPress());
        oldBook.setCategory1(bookProfile.getCategory1());
        oldBook.setCategory2(bookProfile.getCategory2());
        oldBook.setPublishYear(bookProfile.getPublishYear());
        oldBook.setPublishMonth(bookProfile.getPublishMonth());;
        oldBook.setCanBorrow(bookProfile.getCanBorrow());
        oldBook.setCanExchange(bookProfile.getCanExchange());
        oldBook.setBorrowCredit(bookProfile.getBorrowCredit());
        oldBook.setBuyCredit(bookProfile.getBuyCredit());
      
        // updateBookProfile
        this.bookDao.updateBookProfileInMongo(oldBook, bookProfileInMongo);
        if(bookProfile.getCoverPicture() != null) {
            // save coverPicture
            this.imageDao.deleteImageById(oldBook.getImageID());
            oldBook.setImageID(this.imageDao.saveImage(bookProfile.getCoverPicture()));
        }
        this.bookDao.update(oldBook);

        return true;
    }
    
    @Override
    public Boolean offlineBook(int bookID) {
        // 下架书（把可借阅和可交换都设为0）
        BookRelease bookRelease = this.bookReleaseDao.getReleaseBookByBookID(bookID);
        if(bookRelease.getUserID() != this.getLoginedUserInfo().getUserID()) {
            return false;    // 只能修改自己发布的书
        }
        Book oldBook = this.bookDao.getBookByID(bookID);
        oldBook.setCanBorrow(0);
        oldBook.setCanExchange(0);
        this.bookDao.update(oldBook);
        return true;
    }
    
    @Override
    public Boolean deleteBook(int bookID) {
        // 从数据库中删除书，禁止调用此函数！！！
        return null;
    }
    
    @Override
    public List<Book> showBooksByCategory1NameByPage(String category1Name, int part, int pageSize) {
        List<Book> bookList = this.bookDao.getBooksByCategory1NameByPage(category1Name, part, pageSize);
        for(Book book : bookList) {
            book.setBookStatus(book.getStatus().toString());
        }
        return bookList;
    }
    
    @Override
    public List<Book> showBooksByCategory2NameByPage(String category2Name, int part, int pageSize) {
        List<Book> bookList = this.bookDao.getBooksByCategory2NameByPage(category2Name, part, pageSize);
        for(Book book : bookList) {
            book.setBookStatus(book.getStatus().toString());
        }
        return bookList;
    }
    
    @Override
    public List<Category1> showAllCategory1s() {
        if(AllBookCategory.getAllBookCategory()==null) {
            AllBookCategory.setAllBookCategory(this.categoryDao.getAllCategory());
        }
        return AllBookCategory.getAllBookCategory();
    }
    
    @Override
    public List<Book> showBooksByConditions(Integer part, Integer pageSize, String category1Name, String category2Name) {
        Map conditions = new HashMap();
        /*
        if(part != null && pageSize != null) {
            conditions.put("part", part);
            conditions.put("pageSize", pageSize);
        }
        */
        if(category1Name != null) {
            conditions.put("category1Name", category1Name);
        }
        if(category2Name != null) {
            conditions.put("category2Name", category2Name);
        }
        List<Book> result = this.bookDao.searchByCondition(conditions, part, pageSize);
        return result;
    }

    @Override
    public Map getInfoByIsbn(String isbn){
        String url = "https://api.douban.com/v2/book/isbn/"+isbn;
        Map returnMap = new HashMap();
        String result = "{}";
        DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
        try {
            HttpGet httpGet = new HttpGet(url);
            HttpResponse response = defaultHttpClient.execute(httpGet);
            HttpEntity httpEntity = response.getEntity();
            result = EntityUtils.toString(httpEntity);
            JSONObject json = JSONObject.fromObject(result);

            String[] fieldList = {"author","publisher","title","summary","pages"};
            String[] resultList = {"","","","",""};
            for(int i=0;i < fieldList.length;i++){
                String parse = json.get(fieldList[i]).toString();
                parse = parse.replace("[","");
                parse = parse.replace("]","");
                parse = parse.replace("//",",");
                parse = parse.replaceAll("\"","");
                resultList[i] = parse;
                //System.out.println(resultList[i]);
                returnMap.put(fieldList[i],resultList[i]);
            }
            returnMap.put("success",true);
            return returnMap;
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            defaultHttpClient.getConnectionManager().shutdown();
        }
        returnMap.put("success",false);
        return returnMap;
    }
}