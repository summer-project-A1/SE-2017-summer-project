package dao;

import java.util.List;

import model.Reserve;

public interface ReserveDao extends BaseDao {
    public List<Reserve> getReservationByUserID(int userID);
}