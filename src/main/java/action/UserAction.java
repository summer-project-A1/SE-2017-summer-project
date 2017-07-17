package action;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import model.FullAddress;
import model.UserProfile;
import service.UserService;

public class UserAction extends ActionSupport {
    private static final long serialVersionUID = -715680791767950984L;

    private UserService userService; 
    
    private Map params;

    private UserProfile userProfile;
    

    private String province;
    private String city;
    private String district;
    private String address;
    /*
        修改用户密码
     */
    private String oldPassword;
    private String newPassword;
    private String confirmNewPassword;

    /*
        修改用户头像
     */

    private File userPicture;
    private String userPictureFileName;
    private String userPictureContentType;
    
    private String addrID;
    
    /* =========================================================== */



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

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmNewPassword() {
        return confirmNewPassword;
    }

    public void setConfirmNewPassword(String confirmNewPassword) {
        this.confirmNewPassword = confirmNewPassword;
    }
    public File getUserPicture() {
        return userPicture;
    }

    public void setUserPicture(File userPicture) {
        this.userPicture = userPicture;
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
    
    public String getAddrID() {
        return addrID;
    }

    public void setAddrID(String addrID) {
        this.addrID = addrID;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public UserProfile getUserProfile(){
        return this.userProfile;
    }

    /* =========================================================== */


    public String showUserProfile(){
        this.userProfile = this.userService.showUserProfile();
        ActionContext.getContext().put("userProfile",this.userProfile);
        return "showUserProfile";
    }

    public String updatePassword() {
        this.params = new HashMap();
        System.out.println("oldPassword: "+ oldPassword);
        System.out.println("newPassword: "+ newPassword);
        boolean result=this.userService.updatePassword(this.oldPassword, this.newPassword);
        if(result){
            params.put("success",true );

        }
        else{
            params.put("success",false);

        }
        return "ajax";
    }

    public String updateUserProfile(){
        this.params = new HashMap();
        boolean result = this.userService.updateUserProfile(this.userProfile);
        params.put("success", result);
        return "ajax";
    }

    public String updateUserPicture(){
        this.params = new HashMap();
        //boolean result = this.userService.updateUserPicture(this.userPicture);
        //params.put("success", result);
        params.put("success",true);
        return "ajax";
    }
    
    public String getAllAddress() {     // 应合并到checkout的action中
        Map result = this.userService.getAllDeliveryAddress();
        ActionContext.getContext().put("defaultAddrList", result.get("defaultAddrList"));
        ActionContext.getContext().put("addrList", result.get("addrList"));
        return "getAllAddress";
    }
    public String addAddress() {
        FullAddress fullAddress = new FullAddress();
        fullAddress.setProvince(this.province);
        fullAddress.setCity(this.city);
        fullAddress.setDistrict(this.district);
        fullAddress.setAddress(this.address);
        fullAddress.setIsDefault(false);
        this.params = this.userService.addDeliveryAddress(fullAddress);
        return "ajax";
    }
    public String setDefaultAddress() {
        this.params = this.userService.setDefaultDeliveryAddress(this.addrID);
        return "ajax";
    }
    public String deleteAddress() {
        this.params = new HashMap();
        boolean result = this.userService.deleteDeliveryAddress(this.addrID);
        this.params.put("result", result);
        return null;
    }



}