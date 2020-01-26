package com.jacklinsir.springbootjwt.controller;

import com.jacklinsir.springbootjwt.common.CommonResult;
import com.jacklinsir.springbootjwt.common.ResultCode;
import com.jacklinsir.springbootjwt.model.SysUser;
import com.jacklinsir.springbootjwt.service.ISysUserService;
import com.jacklinsir.springbootjwt.utils.JwtTokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author linSir
 * @version V1.0
 * @Date 2020/1/26 15:35
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private ISysUserService iSysUserService;


    @PostMapping("/login")
    public CommonResult login(@RequestBody SysUser user) {
        try {
            String token = iSysUserService.login(user);
            log.info("登入token: {}", token);
            return !StringUtils.isEmpty(token) ? CommonResult.success(ResultCode.LOGIN_SUCCESS, token) : CommonResult.failure(ResultCode.LOGIN_FAILURE);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("登入出现异常: {}", e.fillInStackTrace());
            return CommonResult.failure(ResultCode.LOGIN_FAILURE);
        }
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    @PostMapping("/profile")
    public CommonResult profile(HttpServletRequest request) {
        try {
            //在request对象中获取用户名
            String username = (String) request.getAttribute("username");
            //根据用户名查找相关信息
            SysUser user = iSysUserService.queryByUserName(username);
            return user == null ? CommonResult.failure() : CommonResult.success(user);
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failure(ResultCode.TOKEN_ERROR);
        }

    }
}
