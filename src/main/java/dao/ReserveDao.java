package dao;

import java.util.List;

import model.Reserve;

public interface ReserveDao extends BaseDao {
    public Reserve getReserveByID(int reserveID);
    public List<Reserve> getReservationByUserID(int userID);
    public Reserve getFirstReserveByBookID(int bookID);
}