package com.jacklinsir.springbootjwt.service;

import com.jacklinsir.springbootjwt.common.ResultCode;
import com.jacklinsir.springbootjwt.dao.SysUserDao;
import com.jacklinsir.springbootjwt.model.SysUser;
import com.jacklinsir.springbootjwt.utils.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

/**
 * @author linSir
 * @version V1.0
 * @Date 2020/1/26 13:45
 */
@Service
public class SysUserService implements ISysUserService {

    @Autowired
    private SysUserDao sysUserDao;

    @Autowired
    private JwtTokenUtils jwtTokenUtils;


    @Override
    public String login(SysUser user) {
        if (user == null) {
            throw new RuntimeException("参数异常");
        }
        try {
            //根据帐号和密码查询用户
            Example<SysUser> example = Example.of(user);
            Optional<SysUser> sysUserDaoOne = sysUserDao.findOne(example);
            SysUser sysUser = sysUserDaoOne.get();
            //判断用户不能为空，开始使用jwt生成token
            if (sysUser != null) {
                String token = jwtTokenUtils.generateToken(user);
                return token;
            }
        } catch (Exception e) {
            throw new RuntimeException("帐号密码错误");
        }
        return null;
    }

    @Override
    public SysUser queryByUserName(String username) {
        if (StringUtils.isEmpty(username)) {
            throw new RuntimeException(ResultCode.TOKEN_ERROR.getMessage());
        }
        SysUser user = new SysUser();
        user.setUsername(username);
        return sysUserDao.findOne(Example.of(user)).get();
    }

    @Override
    public List<SysUser> queryUserList() {
        return sysUserDao.findAll();
    }
}
