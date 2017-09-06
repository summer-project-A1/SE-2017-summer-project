package service.impl;

import dao.BorrowHistoryDao;
import dao.OrderDao;
import dao.UserDao;
import model.*;
import service.CommentService;
import dao.CommentDao;
import dao.ExchangeHistoryDao;

import java.util.*;

public class CommentServiceImpl extends BaseServiceImpl implements CommentService {
    private CommentDao commentdao;
    private UserDao userDao;
    private BorrowHistoryDao borrowHistoryDao;
    private ExchangeHistoryDao exchangeHistoryDao;
    private OrderDao orderDao;

    public CommentDao getCommentdao() {
        return commentdao;
    }

    public void setCommentdao(CommentDao commentdao) {
        this.commentdao = commentdao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public BorrowHistoryDao getBorrowHistoryDao() {
        return borrowHistoryDao;
    }

    public void setBorrowHistoryDao(BorrowHistoryDao borrowHistoryDao) {
        this.borrowHistoryDao = borrowHistoryDao;
    }

    public ExchangeHistoryDao getExchangeHistoryDao() {
        return exchangeHistoryDao;
    }

    public void setExchangeHistoryDao(ExchangeHistoryDao exchangeHistoryDao) {
        this.exchangeHistoryDao = exchangeHistoryDao;
    }

    public OrderDao getOrderDao() {
        return orderDao;
    }

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }


    @Override
    public boolean commentBook(int borrowID, int bookID, String comment) {
        if (!isLogined()) {
            return false;
        }
        Map commentInMongo = new HashMap();
        commentInMongo.put("bookComment", comment);
        commentInMongo.put("commentTime", new Date());

        User user = getLoginedUserInfo();
        Comment newComment = new Comment();
        newComment.setCommentID(borrowID);
        newComment.setBookID(bookID);
        newComment.setUserID(user.getUserID());
        newComment.setProfileID(this.commentdao.saveBookComment(commentInMongo));
        this.commentdao.save(newComment);
        return true;
    }

    @Override
    public List<CommentProfile> getBookComment(int bookID) {
        List list = this.commentdao.getCommentsByBookID(bookID);
        List<CommentProfile> commentProfileList = new ArrayList<>();

        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Comment comment = (Comment) list.get(i);
                Map commentProfileInMongo = this.commentdao.getBookCommentProfileMap(comment);
                CommentProfile commentProfile = new CommentProfile();
                commentProfile.setCommentID(comment.getCommentID());
                commentProfile.setBookID(bookID);
                commentProfile.setUserID(comment.getUserID());
                commentProfile.setEmail((this.userDao.getUserById(comment.getUserID())).getEmail());
                commentProfile.setCommentContent((String) commentProfileInMongo.get("bookComment"));
                commentProfile.setCommentDate((Date) commentProfileInMongo.get("commentTime"));
                commentProfileList.add(commentProfile);
            }
            return commentProfileList;
        }
        return null;
    }

    @Override
    public boolean deleteComment(int commentID){
        Comment comment = this.commentdao.getCommentsByCommentID(commentID);
        Map commentProfileInMongo = this.commentdao.getBookCommentProfileMap(comment);
        this.commentdao.deleteCommentProfile(commentProfileInMongo);
        this.commentdao.delete(comment);
        return true;
    }

    @Override
    public boolean honestyRatingInBorrow(int borrowID,int creditRating){
        BorrowHistory bh = this.borrowHistoryDao.getBorrowHistoryByBorrowID(borrowID);
        int userID1 = bh.getUserID1(); //买家
        int userID2 = bh.getUserID2(); //卖家
        User user = getLoginedUserInfo();
        int currentID = user.getUserID();
        int ratingID = 0;
        if(currentID == userID1){
            ratingID = userID2; //买家评论卖家
            bh.setComment1(creditRating);
        }else{
            ratingID = userID1; //卖家评论买家
            bh.setComment2(creditRating);
        }
        User ratingUser = this.userDao.getUserById(ratingID);
        ratingUser.setHonesty(ratingUser.getHonesty()+creditRating);
        this.userDao.update(ratingUser);
        this.borrowHistoryDao.update(bh);
        return true;
    }

    @Override
    public boolean honestyRatingInBuy(int orderID,int creditRating){
        Order order = this.orderDao.getOrderByID(orderID);
        int buyerID = order.getBuyerID();
        int sellerID = order.getSellerID();
        User user = getLoginedUserInfo();
        int currentID = user.getUserID();
        int ratingID = 0;
        if(currentID == buyerID){
            ratingID = sellerID; //买家评论卖家
            order.setBuyerComment(creditRating);
        }else{
            ratingID = buyerID;
            order.setSellerComment(creditRating);
        }
        User ratingUser = this.userDao.getUserById(ratingID);
        ratingUser.setHonesty(ratingUser.getHonesty()+creditRating);
        this.userDao.update(ratingUser);
        this.orderDao.update(order);
        return true;
    }

    @Override
    public boolean honestyRatingInExchange(int exchangeID,int creditRating) {
        ExchangeHistory eh = this.exchangeHistoryDao.getExchangeHistoryByID(exchangeID);
        int user1ID = eh.getUserID1();    // 交换的申请者
        int user2ID = eh.getUserID2();    // 交换的被申请者
        User user = getLoginedUserInfo();
        int currentID = user.getUserID();
        int ratingID = 0;
        if(currentID == user1ID){
            ratingID = user2ID; //申请者评论被申请者
            eh.setComment1(creditRating);
        }else{
            ratingID = user1ID;
            eh.setComment2(creditRating);
        }
        User ratingUser = this.userDao.getUserById(ratingID);
        ratingUser.setHonesty(ratingUser.getHonesty()+creditRating);
        this.userDao.update(ratingUser);
        this.exchangeHistoryDao.update(eh);
        return true;
    }
}