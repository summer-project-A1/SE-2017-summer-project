package action;

import com.opensymphony.xwork2.ActionContext;
import model.User;
import service.AdminService;

import java.util.List;
import java.util.Map;

/**
 * Created by zzy on 2017/7/5.
 */
public class AdminAction {
    private AdminService adminService;

    private int userID;
    private String email;
    private String password;
    private int credit;
    private Map params;
    private int bookID;

    public AdminService getAdminService(){return adminService;}
    public void setAdminService(AdminService adminService){this.adminService = adminService;}
    public int getUserID() {
        return userID;
    }
    public void setUserID(int userID) {
        this.userID = userID;
    }
    public Map getParams() {
        return params;
    }
    public void setParams(Map params) {
        this.params = params;
    }
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
    public int getCredit() {
        return credit;
    }
    public void setCredit(int credit) {
        this.credit = credit;
    }

    public int getBookID() {
		return bookID;
	}
	public void setBookID(int bookID) {
		this.bookID = bookID;
	}
	
	public String showAllUserList(){
        List<User> list = this.adminService.showAllUserList();
        ActionContext.getContext().put("userList",list);
        return "showAllUserList";
    }

    public String deleteUser(){
        if(this.adminService.deleteUser(userID)){
            return "deleteUser";
        }
        return null;
    }

    public String addUser(){
        if(this.adminService.addUser(email,password,credit)){
            return "addUser";
        }
        return null;
    }

    public String resetPassword(){
        if(this.adminService.resetPassword(userID)){
            return "resetPassword";
        }
        return null;
    }
    
    public String deleteBook()
    {
    	if(adminService.deleteBook(bookID))
    		return "deleteBook";
    	return null;
    }
}
