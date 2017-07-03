package action;

import java.util.HashMap;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;

import service.UserService;

public class UserAction extends ActionSupport {
    private static final long serialVersionUID = -715680791767950984L;

    private UserService userService; 
    
    private Map params;
    
    private String email;
    private String password;
    private String confirmpassword;
    private String name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        Map registerInfo = new HashMap();
        registerInfo.put("email", this.email);
        registerInfo.put("password", this.password);
        registerInfo.put("mobile", this.mobile);
        registerInfo.put("province", this.province);
        if(this.district != null) {
            // 普通省市
            registerInfo.put("city", this.city);
            registerInfo.put("district", this.district);
        }
        else {
            // 直辖市
            // 注意此时前台并不传递district到后台，且district的内容保存在city参数中！
            registerInfo.put("city", this.province);
            registerInfo.put("district", this.city);
        }
        registerInfo.put("address", this.address);
        registerInfo.put("name", this.name);
        registerInfo.put("gender", this.gender);
        
        boolean result = this.userService.register(registerInfo);
        this.params = new HashMap();
        if(result) {
            this.params.put("result", true);
            this.params.put("message", "注册成功");
        }
        else {
            this.params.put("result", false);
            this.params.put("message", "注册失败");
        }
        return "ajax";
    }
    
    public String login() {
        this.params = this.userService.login(this.email, this.password); 
        return "ajax";
    }
    
    public String logout() {
        this.userService.logout();
        return "logout";
    }
}