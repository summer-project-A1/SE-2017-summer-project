package service;

import java.util.Map;

public interface UserService {
    public Boolean isLogined();
    public Boolean login(String email, String plainPassword);
    public Boolean register(String email, String plainPassword);
    public Boolean logout();
    public Map showUserProfile(int userID);
    public Boolean updateUserProfile(int userID, Map newUserProfile);
    public Boolean updateUserPassword(String oldPassword, String newPassword);
}