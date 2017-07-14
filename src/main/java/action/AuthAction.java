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
    private UserProfile registerInfo;

    private String email;
    private String password;
    private String confirmpassword;


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

    public void setRegisterInfo(UserProfile registerInfo){
        this.registerInfo=registerInfo;
    }
    public UserProfile getRegisterInfo(){
       return this.registerInfo;
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
        if(this.userService.checkEmailAvailable(this.registerInfo.getEmail())) {
            params.put("result", true);
        }
        else {
            params.put("result", false);
        }
        return "ajax";
    }

    public String register() {
        if(this.registerInfo.getCity()==null&&this.registerInfo.getDistrict()==null){
            registerInfo.setCity(this.registerInfo.getProvince());
            registerInfo.setDistrict(this.registerInfo.getProvince());
        }

        registerInfo.setNickName(this.registerInfo.getNickName()==null?"":this.registerInfo.getNickName());

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
