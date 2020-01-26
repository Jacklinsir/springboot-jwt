package com.jacklinsir.springbootjwt;

import com.jacklinsir.springbootjwt.service.ISysUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootJwtApplicationTests {

      @Autowired
    private ISysUserService iSysUserService;

    @Test
    public void contextLoads() {

    }

}
