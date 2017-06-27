package service.impl;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

import common.MD5Util;
import dao.UserDao;
import model.User;
import service.LogInOutService;

public class LogInOutServiceImpl implements LogInOutService {
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

    public Boolean isLogined() {
        return getHttpSession().containsKey("userinfo");
    }

    public Boolean login(String username, String plainPassword) {
        Boolean logined = isLogined();
        if(!logined) {
            if(username != null) {
                User userinfo = getUserDao().getUserByUsername(username);
                System.out.println(userinfo.getPassword());
                System.out.println(MD5Util.encoderByMd5(plainPassword));
                if(userinfo != null) {
                    if(MD5Util.encoderByMd5(plainPassword).toLowerCase().equals(userinfo.getPassword().toLowerCase())) {
                        getHttpSession().put("userinfo", userinfo);
                        logined = true;
                    }
                }
            }
        }
        
        User userinfo = (User)getHttpSession().get("userinfo");
        if(logined) {
            return true;
        }
        else {
            return false;
        }
    }

    public Boolean logout() {
        Boolean logined = isLogined();
        if (logined) {
            getHttpSession().clear();
        }
        return true;
    }
}