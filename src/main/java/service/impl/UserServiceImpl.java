package service.impl;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

import common.utils.MD5Util;
import dao.UserDao;
import model.User;
import service.UserService;

public class UserServiceImpl implements UserService {
    private UserDao userDao; 
    
    /* ======================================================== */

    public UserDao getUserDao() {
        return userDao;
    }
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    /* ======================================================== */
    
    public Map<String, Object> getHttpSession() {
        return ActionContext.getContext().getSession();
    }
    
    /* ======================================================== */

    @Override
    public Boolean isLogined() {
        return getHttpSession().containsKey("userinfo");
    }
    
    @Override
    public Boolean login(String email, String plainPassword) {
        Boolean logined = isLogined();
        if(!logined) {
            if(email != null) {
                User userinfo = getUserDao().getUserByEmail(email);
                if(userinfo != null) {
                    if(MD5Util.encoderByMd5(plainPassword).toLowerCase().equals(userinfo.getPassword().toLowerCase())) {
                        getHttpSession().put("userinfo", userinfo);
                        logined = true;
                    }
                }
            }
        }
        
        if(logined) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public Boolean register(String email, String plainPassword) {
        // TODO 自动生成的方法存根
        return null;
    }

    @Override
    public Boolean logout() {
        Boolean logined = isLogined();
        if (logined) {
            getHttpSession().clear();
        }
        return true;
    }

    @Override
    public Map showUserProfile(int userID) {
        // TODO 自动生成的方法存根
        return null;
    }

    @Override
    public Boolean updateUserProfile(int userID, Map newUserProfile) {
        // TODO 自动生成的方法存根
        return null;
    }

    @Override
    public Boolean updateUserPassword(String oldPassword, String newPassword) {
        // TODO 自动生成的方法存根
        return null;
    }
    
}