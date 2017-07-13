package service;

import java.util.Map;

import model.UserProfile;

public interface UserService extends BaseService {
    public Map login(String email, String plainPassword);
    public boolean register(UserProfile registerInfo);
    public boolean checkEmailAvailable(String email);
    public boolean logout();
    public Map showUserProfile(int userID);
    public boolean updatePassword(String oldPlainPassword, String newPlainPassword);
    public boolean updateUserProfile(int userID, Map newUserProfile);
}