package dao;

import java.util.List;
import java.util.Map;

import model.User;
import model.UserProfile;

public interface UserDao extends BaseDao {
    public User getUserById(int id);
    public User getUserByEmail(String email);
    public UserProfile getUserProfile(int userID);
    public boolean saveUserProfile(UserProfile newUserProfile);
    public boolean updateUserProfile(UserProfile newUserProfile);
    public Map getUserProfileMapInMongo(int userID);
    public String saveUserProfileInMongo(Map userProfile);
    public boolean updateUserProfileInMongo(User user, Map userProfile);
    public List<User> getAllUsers();
    
}