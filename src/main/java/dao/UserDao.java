package dao;

import java.util.List;
import java.util.Map;

import model.FullAddress;
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
    public boolean deleteUserProfileInMongo(String profileID);
    public boolean deleteUser(User user);
    public List<User> getAllUsers();
    public List<User> getAllTimeoutUnactiveUser();
    
    public FullAddress getDeliveryAddressByID(int userID, String fullAddressID);
    public FullAddress getDefaultDeliveryAddress(int userID);
    public FullAddress addDeliveryAddress(int userID, FullAddress fullAddress);
    public List<FullAddress> getAllDeliveryAddress(int userID);
    public boolean removeDeliveryAddress(int userID, String fullAddressID);
    public boolean setDefaultDeliveryAddress(int userID, String FullAddressID);
}