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
        List<ExchangeProfile> initiativeExchange = (List<ExchangeProfile>) result.get("initiativeExchange");
        List<ExchangeProfile> initiativeExchangeHistory = (List<ExchangeProfile>) result.get("initiativeExchangeHistory");
        List<ExchangeProfile> passiveExchange = (List<ExchangeProfile>) result.get("passiveExchange");
        List<ExchangeProfile> passiveExchangeHistory = (List<ExchangeProfile>) result.get("passiveExchangeHistory");

        ActionContext.getContext().put("initiativeExchange",initiativeExchange);
        ActionContext.getContext().put("initiativeExchangeHistory",initiativeExchangeHistory);
        ActionContext.getContext().put("passiveExchange",passiveExchange);
        ActionContext.getContext().put("passiveExchangeHistory",passiveExchangeHistory);
        return "showMyExchange";
    }



}
