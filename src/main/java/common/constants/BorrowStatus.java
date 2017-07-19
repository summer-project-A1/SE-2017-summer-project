package common.constants;

public enum BorrowStatus {
    /*
     * 操作：状态
     * 
     * 生成订单：买家未付款
     * 买家付款后:卖家未发货
     * 卖家发货:卖家已发货
     * 买家确认收货:买家未归还
     * 买家归还:待卖家确认
     * 卖家确认:已完成
     * 买家取消:已取消
     */
    BUYER_NOTPAID,
    SELLER_NOT_SHIPPED,
    SELLER_SHIPPED,
    BUYER_NOT_RETURNED,
    BUYER_RETURNED,
    COMPLETED,
    CANCELED;
}