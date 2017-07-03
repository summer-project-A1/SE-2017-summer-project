package service;

import java.util.List;

import model.Apply;

public interface ReserveService {
    public Boolean reserveBook(int bookID);
    public List<Apply> showReservation();
    public Boolean cancelReservation(int applyID);
}