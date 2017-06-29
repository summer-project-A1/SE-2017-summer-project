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

    public String register() {
        return this.userService.register(this.email, this.password)? SUCCESS : ERROR;
    }
    
    public String login() {
        this.params = this.userService.login(this.email, this.password);
        return SUCCESS;
    }
    
    public String logout() {
        this.userService.logout();
        return "logout";
    }
}