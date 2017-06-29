package dao;

import java.util.List;

import model.Apply;

public interface ApplyDao extends BaseDao {
    public List<Apply> getReservationByUserID(int userID);
}