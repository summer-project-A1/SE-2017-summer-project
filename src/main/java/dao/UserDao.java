package dao;

import java.util.List;
import java.util.Map;

import model.User;

public interface UserDao {

    public boolean save(Object obj);
    
    public boolean update(Object obj);
    
    public boolean delete(Object obj);
    
    /* ==================================================== */
    
    public User getUserById(int id);
    
    public User getUserByEmail(String email);

    public Map getUserProfileMap(int userID);
    
    public boolean saveUserProfile(int userID, Map userProfile);
    
    public List<User> getAllUsers();
    
}