package com.jacklinsir.springbootjwt.model;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author linSir
 * @version V1.0
 * @Date 2020/1/26 13:35
 */
@Data
@Entity
@Table(name = "sys_user")
public class SysUser implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;

    @Column(name = "icon")
    private String icon;

    @Column(name = "email")
    private String email;

    @Column(name = "nick_name")
    private String nickName;

    @Column(name = "note")
    private String note;

    @Column(name = "create_time")
    private String createTime;

    @Column(name = "login_time")
    private String loginTime;

    @Column(name = "status")
    private Integer status;
}
