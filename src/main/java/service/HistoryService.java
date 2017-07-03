package service;

import java.util.List;
import java.util.Map;

import model.Apply;
import model.BookRelease;

public interface HistoryService {
    public Map getUserExchangeHistory();
    public Map getUserBorrowHistory();
    public Map getUserOrder();
    public List<Apply> getUserReservation();
    public List<BookRelease> getReleaseBook();
}