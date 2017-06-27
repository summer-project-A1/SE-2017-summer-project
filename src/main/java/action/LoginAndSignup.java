package action;

import com.opensymphony.xwork2.ActionSupport;

import service.LogInOutService;

public class LoginAndSignup extends ActionSupport {
    private static final long serialVersionUID = 942429918840250595L;

    private LogInOutService logInOutService; 
    
    private String username;
    private String password;
    
    /* =========================================================== */

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public LogInOutService getLogInOutService() {
        return logInOutService;
    }

    public void setLogInOutService(LogInOutService logInOutService) {
        this.logInOutService = logInOutService;
    }

    /* =========================================================== */
    
    public String login() {
        if(this.logInOutService.isLogined() || this.logInOutService.login(this.username, this.password)) {
            return SUCCESS;
        }
        return ERROR;
    }
    
    public String logout() {
        this.logInOutService.logout();
        return SUCCESS;
    }
}