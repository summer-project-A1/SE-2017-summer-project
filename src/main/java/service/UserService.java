package service;

import java.io.File;
import java.util.List;
import java.util.Map;

import model.FullAddress;
import model.UserProfile;

public interface UserService extends BaseService {
    public Map login(String email, String plainPassword);
    public boolean register(UserProfile registerInfo);
    public boolean checkEmailAvailable(String email);
    public boolean logout();
    public UserProfile showUserProfile();
    public boolean updatePassword(String oldPlainPassword, String newPlainPassword);
    public boolean updateUserProfile(UserProfile newUserProfile);
    public boolean updateUserPicture(File userPicture);
    
    public Map addDeliveryAddress(FullAddress fullAddress);
    public Map getAllDeliveryAddress();
    public Map setDefaultDeliveryAddress(String fullAddressID);
    public boolean deleteDeliveryAddress(String fullAddressID);
}