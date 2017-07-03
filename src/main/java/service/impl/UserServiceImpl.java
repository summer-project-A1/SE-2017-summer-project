package service.impl;

import java.util.HashMap;
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
    public Map login(String email, String plainPassword) {
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
        
        User userinfo = (User)getHttpSession().get("userinfo");
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
    public boolean register(Map registerInfo) {
        String email = (String)registerInfo.get("email");
        if(this.userDao.getUserByEmail(email) != null) {
            return false;
        }
        String plainPassword = (String)registerInfo.get("password");
        String name = (String)registerInfo.get("name");
        String gender = (String)registerInfo.get("gender");
        String mobile = (String)registerInfo.get("mobile");
        String province = (String)registerInfo.get("province");
        String city = (String)registerInfo.get("city");
        String district = (String)registerInfo.get("district");
        String address = (String)registerInfo.get("address");
        
        User newUser = new User();
        newUser.setEmail(email);
        newUser.setPassword(PasswordUtil.getEncryptedPassword(plainPassword));
        newUser.setRole(UserRole.COMMON);
        newUser.setCredit(0);
        newUser.setImageID("");
        newUser.setProfileID("");
        this.userDao.save(newUser);
        
        Map userProfile = new HashMap();
        userProfile.put("userID", newUser.getUserID());
        userProfile.put("name", name);
        userProfile.put("gender", gender);
        userProfile.put("mobile", mobile);
        userProfile.put("province", province);
        userProfile.put("city", city);
        userProfile.put("district", district);
        userProfile.put("address", address);
        
        String profileID = this.userDao.saveOrUpdateUserProfile(userProfile);
        newUser.setProfileID(profileID);
        
        this.userDao.update(newUser);
        
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