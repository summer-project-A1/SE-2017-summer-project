package service;

import java.util.Map;

import model.User;

public interface BaseService {
    public Map<String, Object> getHttpSession();
    public boolean isLogined();
    public User getLoginedUserInfo();
    public void setLoginedUserInfo(User userInfo);
    public void clearLoginedUserInfo();
    
    public String getBasePath();
}