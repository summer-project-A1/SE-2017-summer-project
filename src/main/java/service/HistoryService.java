package service;

import java.util.List;
import java.util.Map;

import model.BookRelease;
import model.Reserve;

public interface HistoryService extends BaseService {
    public Map getUserExchangeHistory(int userID);
    public Map getUserBorrowHistory(int userID);
    public Map getUserOrder(int userID);
    public List<Reserve> getUserReservation(int userID);
    public List<BookRelease> getReleaseBook(int userID);
}