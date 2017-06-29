package model;

import common.constants.UserRole;

public class User {
    private int userID;
    // 不保存username属性
    private String password;
    private String email;       // 邮箱作为登录名，具有唯一性
    private int credit;         // 用户积分
    private UserRole role;      // 用户角色（管理员/普通）
    private String profileID;         // 用户的详细信息在monggodb中的id
    private String imageID;           // 用户的头像在monggodb中的图片
    
    /* ======================================= */
    
    public int getUserID() {
        return userID;
    }
    public void setUserID(int userID) {
        this.userID = userID;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public int getCredit() {
        return credit;
    }
    public void setCredit(int credit) {
        this.credit = credit;
    }
    public UserRole getRole() {
        return role;
    }
    public void setRole(UserRole role) {
        this.role = role;
    }
    public String getProfileID() {
        return profileID;
    }
    public void setProfileID(String profileID) {
        this.profileID = profileID;
    }
    public String getImageID() {
        return imageID;
    }
    public void setImageID(String imageID) {
        this.imageID = imageID;
    }
    
    
}