package com.jacklinsir.springbootjwt.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 统一的结果集对象
 *
 * @author linSir
 * @version V1.0
 * @Date 2020/1/26 15:36
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResult<T> {
    private long code;
    private String message;
    private T data;

    /**
     * 成功带返回数据
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> success(T data) {
        return new CommonResult<>(ResultCode.LOGIN_SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }

    /**
     * 登入成功返回数据
     *
     * @param code
     * @param data
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> success(ResultCode code, T data) {
        return new CommonResult<T>(code.getCode(), code.getMessage(), data);
    }

    /**
     * 失败不带返回状态
     *
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> failure() {
        return new CommonResult<>(ResultCode.FAILURE.getCode(), ResultCode.FAILURE.getMessage(), null);
    }

    /**
     * 带请求状态码
     *
     * @param code
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> failure(ResultCode code) {
        return new CommonResult<T>(code.getCode(), code.getMessage(), null);
    }


}
