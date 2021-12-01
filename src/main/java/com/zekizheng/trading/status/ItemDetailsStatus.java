package com.zekizheng.trading.status;

/**
 * @author zongzi
 **/
public enum ItemDetailsStatus {
    ON_SELL(0),
    SOLD(1),
    HIDE(2);

    private final int key;

    private ItemDetailsStatus(int key) {
        this.key = key;
    }

    public int getKey() {
        return this.key;
    }
}
