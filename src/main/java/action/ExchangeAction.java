package action;

import com.opensymphony.xwork2.ActionSupport;
import service.ExchangeService;

/**
 * Created by zzy on 2017/7/20.
 */
public class ExchangeAction extends ActionSupport{
    private ExchangeService exchangeService;
    private int bookID;

    /*======================================================*/
    public void setExchangeService(ExchangeService exchangeService){
        this.exchangeService=exchangeService;
    }

    public ExchangeService getExchangeService(){
        return this.exchangeService;
    }
    /*======================================================*/
}
