package com.david.core;

/**
 * =================================
 * Created by David on 2018/12/5.
 * mail:    17610897521@163.com
 * 描述:      响应提示信息
 */

public enum ResultMessage {

    SUCCESS("SUCCESS"),//成功
    FAIL("FAIL");//失败

    private final String message;

    ResultMessage(String message) {
        this.message = message;
    }

    public String message() {
        return message;
    }

}
