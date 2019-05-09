package com.david.core;

/**
 * 响应码枚举，参考HTTP状态码的语义
 */
public enum ResultCode {

    SUCCESS(200),//成功
    FAIL(4000);//失败

    private final int code;

    ResultCode(int code) {
        this.code = code;
    }

    public int code() {
        return code;
    }
}
