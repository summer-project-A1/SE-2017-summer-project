package service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

import common.constants.UserRole;
import common.utils.MD5Util;
import common.utils.PasswordUtil;
import dao.UserDao;
import model.User;
import model.UserProfile;
import service.UserService;

public class UserServiceImpl extends BaseServiceImpl implements UserService {
    private UserDao userDao; 
    
    /* ======================================================== */

    public UserDao getUserDao() {
        return userDao;
    }
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
    
    /* ======================================================== */

    @Override
    public Map login(String email, String plainPassword) {
        Boolean logined = isLogined();
        if(!logined) {
            if(email != null) {
                User user = userDao.getUserByEmail(email);
                if(user != null) {
                    if(MD5Util.encoderByMd5(plainPassword).toLowerCase().equals(user.getPassword().toLowerCase())) {
                        setLoginedUserInfo(user);
                        logined = true;
                    }
                }
            }
        }
        
        User userinfo = getLoginedUserInfo();
        Map params = new HashMap();
        if(logined) {
            params.put("result", true);
            params.put("message", "登录成功");
            params.put("userID", userinfo.getUserID());
            params.put("email", userinfo.getEmail());
            params.put("role", (userinfo.getRole()==UserRole.ADMIN)? 0:1);
        }
        else {
            params.put("result", false);
            params.put("message", "用户名或密码错误");
        }
        return params;
    }

    @Override
    public boolean register(UserProfile registerInfo) {
        String email = registerInfo.getEmail();
        if(this.userDao.getUserByEmail(email) != null) {
            return false;
        }
        User newUser = new User();
        Map userProfile = new HashMap();
        
        userProfile.put("name", registerInfo.getName());
        userProfile.put("gender", registerInfo.getGender());
        userProfile.put("mobile", registerInfo.getMobile());
        userProfile.put("deliveryAddressID", new ArrayList());
        
        String profileID = this.userDao.saveUserProfile(userProfile);
        newUser.setProfileID(profileID);
        newUser.setEmail(email);
        newUser.setNickName(registerInfo.getNickName());
        newUser.setPassword(PasswordUtil.getEncryptedPassword(registerInfo.getPlainPassword()));
        newUser.setRole(UserRole.COMMON);
        newUser.setCredit(0);
        newUser.setProvince(registerInfo.getProvince());
        newUser.setCity(registerInfo.getCity());
        newUser.setDistrict(registerInfo.getDistrict());
        newUser.setAddress(registerInfo.getAddress());
        newUser.setImageID("");
        
        this.userDao.save(newUser);
        
        setLoginedUserInfo(newUser);
        return true;
    }

    @Override
    public boolean checkEmailAvailable(String email) {
        // 检查email是否已被注册，未注册（可用）返回true
        if(this.userDao.getUserByEmail(email) != null) {
            // 已被注册
            return false;
        }
        return true;
    }
    
    @Override
    public boolean logout() {
        Boolean logined = isLogined();
        if (logined) {
            clearLoginedUserInfo();
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