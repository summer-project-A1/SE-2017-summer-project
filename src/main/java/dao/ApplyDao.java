package dao;

import java.util.List;

import model.Apply;

public interface ApplyDao {
    public List<Apply> getReservationByUserID(int userID);
}