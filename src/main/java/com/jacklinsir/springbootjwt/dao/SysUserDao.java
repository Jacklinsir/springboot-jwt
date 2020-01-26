package com.jacklinsir.springbootjwt.dao;

import com.jacklinsir.springbootjwt.model.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * @author linSir
 * @version V1.0
 * @Date 2020/1/26 13:42
 */
@Repository
public interface SysUserDao extends JpaRepository<SysUser, Long>, JpaSpecificationExecutor<SysUser>, Serializable {
}
