package model;

import common.constants.UserRole;
import common.constants.UserStatus;

import java.util.Date;

public class User {
    private int userID;
    private String nickName;
    private String password;
    private String email;       // 邮箱作为登录名，具有唯一性
    private int credit;         // 用户积分
    private UserRole role;      // 用户角色（管理员/普通）
    private int honesty;       //信用积分
    private String province;        // 用户地址（区别于收货地址）
    private String city;
    private String district;
    private String address;
    private String profileID;         // 用户的详细信息在monggodb中的id
    private String imageID;           // 用户的头像在monggodb中的图片
    private UserStatus status;
    private String activeCode;
    private Date due;
    
    /* ======================================= */
    
    public int getUserID() {
        return userID;
    }
    public void setUserID(int userID) {
        this.userID = userID;
    }
    public String getNickName() {
        return nickName;
    }
    public void setNickName(String nickName) {
        this.nickName = nickName;
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
    public int getHonesty() {
		return honesty;
	}
	public void setHonesty(int honesty) {
		this.honesty = honesty;
	}
	public String getProvince() {
        return province;
    }
    public void setProvince(String province) {
        this.province = province;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getDistrict() {
        return district;
    }
    public void setDistrict(String district) {
        this.district = district;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
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

    public String getActiveCode() {
        return activeCode;
    }

    public void setActiveCode(String activeCode) {
        this.activeCode = activeCode;
    }

    public Date getDue() {
        return due;
    }

    public void setDue(Date due) {
        this.due = due;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }
}