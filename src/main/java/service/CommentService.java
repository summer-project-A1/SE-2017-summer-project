package service;

import model.CommentProfile;

import java.util.List;
import java.util.Map;

public interface CommentService extends BaseService {
    public boolean commentBook(int borrowID,int bookID,String comment);
    public List<CommentProfile> getBookComment(int bookID);
    public boolean deleteComment(int commentID);
    public boolean honestyRatingInBorrow(int borrowID,int creditRating);
    public boolean honestyRatingInBuy(int orderID,int creditRating);
    public boolean honestyRatingInExchange(int exchangeID,int creditRating);
    public boolean honestyRatingInExchangeHistory(int ehID,int creditRating);
}