package com.athen.system.api.cglib.T.model;

/**
 * User: chenying
 * Date: 2019-08-02
 * Time: 16:11
 * since: 1.0.0
 */
public enum State {
    Disable(0, "锁定"), Enable(1, "开启"), NoStart(-1, "未开始");

    int code;
    String value;

    State(int code, String value) {
        this.code = code;
        this.value = value;
    }

    /** 显示用 */
    public String getValue() {
        return value;
    }

    /** 数据关联用 */
    public int getCode() {
        return code;
    }
}
