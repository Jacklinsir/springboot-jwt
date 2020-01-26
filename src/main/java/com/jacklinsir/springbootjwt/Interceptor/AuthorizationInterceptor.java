package com.jacklinsir.springbootjwt.Interceptor;

import com.jacklinsir.springbootjwt.common.CommonResult;
import com.jacklinsir.springbootjwt.common.ResultCode;
import com.jacklinsir.springbootjwt.utils.JwtTokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 授权拦截器
 *
 * @author linSir
 * @version V1.0
 * @Date 2020/1/26 19:44
 */
@Slf4j
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取请求头的token信息
        String authorization = request.getHeader("Authorization");
        if (!StringUtils.isEmpty(authorization) && authorization.startsWith("Bearer")) {
            //替换Bearer+空格
            String token = authorization.replace("Bearer", "");
            //解析token
            String username = jwtTokenUtils.getUserNameFromToken(token);
            if (!StringUtils.isEmpty(username)) {
                log.info("checking username:{}", username);
                //方便其他地方取出用来查询相关用户信息
                request.setAttribute("username", username);
            }
            //放行
            return true;
        }
        throw new RuntimeException(ResultCode.UNAUTHORIZATION.getMessage());
    }
}
