package dao;

import java.util.List;

import model.Reserve;

public interface ReserveDao extends BaseDao {
    public Boolean isReserved(int userID, int bookID);
    public Reserve getReserveByID(int reserveID);
    public List<Reserve> getReservationByUserID(int userID);
    public List<Reserve> getReservationByBookID(int bookID);
    public Reserve getFirstReserveByBookID(int bookID);
}