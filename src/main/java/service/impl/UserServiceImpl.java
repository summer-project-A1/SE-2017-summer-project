package service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

import common.constants.UserRole;
import common.utils.MD5Util;
import common.utils.PasswordUtil;
import dao.UserDao;
import model.FullAddress;
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
                User userinfo = getUserDao().getUserByEmail(email);
                if(userinfo != null) {
                    if(MD5Util.encoderByMd5(plainPassword).toLowerCase().equals(userinfo.getPassword().toLowerCase())) {
                        setLoginedUserInfo(userinfo);
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
        userProfile.put("deliveryAddress", new ArrayList());
        
        String profileID = this.userDao.saveUserProfileInMongo(userProfile);
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
            //clearLoginedUserInfo();
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
    public boolean updatePassword(String oldPlainPassword, String newPlainPassword) {
        User user = this.getLoginedUserInfo();
        System.out.println(oldPlainPassword);
        System.out.println(user.getPassword());
        if(PasswordUtil.checkPassword(oldPlainPassword, user.getPassword())) {
            user.setPassword(PasswordUtil.getEncryptedPassword(newPlainPassword));
            this.userDao.update(user);
            return true;
        }
        else {
            return false;
        }
    }
    
    @Override
    public boolean updateUserProfile(UserProfile newUserProfile) {
        // TODO 自动生成的方法存根
        return false;
    }
    
    @Override
    public Map addDeliveryAddress(FullAddress fullAddress) {
        int userID = this.getLoginedUserInfo().getUserID();
        Map params = new HashMap();
        FullAddress result = this.userDao.addDeliveryAddress(userID, fullAddress);
        params.put("result", true);
        params.put("newAddressID", result.getFullAddressID());
        params.put("newAddress", result.toFullAddressString());
        return params;
    }
    
    @Override
    public Map getAllDeliveryAddress() {
        int userID = this.getLoginedUserInfo().getUserID();
        Map params = new HashMap();
        List<FullAddress> defaultAddress = new ArrayList<FullAddress>();
        List<FullAddress> otherAddress = new ArrayList<FullAddress>();
        List<FullAddress> all = this.userDao.getAllDeliveryAddress(userID);
        for(FullAddress tmp : all) {
            tmp.setFullAddressString(tmp.toFullAddressString());
            if(tmp.getIsDefault()) {
                defaultAddress.add(tmp);
            }
            else {
                otherAddress.add(tmp);
            }
        }
        params.put("result", true);
        params.put("defaultAddrList", defaultAddress);
        params.put("addrList", otherAddress);
        return params;
    }
    
    @Override
    public Map setDefaultDeliveryAddress(String fullAddressID) {
        int userID = this.getLoginedUserInfo().getUserID();
        Map params = new HashMap();
        FullAddress oldDefault = this.userDao.getDefaultDeliveryAddress(userID);
        this.userDao.setDefaultDeliveryAddress(userID, fullAddressID);
        FullAddress newDefault = this.userDao.getDeliveryAddressByID(userID, fullAddressID);
        params.put("result",true);
        if(oldDefault != null) { 
            params.put("oldDefaultAddrID", oldDefault.getFullAddressID());
            params.put("oldDefaultAddr", oldDefault.toFullAddressString());
        }
        else {
            params.put("oldDefaultAddrID", null);
            params.put("oldDefaultAddr", null);
        }
        params.put("newDefaultAddrID", newDefault.getFullAddressID());
        params.put("newDefaultAddr", newDefault.toFullAddressString());
        return params;
    }
    
    @Override
    public boolean deleteDeliveryAddress(String fullAddressID) {
        int userID = this.getLoginedUserInfo().getUserID();
        this.userDao.removeDeliveryAddress(userID, fullAddressID);
        return true;
    }
}