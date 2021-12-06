package com.zekizheng.trading.status;

/**
 * @author zongzi
 **/
public enum OrderStatus {
    CREATED(0),
    PAID(1),
    ON_DELIVERY(2),
    ON_RECEIVED(3),
    FINISH(4),
    APPLYING_REFUND(5),
    REFUNDING(6),
    CLOSED(7);


    private final int key;

    OrderStatus(int key) {
        this.key = key;
    }

    public int getKey() {
        return this.key;
    }
}
