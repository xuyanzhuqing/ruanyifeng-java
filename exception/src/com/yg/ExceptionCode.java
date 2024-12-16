package com.yg;

public enum ExceptionCode {
    USER_NOT_EXIST(100),
    AUTH_NOT_PERMIT(200);
    // 错误码
    public final int code;
    ExceptionCode(int code) {
        this.code = code;
    }
}
