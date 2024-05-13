package com.luka.r18;

import com.luka.r18.mappers.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class R18ApplicationTests {

    @Resource
    UserMapper userMapper;

    @Test
    void contextLoads() {

    }

}
