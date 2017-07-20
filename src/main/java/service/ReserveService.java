package service;

import java.util.List;

import model.ReservationProfile;
import model.Reserve;

public interface ReserveService extends BaseService {
    public String reserveBook(int bookID);
    public List<ReservationProfile> showReservation();
    public Boolean cancelReservation(int reserveID);
}