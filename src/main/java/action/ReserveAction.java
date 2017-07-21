package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import model.ReservationProfile;
import service.ReserveService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zzy on 2017/7/5.
 */
public class ReserveAction extends ActionSupport{
    
    private ReserveService reserveService;
    
    private Map params;

    private int bookID;
    private int reserveID;


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

    public int getReserveID() {
        return reserveID;
    }

    public void setReserveID(int reserveID) {
        this.reserveID = reserveID;
    }

    /* =========================================================== */

    public String reserveBook() {
        params = new HashMap();
        //return value: "success","already","error"
        params.put("success",this.reserveService.reserveBook(bookID));
        return "ajax";
    }

    public String cancelReservation() {
        params = new HashMap();
        //return value: true, false
        params.put("success",this.reserveService.cancelReservation(this.reserveID));
        return "ajax";
    }

    public String showMyReservation(){
        List<ReservationProfile> reservationProfileList = this.reserveService.showReservation();
        ActionContext.getContext().put("reservationList",reservationProfileList);
        return "showMyReservation";
    }



}
