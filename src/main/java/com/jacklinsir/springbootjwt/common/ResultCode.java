package com.jacklinsir.springbootjwt.common;

import lombok.AllArgsConstructor;

/**
 * @author linSir
 * @version V1.0
 * @Date 2020/1/26 15:38
 */
@AllArgsConstructor
public enum ResultCode implements IErrorCode {
    //公共业务状态
    SUCCESS(200L, "请求成功！"),
    FAILURE(500L, "请求失败！"),
    //登入模块
    LOGIN_SUCCESS(600L, "登入成功！"),
    LOGIN_FAILURE(601L, "登入失败,帐号或者密码错误！"),
    //授权模块
    UNAUTHORIZATION(700L, "未授权"),
    //token状态
    TOKEN_ERROR(800L, "token无效或者已经过期！");

    private long code;
    private String message;

    @Override
    public long getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
