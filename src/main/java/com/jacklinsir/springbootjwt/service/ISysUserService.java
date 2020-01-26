package com.jacklinsir.springbootjwt.service;

import com.jacklinsir.springbootjwt.model.SysUser;

import java.util.List;

/**
 * @author linSir
 * @version V1.0
 * @Date 2020/1/26 13:44
 */
public interface ISysUserService {

    /**
     * 查询所有用户
     *
     * @return
     */
    List<SysUser> queryUserList();

    /**
     * 处理登入 返回登入成功token
     *
     * @param user
     * @return
     */
    String login(SysUser user);

    /**
     * 根据用户名查找对象
     *
     * @param username
     * @return
     */
    SysUser queryByUserName(String username);
}
