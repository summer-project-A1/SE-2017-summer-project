package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import model.ExchangeProfile;
import service.ExchangeService;
import service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zzy on 2017/7/20.
 */
public class ExchangeAction extends ActionSupport{
    private ExchangeService exchangeService;
    private UserService userService;
    private int wantedBookID;
    private int hadBookID;
    private String address;  //���뽻���ߵ�ַ
    private Map params;
    private int exchangeID;
    private String address2;

    /*======================================================*/
    public void setExchangeService(ExchangeService exchangeService){
        this.exchangeService=exchangeService;
    }

    public ExchangeService getExchangeService(){
        return this.exchangeService;
    }
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    public UserService getUserService(){
        return this.userService;
    }
    public void setWantedBookID(int wantedBookID) {
        this.wantedBookID = wantedBookID;
    }
    public int getWantedBookID(){
        return this.wantedBookID;
    }
    public int getHadBookID() {
        return hadBookID;
    }
    public void setHadBookID(int hadBookID) {
        this.hadBookID = hadBookID;
    }
    public Map getParams() {
        return params;
    }

    public void setParams(Map params) {
        this.params = params;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public int getExchangeID(){return this.exchangeID;}
    public void setExchangeID(int exchangeID){this.exchangeID = exchangeID;}
    public String getAddress2() {
        return address2;
    }
    public void setAddress2(String address2) {
        this.address2 = address2;
    }
    /*======================================================*/
    public String prepareExchange(){
        ActionContext.getContext().put("wantedBook",exchangeService.prepareExchange(wantedBookID).get("wantedBook"));
        ActionContext.getContext().put("userReleasedBookList",exchangeService.prepareExchange(wantedBookID).get("userReleasedBookList"));
        ActionContext.getContext().put("defaultAddrList", userService.getAllDeliveryAddress().get("defaultAddrList"));
        ActionContext.getContext().put("addrList", userService.getAllDeliveryAddress().get("addrList"));
        return "showExchangeApply";
    }

    public String createExchangeOrder(){
        this.params=new HashMap();
        this.params.put("success",this.exchangeService.applyExchange(wantedBookID,hadBookID,address));
        return "ajax";
    }

    public String showMyExchange(){
        Map result = this.exchangeService.showMyExchange();
        List<ExchangeProfile> activeExchange = (List<ExchangeProfile>) result.get("activeExchange");
        List<ExchangeProfile> activeExchangeHistory = (List<ExchangeProfile>) result.get("activeExchangeHistory");
        List<ExchangeProfile> passiveExchange = (List<ExchangeProfile>) result.get("passiveExchange");
        List<ExchangeProfile> passiveExchangeHistory = (List<ExchangeProfile>) result.get("passiveExchangeHistory");
        System.out.println(passiveExchange.size()+"^&*");

        ActionContext.getContext().put("activeExchange",activeExchange);
        ActionContext.getContext().put("activeExchangeHistory",activeExchangeHistory);
        ActionContext.getContext().put("passiveExchange",passiveExchange);
        ActionContext.getContext().put("passiveExchangeHistory",passiveExchangeHistory);
        return "showMyExchange";
    }

    public String rejectExchange(){
        if(this.exchangeService.rejectExchange(exchangeID)){
            return "rejectExchange";
        }
        return null;
    }

    public String agreeExchange(){
        if(this.exchangeService.agreeExchange(exchangeID,address2)){
            return "agreeExchange";
        }
        return null;
    }

    public String cancelExchange(){
        if(this.exchangeService.cancelExchange(exchangeID)){
            return "cancelExchange";
        }
        return null;
    }


}
