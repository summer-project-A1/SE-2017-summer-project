package action;

import com.opensymphony.xwork2.ActionSupport;

import service.ReserveService;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zzy on 2017/7/5.
 */
public class ReserveAction extends ActionSupport{
    
    private ReserveService reserveService;
    
    private Map params;

    private int bookID;


    /* =========================================================== */
    
    public ReserveService getReserveService() {
        return reserveService;
    }

    public void setReserveService(ReserveService reserveService) {
        this.reserveService = reserveService;
    }

    public Map getParams() {
        return params;
    }

    public void setParams(Map params) {
        this.params = params;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    /* =========================================================== */

    public String reserveBook() {
        params = new HashMap();
        //return value: "success","already","error"
        params.put("success",this.reserveService.reserveBook(bookID));
        return "ajax";
    }

    public String cancelReservation() {
        this.params = new HashMap();
        params = new HashMap();
        //return value: true, false
        params.put("result",this.reserveService.cancelReservation(bookID));
        params.put("success", true);

        return "ajax";
    }


}
