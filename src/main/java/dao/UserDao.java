package dao;

import java.util.List;
import java.util.Map;

import model.User;

public interface UserDao extends BaseDao {
    
    public User getUserById(int id);
    
    public User getUserByEmail(String email);

    public Map getUserProfileMap(int userID);
    
    public String saveUserProfile(Map userProfile);
    
    public boolean updateUserProfile(User user, Map userProfile);
    
    public List<User> getAllUsers();
    
}