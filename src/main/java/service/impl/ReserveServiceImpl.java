package service.impl;

import java.util.List;

import model.Reserve;
import service.ReserveService;

public class ReserveServiceImpl extends BaseServiceImpl implements ReserveService {

    private ReserveService reserveService;
    
    /* ============================================================ */
    
    public ReserveService getReserveService() {
        return reserveService;
    }

    public void setReserveService(ReserveService reserveService) {
        this.reserveService = reserveService;
    }

    /* ============================================================ */
    
    @Override
    public Boolean reserveBook(int bookID) {
        // TODO 自动生成的方法存根
        return null;
    }

    @Override
    public List<Reserve> showReservation() {
        
        return null;
    }

    @Override
    public Boolean cancelReservation(int applyID) {
        // TODO 自动生成的方法存根
        return null;
    }
    
}