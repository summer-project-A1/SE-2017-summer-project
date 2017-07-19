package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import model.Book;
import model.BorrowProfile;
import service.BorrowService;
import service.CartService;
import service.OrderService;
import service.UserService;

import java.util.*;

public class BorrowAction extends ActionSupport {

    private BorrowService borrowService;
    private CartService cartService;
    private UserService userService;

    private int bookID;
    private Map params;

	private int borrowID;
    private String trackingNo1; //买家还书快递单号
    private String address;  //买家地址
    private String trackingNo2;   //卖家发书快递单号
    private String returnAddress; //买家地址
    private List<Integer> borrowIDList;
    
    /* ============================================================== */
    
    public BorrowService getBorrowService() {
        return borrowService;
    }
    public void setBorrowService(BorrowService borrowService) {
        this.borrowService = borrowService;
    }
    public CartService getCartService() {
        return cartService;
    }
    public void setCartService(CartService cartService) {
        this.cartService = cartService;
    }

    public UserService getUserService(){return userService;}
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    
    public int getBookID() {
        return bookID;
    }
    public void setBookID(int bookID) {
        this.bookID = bookID;
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
    public List<Integer> getBorrowIDList() {
        return borrowIDList;
    }
    public void setBorrowIDList(List<Integer> borrowIDList) {
        this.borrowIDList = borrowIDList;
    }
    public int getBorrowID() {
        return borrowID;
    }
    public void setBorrowID(int borrowID) {
        this.borrowID = borrowID;
    }
    public String getTrackingNo1() {
        return trackingNo1;
    }
    public void setTrackingNo1(String trackingNo1) {
        this.trackingNo1 = trackingNo1;
    }
    public String getTrackingNo2(){return trackingNo2;}
    public void setTrackingNo2(String trackingNo2){this.trackingNo2 = trackingNo2;}
    public String getReturnAddress(){return returnAddress;}
    public void setReturnAddress(String returnAddress){this.returnAddress = returnAddress;}
    
    /* ============================================================== */
    
    public String borrowCheckout() {        // 从购物车跳转到地址确认页面，不修改数据库
        Map borrowInfo = this.cartService.showBorrowCart();
        List<Book> booksInBorrowCart = (List<Book>)borrowInfo.get("booksInBorrowCart");
        Integer totalCredit = (Integer)borrowInfo.get("totalCredit");
        ActionContext.getContext().put("action","borrowCheckout");
        ActionContext.getContext().put("booksInOrder",booksInBorrowCart.isEmpty()?null:booksInBorrowCart);
        ActionContext.getContext().put("totalCredit",totalCredit);
        Map result = this.userService.getAllDeliveryAddress();
        ActionContext.getContext().put("defaultAddrList", result.get("defaultAddrList"));
        ActionContext.getContext().put("addrList", result.get("addrList"));
        return "borrowCheckout";
    }
    
    public String createBorrowOrder() {     // 用户创建订单，添加到数据库，跳转到付款页面（不修改书的状态）
        Map result = this.borrowService.createBorrowOrder(this.address);
        List<BorrowProfile> borrowProfileList = (List<BorrowProfile>)result.get("borrowProfileList");
        Integer totalCredit = (Integer)result.get("totalCredit");
        ActionContext.getContext().put("buyOrBorrow","borrow");
        ActionContext.getContext().put("totalCredit",totalCredit);
        ActionContext.getContext().put("borrowProfileList",borrowProfileList);
        return "createBorrowOrder";
    }
    public String confirmBorrowOrder() {       // 用户付款确认订单（允许多个订单），修改订单状态
        this.params = this.borrowService.confirmBorrowOrder(this.borrowIDList);
        return "ajax";
    }
    public String showMyBorrow(){
        this.params = this.borrowService.showMyBorrow();
        List borrowBook = (List)params.get("borrowBook");
        List borrowHistoryBook = (List)params.get("borrowHistoryBook");
        ActionContext.getContext().put("borrowBook",borrowBook);
        ActionContext.getContext().put("borrowHistoryBook",borrowHistoryBook);
        return "showMyBorrow";
    }

    public String delayBook(){
        /*
        params = new HashMap();
        Date yhdate = new Date();  // 需要一个应还日期，尚未实现
        if(this.borrowService.delayBook(getBookID())){
            params.put("success",true);
            params.put("yhdate",yhdate);
        }
        */
        this.params = this.borrowService.delayBook(this.borrowID);
        return "ajax";
    }

    public String returnBook(){
        /*
        params = new HashMap();
        Date returnDate = new Date(); // 需要一个归还日期，尚未实现
        if(this.borrowService.returnBook(getBookID())){
            params.put("success",true);
            params.put("returnDate",returnDate);
        }
        */
        this.params = this.borrowService.returnBook(this.borrowID,this.trackingNo1);
        return "ajax";
    }

    public String confirmBorrowReceipt(){
        this.params = this.borrowService.confirmBorrowReceipt(this.borrowID);
        return "ajax";
    }

    public String deliverBorrowOrder(){ //卖家发货,前台传borrowID，发货单号，还书地址,返回success和发货时间
        this.params = this.borrowService.deliveryBorrowOrder(this.borrowID,this.trackingNo2,this.returnAddress);
        return "ajax";
    }

    public String confirmReturnReceipt(){ //卖家收货，前台传borrowID,返回success和收货时间
        this.params = this.borrowService.confirmReturnReceipt(this.borrowID);
        return "ajax";
    }

    public String cancelBorrowOrder(){  //预留取消借阅，日后解封
        Map returnMap = new HashMap();
        if(this.borrowService.cancelBorrowOrder(this.borrowID)){
            returnMap.put("success",true);
        }
        else{
            returnMap.put("success",false);
        }
        setParams(returnMap);
        return "ajax";
    }

}
