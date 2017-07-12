package common.constants;

public enum OrderStatus {
    COMPLETED,     // 已完成（已确认收货）
    NOTPAID,       // 未支付
    CANCELED,      // 已取消（只能在未支付时取消）
    NOTSHIPPED,    // 未发货
    SHIPPED;       // 已发货但未确认收货
}