package action;

import com.opensymphony.xwork2.ActionSupport;


import java.util.HashMap;
import java.util.Map;

/**
 * Created by zzy on 2017/7/5.
 */
public class ReserveAction extends ActionSupport{


    private Map params;

   private int bookID;


    /* =========================================================== */
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
        //params.put("result",this.reserveService.reserveBook(bookID));
        params.put("success","success");

        return "ajax";
    }

    public String cancelReservation() {
        this.params = new HashMap();
        params = new HashMap();
        //return value: true, false
        //params.put("result",this.reserveService.cancelReservation(bookID));
        params.put("success", true);

        return "ajax";
    }


}
