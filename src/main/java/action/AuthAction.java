package action;

import com.opensymphony.xwork2.ActionSupport;

import model.UserProfile;
import service.UserService;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zzy on 2017/7/5.
 */
public class AuthAction extends ActionSupport{
    private UserService userService;

    private Map params;

    private String email;
    private String password;
    private String confirmpassword;
    private String nickName;
    private String gender;
    private String mobile;
    private String province;
    private String city;
    private String district;
    private String address;

    /* =========================================================== */

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmpassword() {
        return confirmpassword;
    }

    public void setConfirmpassword(String confirmpassword) {
        this.confirmpassword = confirmpassword;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public Map getParams() {
        return params;
    }

    public void setParams(Map params) {
        this.params = params;
    }

    /* =========================================================== */

    public String checkEmailAvailable() {
        params = new HashMap();
        if(this.userService.checkEmailAvailable(this.email)) {
            params.put("result", true);
        }
        else {
            params.put("result", false);
        }
        return "ajax";
    }

    public String register() {
        UserProfile registerInfo = new UserProfile();
        registerInfo.setEmail(this.email);
        registerInfo.setPlainPassword(this.password);
        registerInfo.setMobile(this.mobile);
        registerInfo.setProvince(this.province);
        if(this.city != null && this.district == null ) {
            // 直辖市
            // 注意此时前台并不传递district到后台，且district的内容保存在city参数中！
            registerInfo.setCity(this.province+"市");
            registerInfo.setDistrict(this.city);
        }
        else {
            // 普通省市（三个属性全有）或国外（只有province属性）
            registerInfo.setCity(this.city);
            registerInfo.setDistrict(this.district);
        }
        registerInfo.setAddress(this.address);
        registerInfo.setNickName(this.nickName==null?"":this.nickName);
        registerInfo.setGender(this.gender);

        boolean result = this.userService.register(registerInfo);
        this.params = new HashMap();
        if(result) {
            this.params.put("result", true);
            //this.params.put("message", "注册成功");
        }
        else {
            this.params.put("result", false);
            //this.params.put("message", "注册失败");
        }
        return "ajax";
    }

    public String login() {
        this.params = this.userService.login(this.email, this.password);
        return "ajax";
    }

    public String signin(){
        this.userService.login(this.email, this.password);
        return "signin";
    }

    public String logout() {
        this.userService.logout();
        return "logout";
    }

}
