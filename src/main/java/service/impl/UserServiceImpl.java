package service.impl;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

import common.constants.UserRole;
import common.utils.MD5Util;
import common.utils.PasswordUtil;
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
    public boolean isLogined() {
        return getHttpSession().containsKey("userinfo");
    }
    
    @Override
    public boolean login(String email, String plainPassword) {
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
    public boolean register(String email, String plainPassword) {
        if(this.userDao.getUserByEmail(email) != null) {
            return false;
        }
        User newUser = new User();
        newUser.setEmail(email);
        newUser.setPassword(PasswordUtil.getEncryptedPassword(plainPassword));
        newUser.setRole(UserRole.COMMON);
        newUser.setCredit(0);
        newUser.setImageID("");
        newUser.setProfileID("");
        return true;
    }

    @Override
    public boolean logout() {
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
    public boolean updateUserProfile(int userID, Map newUserProfile) {
        // TODO 自动生成的方法存根
        return false;
    }

    @Override
    public boolean updateUserPassword(String oldPassword, String newPassword) {
        // TODO 自动生成的方法存根
        return false;
    }
    
}