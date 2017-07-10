package service;

import java.util.List;

import model.Reserve;

public interface ReserveService extends BaseService {
    public Boolean reserveBook(int bookID);
    public List<Reserve> showReservation();
    public Boolean cancelReservation(int applyID);
}